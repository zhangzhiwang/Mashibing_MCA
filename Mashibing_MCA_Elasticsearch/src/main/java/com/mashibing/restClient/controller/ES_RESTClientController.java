package com.mashibing.restClient.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mashibing.restClient.util.ESClientUtil;
import com.mashibing.transportClient.entity.Product;
import com.mashibing.transportClient.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("rest")
public class ES_RESTClientController {
    private static final RestHighLevelClient CLIENT = ESClientUtil.getESClientInstance();

    @Autowired
    private IProductService productService;

    /**
     * 初始化数据
     *
     * @return
     */
    @RequestMapping("init")// @RequestMapping如果不指定请求方法，那么默认所有的请求方法都可以接受
    public String init() {
        try {
            // 查询索引是不是存在
            GetIndexRequest getIndexRequest = new GetIndexRequest("index17_8");
            boolean exists = CLIENT.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
            if (exists) {
                // 存在则删除
                DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("index17_8");
                AcknowledgedResponse acknowledgedResponse = CLIENT.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
                if (acknowledgedResponse.isAcknowledged()) {
                    log.info("索引已删除");
                }
            }

            // 创建索引
            CreateIndexRequest createIndexRequest = new CreateIndexRequest("index17_8");
            createIndexRequest.mapping("{\n" +
                    "    \"properties\": {\n" +
                    "      \"message\": {\n" +
                    "        \"type\": \"text\",\n" +
                    "        \"fields\": {\n" +
                    "          \"my_sub_field\": {\n" +
                    "            \"type\": \"keyword\",\n" +
                    "            \"ignore_above\": 256\n" +
                    "          }\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }", XContentType.JSON);

            List<Product> productList = productService.queryAll();
            CreateIndexResponse createIndexResponse = CLIENT.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            log.info("是否创建成功：" + createIndexResponse.isAcknowledged());

            // 到数据库查询数据
            List<Product> products = productService.queryAll();

            // 批量插入文档
            BulkRequest bulkRequest = new BulkRequest("index17_8");
            for (Product product : products) {
                IndexRequest indexRequest = new IndexRequest();
                indexRequest.id(String.valueOf(product.getId()));
                indexRequest.source(JSONObject.toJSONString(product), XContentType.JSON);
                bulkRequest.add(indexRequest);
            }
            BulkResponse bulkItemResponse = CLIENT.bulk(bulkRequest, RequestOptions.DEFAULT);
            BulkItemResponse[] items = bulkItemResponse.getItems();
            for (BulkItemResponse item : items) {
                if (item.isFailed()) {
                    log.error("文档创建失败：" + item.getFailureMessage());
                }
            }
            log.info("数据初始化完毕！");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "ok";
    }

    /**
     * 通过关键字搜索
     *
     * @param keyword
     * @param from
     * @param size
     */
    @GetMapping("searchByKeyword/{kw}/{start}/{size}")
    public String searchByKeyword(@PathVariable("kw") String keyword, @PathVariable("start") int from, @PathVariable("size") int size) throws IOException {
        SearchRequest searchRequest = new SearchRequest("index17_8");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("name", keyword).fuzziness(Fuzziness.AUTO));// AUTO的修改距离实际上是2
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);

        // 也可以使用链式调用
//        searchRequest.source(searchSourceBuilder);
//        searchSourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchQuery("name", keyword).fuzziness(Fuzziness.AUTO))
//                .from(from)
//                .size(size);

        SearchResponse searchResponse = CLIENT.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = searchResponse.getHits().getHits();
        JSONArray jsonArray = new JSONArray();
        for (SearchHit hit : hits) {
            log.info(hit.getSourceAsString());

            jsonArray.add(JSONObject.parseObject(hit.getSourceAsString()));
        }

        log.info("result : " + jsonArray);
        return jsonArray.toString();
    }

    @GetMapping("bulk")
    public String bulk() throws IOException {
        BulkRequest bulkRequest = new BulkRequest("index17_8");
        bulkRequest.add(new IndexRequest("index17_8").id("12").source("{\"name\":\"zhangsan\"}", XContentType.JSON));
        bulkRequest.add(new DeleteRequest("index17_9").id("1"));
        bulkRequest.add(new UpdateRequest("index17_8", "1").doc(XContentType.JSON, "name", "lisi"));

        BulkResponse bulkResponse = CLIENT.bulk(bulkRequest, RequestOptions.DEFAULT);
        BulkItemResponse[] items = bulkResponse.getItems();
        for (BulkItemResponse item : items) {
            if(item.isFailed()) {
                log.error(item.getFailureMessage());
            }
        }

        return "ok";
    }

    @GetMapping("multiSearch")
    public String multiSearch() throws Exception {
        MultiSearchRequest multiSearchRequest = new MultiSearchRequest();

        SearchRequest searchRequest1 = new SearchRequest("index17_8");
        SearchSourceBuilder searchSourceBuilder1 = new SearchSourceBuilder().query(QueryBuilders.matchQuery("name", "SE2"));
        searchRequest1.source(searchSourceBuilder1);
        multiSearchRequest.add(searchRequest1);

        SearchRequest searchRequest2 = new SearchRequest("index17_9");
        SearchSourceBuilder searchSourceBuilder2 = new SearchSourceBuilder().query(QueryBuilders.matchQuery("name", "zhang"));
        searchRequest2.source(searchSourceBuilder2);
        multiSearchRequest.add(searchRequest2);

        MultiSearchResponse multiSearchResponse = CLIENT.msearch(multiSearchRequest, RequestOptions.DEFAULT);
        MultiSearchResponse.Item[] responses = multiSearchResponse.getResponses();
        for(MultiSearchResponse.Item item : responses) {
            SearchResponse searchResponse = item.getResponse();
            SearchHit[] hits = searchResponse.getHits().getHits();
            for(SearchHit hit : hits) {
                log.info(hit.getSourceAsString());
            }
            log.info("----------------------------");
        }

        return "ok";
    }

    @GetMapping("boolSearch")
    public String boolSearch() throws Exception {
        SearchRequest searchRequest = new SearchRequest("index17_8");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(
                QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("name", "小"))
                        .must(QueryBuilders.rangeQuery("price").gte(10).lte(3000))
                        .mustNot(QueryBuilders.matchQuery("lv", "百"))
        );
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = CLIENT.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = searchResponse.getHits().getHits();
        for(SearchHit hit : hits) {
            log.info(hit.getSourceAsString());
        }
        return "ok";
    }
}
