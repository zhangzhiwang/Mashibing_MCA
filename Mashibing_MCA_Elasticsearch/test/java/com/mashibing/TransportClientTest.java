package com.mashibing;

import com.mashibing.transportClient.entity.Product;
import com.mashibing.transportClient.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.Transport;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.xcontent.XContentFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootTest
@MapperScan(basePackages = {"com.mashibing.transportClient.mapper"})
@Slf4j
public class TransportClientTest {
    @Autowired
    private IProductService productService;

    @Test
    public void testCRUD() {
        TransportClient client = null;
        try {
            // 初始化TransportClient客户端
            Settings settings = Settings.builder()
                    .put("cluster.name", "elasticsearch")  // 配置里面的key就是es的配置文件elasticsearch.yml里面的key
                    .put("node.name", "node-1")
                    .build();
            client = new PreBuiltTransportClient(settings);// 传入配置
//            TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);// 如果使用默认配置，那么可以传入Settings.EMPTY
            // 注意：这个端口是TransportClient客户端和es服务端通信的端口，默认是9300，不是9200端口。另外，如果需要连接多个es服务器可以调用多次addTransportAddress方法，每个方法里面传入一个ip和端口
            client.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
            // TransportClient的初始化可以详见官方文档：https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/transport-client.html

            /*
            1、增（Index）
            官方文档提供了4种新增索引的方式，详见：https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-docs-index.html
             */
            List<Product> productList = productService.queryAll();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            for (Product product : productList) {
                // 创建返回的是IndexResponse
                IndexResponse indexResponse = client.prepareIndex("index17", "_doc", product.getId() + "")// 相当于：put /product/_doc/{id}
                        .setSource(XContentFactory.jsonBuilder()
                                .startObject()  // 相当于json开始的大括号“{”
                                .field("name", product.getName())
                                .field("desc", product.getDesc())
                                .field("price", product.getPrice())
                                .field("lv", product.getLv())
                                .field("type", product.getType())
                                .field("createtime", df.format(product.getCreatetime()))
                                .field("tags", product.getTags())
                                .endObject() // 相当于json结束的大括号“}”
                        ).get();
                /*
                以上代码相当于：
                put /product/_doc/{id}
                {
                  "name":"...",
                  "desc":"...",
                  ...
                  "tags":["","",""]
                }
                 */

                log.info("result:" + indexResponse.getResult());
            }

            // 2、查（Get）
            // 2.1 查询单个索引
            /*
             查询单个索引返回GetResponse，相当于把查询结果的json封装成GetResponse对象。
             {
                "_index" : "index17",
                "_type" : "_doc",
                "_id" : "1",
                "_version" : 1,
                "_seq_no" : 0,
                "_primary_term" : 1,
                "found" : true,
                "_source" : {
                  "name" : "小米手机",
                  "desc" : "手机中的战斗机",
                  "price" : 3999.0,
                  "lv" : "旗舰机",
                  "type" : "手机",
                  "createtime" : "2020-10-01",
                  "tags" : "\"性价比\",\"发烧\",\"不卡顿\""
                }
              }
             */
            GetResponse getResponse = client.prepareGet("index17", "_doc", 1 + "").get();// 相当于：get /product/_doc/1
            log.info("_index:" + getResponse.getIndex());
            log.info("_id:" + getResponse.getId());
            log.info("_version:" + getResponse.getVersion());
            log.info("_primary_term:" + getResponse.getPrimaryTerm());
            log.info("_source(String):" + getResponse.getSourceAsString());
            log.info("_source(Map):" + getResponse.getSourceAsMap());

            // 2.2 查询所有索引
            /*
             查询所有索引返回SearchResponse，相当于把查询结果的json封装成GetResponse对象。
             {
               "took" : 680,
               "timed_out" : false,
               "_shards" : {
                 "total" : 1,
                 "successful" : 1,
                 "skipped" : 0,
                 "failed" : 0
               },
               "hits" : {
                 "total" : {
                   "value" : 11,
                   "relation" : "eq"
                 },
                 "max_score" : 1.0,
                 "hits" : [
                   {
                     "_index" : "index17",
                     "_type" : "_doc",
                     "_id" : "1",
                     "_score" : 1.0,
                     "_source" : {
                       "name" : "小米手机",
                       "desc" : "手机中的战斗机",
                       "price" : 3999.0,
                       "lv" : "旗舰机",
                       "type" : "手机",
                       "createtime" : "2020-10-01",
                       "tags" : "\"性价比\",\"发烧\",\"不卡顿\""
                     }
                   },
                   {
                     "_index" : "index17",
                     "_type" : "_doc",
                     "_id" : "2",
                     "_score" : 1.0,
                     "_source" : {
                       "name" : "小米NFC手机",
                       "desc" : "支持全功能NFC，手机中的滑翔机",
                       "price" : 4999.0,
                       "lv" : "旗舰机",
                       "type" : "手机",
                       "createtime" : "2020-05-21",
                       "tags" : "\"性价比\",\"发烧\",\"公交卡\""
                     }
                   },
                   ...
                 ]
               }
             }
             */
            log.info("查询所有...");
            SearchResponse searchResponse = client.prepareSearch("index17").get();// 相当于：get /product/_search
            log.info("took:" + searchResponse.getTook());
            log.info("successfulShards:" + searchResponse.getSuccessfulShards());
            log.info("totalShards:" + searchResponse.getTotalShards());
            log.info("hits.maxScore:" + searchResponse.getHits().getMaxScore());
            log.info("hits.totalHits:" + searchResponse.getHits().getTotalHits());

            SearchHit[] searchHits = searchResponse.getHits().getHits();
            log.info("hits.hits length:" + searchHits.length);

            for (int i = 0; i < searchHits.length; i++) {
                log.info(searchHits[i].toString());
                log.info("index:" + searchHits[i].getIndex());
                log.info("id:" + searchHits[i].getId());
                log.info("source string:" + searchHits[i].getSourceAsString());
                log.info("source map:" + searchHits[i].getSourceAsMap());
                log.info("------------------------------------------------");
            }

            // 3、改（Update）
            // 修改返回UpdateResponse
            log.info("修改...");
            UpdateResponse updateResponse = client.prepareUpdate("index17", "_doc", 1 + "")
                    .setDoc(XContentFactory.jsonBuilder()
                            .startObject()  // 相当于：{
                            .field("name", "小米手机xiao mi shou ji")
                            .endObject()).get();
            /*
            相当于：
            post /product/_doc/1
            {
                "doc":{
                    "name":"小米手机xiao mi shou ji"
                }
            }
             */
            log.info(updateResponse.getResult().toString());

            // 删（Delete）
            // 删除返回DeleteResponse
            /*log.info("删除...");
            DeleteResponse deleteResponse = client.prepareDelete("index17", "_doc", 1 + "").get();
            log.info("delete result:" + deleteResponse.getResult().toString());*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    /**
     * get /index17/_search
     * {
     *   "from":0,
     *   "size":20,
     *   "query":{
     *     "match": {
     *       "name": "小米"
     *     }
     *   },
     *   "post_filter":{
     *     "range":{
     *       "price":{
     *         "gte":10,
     *         "lte":1000
     *       }
     *     }
     *   }
     * }
     *
     * 查询结果：
     * {
     *   "took" : 1,
     *   "timed_out" : false,
     *   "_shards" : {
     *     "total" : 1,
     *     "successful" : 1,
     *     "skipped" : 0,
     *     "failed" : 0
     *   },
     *   "hits" : {
     *     "total" : {
     *       "value" : 2,
     *       "relation" : "eq"
     *     },
     *     "max_score" : 1.0235538,
     *     "hits" : [
     *       {
     *         "_index" : "index17",
     *         "_type" : "_doc",
     *         "_id" : "4",
     *         "_score" : 1.0235538,
     *         "_source" : {
     *           "name" : "小米耳机",
     *           "desc" : "耳机中的黄焖鸡",
     *           "price" : 999.0,
     *           "lv" : "百元机",
     *           "type" : "耳机",
     *           "createtime" : "2020-06-23",
     *           "tags" : "\"降噪\",\"防水\",\"蓝牙\""
     *         }
     *       },
     *       {
     *         "_index" : "index17",
     *         "_type" : "_doc",
     *         "_id" : "5",
     *         "_score" : 0.31887552,
     *         "_source" : {
     *           "name" : "红米耳机",
     *           "desc" : "耳机中的肯德基",
     *           "price" : 399.0,
     *           "lv" : "百元机",
     *           "type" : "耳机",
     *           "createtime" : "2020-07-20",
     *           "tags" : "\"防火\",\"低音炮\",\"听声辨位\""
     *         }
     *       }
     *     ]
     *   }
     * }
     */
    @Test
    public void testMultiSearch() {
        try(TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);// 使用es的默认配置
        ) {
            client.addTransportAddress(new TransportAddress(InetAddress.getLocalHost(), 9300));
            SearchResponse searchResponse = client.prepareSearch("index17")
                    .setQuery(QueryBuilders.matchQuery("name", "小米"))
                    .setPostFilter(QueryBuilders.rangeQuery("price").gte(10).lte(1000))
                    .setFrom(0) // 设置分页
                    .setSize(20)
                    .get();

            log.info("took:" + searchResponse.getTook());
            log.info("timed_out:" + searchResponse.isTimedOut());
            log.info("_shards.total:" + searchResponse.getTotalShards());
            log.info("_shards.failed:" + searchResponse.getFailedShards());
            SearchHits hits = searchResponse.getHits();
            log.info("hits.max_score:" + hits.getMaxScore());
            log.info("hits.total.value:" + hits.getTotalHits().value);
            log.info("hits.total.relation:" + hits.getTotalHits().relation);
            SearchHit[] hitsArr = hits.getHits();
            log.info("hits.hits.length:" + hitsArr.length);
            for(SearchHit hit : hitsArr) {
                log.info("-------------");
                log.info("_id:" + hit.getId());
                log.info("_index:" + hit.getIndex());
                log.info("_score:" + hit.getScore());
                log.info("_source(String):" + hit.getSourceAsString());
                log.info("_source(Map):" + hit.getSourceAsMap());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
