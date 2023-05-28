package com.mashibing;

import com.alibaba.fastjson.JSONObject;
import com.mashibing.restClient.util.RestHighLevelClientUtil;
import com.mashibing.transportClient.entity.Product;
import com.mashibing.transportClient.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.*;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.client.sniff.SniffOnFailureListener;
import org.elasticsearch.client.sniff.Sniffer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentFactory;
import org.elasticsearch.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
@MapperScan(basePackages = {"com.mashibing.transportClient.mapper"})
@Slf4j
public class HighLevelClientTest {
    @Autowired
    private IProductService productService;

    //-----------------对索引的操作：增、删、查---------------------
    @Test
    public void testCreateIndex() {
        RestHighLevelClient client = null;
        try {
            // step 1：创建RestHighLevelClient
            client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("localhost", 9200, "http")));// 外面创建的是HighLevelClient，里面传入的RestClient对象是Low-Level的，负责建立连接

            // step 2：创建request对象
            // 创建索引要使用CreateIndexRequest
            CreateIndexRequest request = new CreateIndexRequest("index17_2");// 传入索引名称

            // step 3：组装数据
            // 添加设置（可选）
            request.settings(Settings.builder()
                    .put("index.number_of_shards", 3) // 设置分片数
                    .put("index.number_of_replicas", 0)); // 设置副本数

            // 3.1：构建数据方式1：使用字符串（json格式）构建数据
//            String jsonParam =  "{\n" +
//                    "    \"properties\": {\n" +
//                    "      \"message\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"analyzer\": \"ik_smart\",\n" +
//                    "        \"fields\": {\n" +
//                    "          \"my_sub_field\": {\n" +
//                    "            \"type\": \"keyword\",\n" +
//                    "            \"ignore_above\": 256\n" +
//                    "          }\n" +
//                    "        }\n" +
//                    "      }\n" +
//                    "    }\n" +
//                    "  }";
//            request.mapping(jsonParam, XContentType.JSON);// 创建索引时，调用mapping方法就相当于在json里面写了“mappings”属性，所以字符串传进来的是从mappings的下一级开始的

            // 3.2：构建数据方式2：使用Map构建数据
            /*Map<String, Object> mappings = new HashMap<>();
            Map<String, Object> properties = new HashMap<>();
            Map<String, Object> message = new HashMap<>();
            message.put("type", "text");
            message.put("analyzer", "ik_smart");
            Map<String, Object> fields = new HashMap<>();
            Map<String, Object> mySubField = new HashMap<>();
            mySubField.put("type", "keyword");
            mySubField.put("ignore_above", 256);
            fields.put("mySubField", mySubField);
            message.put("fields", fields);

            properties.put("message", message);
            mappings.put("properties", properties);

            request.mapping(mappings);*/

            // 3.3：构建数据方式2：使用XContentBuilder构建数据
            XContentBuilder jsonBuilder = XContentFactory.jsonBuilder();
            jsonBuilder.startObject();// 相当于：{
            jsonBuilder.startObject("properties");// 相当于："properties":{
            jsonBuilder.startObject("message");// 相当于："message":{
            jsonBuilder.field("type", "text");// 相当于："type": "text",
            jsonBuilder.field("analyzer", "ik_smart");// 相当于："analyzer": "ik_smart",
            jsonBuilder.startObject("fields");// 相当于："fields":{
            jsonBuilder.startObject("my_sub_field");// 相当于："my_sub_field":{
            jsonBuilder.field("type", "keyword");// 相当于："type": "keyword",
            jsonBuilder.field("ignore_above", 128);// 相当于："ignore_above": 128 ,
            jsonBuilder.endObject();// 相当于：}
            jsonBuilder.endObject();// 相当于：}
            jsonBuilder.endObject();// 相当于：}
            jsonBuilder.endObject();// 相当于：}
            jsonBuilder.endObject();// 相当于：}
            // 这里要注意：一个startObject()和一个endObject()是一一对应的，就和json的大括号一样

            request.mapping(jsonBuilder);
            // step 4：发送数据
            // 4.1：同步发送数据
            // 创建返回的是CreateIndexResponse
            // client先调用indices()方法，返回的是IndicesClient对象，IndicesClient封装了对索引的操作（单词indices就是index的复数形式）
//            CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
            //            boolean acknowledged = createIndexResponse.isAcknowledged();// 是否创建成功
//            log.info("是否创建成功：" + acknowledged);

            // 4.2：异步发送数据
            client.indices().createAsync(request, RequestOptions.DEFAULT, new ActionListener<CreateIndexResponse>() {
                @Override
                public void onResponse(CreateIndexResponse createIndexResponse) {
                    log.info("666");
                }

                @Override
                public void onFailure(Exception e) {
                    log.info("失败了");
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (client != null) {
                    Thread.sleep(2000);
                    client.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void tesGetIndex() {
        try (RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));) {
            // 查询索引要使用GetIndexRequest
            GetIndexRequest request = new GetIndexRequest("index17*");// 相当于：get /index*，查询所有以index17开头的索引
            // 查询返回的是GetIndexResponse
            GetIndexResponse getIndexResponse = client.indices().get(request, RequestOptions.DEFAULT);
            log.info(getIndexResponse.toString());
            /*
            通过debug看GetIndexResponse的结构可以看出来，GetIndexResponse对象并不是对查询结果的json进行原封不动地封装，是按照属性汇总之后的封装，
            汇总之后是按照key-value的形式组织数据的，debug看一下结构就知道在说什么了。
             */
            String[] indices = getIndexResponse.getIndices();
            for (String s : indices) {
                log.info(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteIndex() {
        try (RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));) {
            // 删除要使用DeleteIndexRequest
            DeleteIndexRequest request = new DeleteIndexRequest("index17_3");
            AcknowledgedResponse acknowledgedResponse = client.indices().delete(request, RequestOptions.DEFAULT);

            log.info("acknowledgedResponse : " + acknowledgedResponse.isAcknowledged());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //-----------------对索引文档的操作：增、删、改、查---------------------
    @Test
    public void testInsertData() {
        try (RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));) {
            // 从数据库查询所有数据
            List<Product> productList = productService.queryAll();
            // 插入数据用IndexRequest
            IndexRequest request = new IndexRequest("index17_4");// 如果索引不存在则自动创建

            for (Product product : productList) {
                request.id(product.getId() + "");
                request.source(JSONObject.toJSONString(product), XContentType.JSON);// TODO 等有时间研究下为什么不建议使用阿里的fastjson
                // 插入数据返回IndexResponse
                IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);// 不需要先调用indices()方法返回IndicesClient对象，因为不是对索引的操作而是对文档数据的操作，所以直接调用index()方法来插入数据
                log.info("indexResponse : " + indexResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBulkInsertData() {
        try (RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));) {
            // 批量插入文档数据使用BulkRequest，其实BulkRequest就是往里面添加（add）多个IndexRequest，每一个文档数据都放在一个IndexRequest里面
            BulkRequest bulkRequest = new BulkRequest("index17_5");// 往index17_5下面批量插入文档数据

            for (int i = 0; i < 10; i++) {// 批量向index17_5索引下面插入10条数据
                Product product = new Product();
                product.setId((long) i);
                product.setName("xiaomi_" + i);
                product.setDesc("xiaomi desc_" + i);

                IndexRequest indexRequest = new IndexRequest();
                indexRequest.id(i + "");
                indexRequest.source(JSONObject.toJSONString(product), XContentType.JSON);
                bulkRequest.add(indexRequest);
            }

            // 批量插入返回BulkResponse
            BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);// 调用client的bulk()方法
            BulkItemResponse[] items = bulkResponse.getItems();
            for (BulkItemResponse item : items) {
                log.info("isFailed : " + item.isFailed());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetById() {
        try (RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));) {
            // 查询单个文档使用GetRequest
            GetRequest getRequest = new GetRequest("index17_5", "1");// 查询index17_5 id为1的文档

            String[] includes = {"name", "price"};// 只查询name和price两个字段
            String[] excludes = {"price"};// 不查询price字段，当includes和excludes冲突时以excludes为准
            FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
            getRequest.fetchSourceContext(fetchSourceContext);
            // 查询单个文档返回GetResponse
            GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
            log.info("response : " + response);
            log.info("response.source : " + response.getSourceAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMultiGetById() {
        try (RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")))) {
            // 批量查询（根据多个id查询，相当于是多个get）
            MultiGetRequest multiGetRequest = new MultiGetRequest();
            // 写法1：
            multiGetRequest.add("index17_4", "1");
            multiGetRequest.add("index17_5", "1");// 可以查询多个索引的文档
            // 写法2：
            multiGetRequest.add(new MultiGetRequest.Item("index17_4", "2"));
            multiGetRequest.add(new MultiGetRequest.Item("index17", "3"));

            // 批量查询返回MultiGetResponse
            MultiGetResponse mget = client.mget(multiGetRequest, RequestOptions.DEFAULT);
            MultiGetItemResponse[] mgetResponses = mget.getResponses();
            for (MultiGetItemResponse itemResponse : mgetResponses) {
                log.info("itemResponse : " + itemResponse.getResponse().getSourceAsString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateByQuery() {
        try (RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));) {
            // 查询完更新
            UpdateByQueryRequest updateByQueryRequest = new UpdateByQueryRequest("index17_4");// 入参是索引名，对索引index17_4进行update by query操作
            updateByQueryRequest.setQuery(QueryBuilders.matchQuery("name", "NFC"));// 设置查询条件，查询名称包含NFC的
            updateByQueryRequest.setScript(new Script(ScriptType.INLINE, "painless", "ctx._source.desc+='aaa';", new HashMap<>()));// 使用脚本

            // 查询更新返回BulkByScrollResponse
            BulkByScrollResponse bulkByScrollResponse = client.updateByQuery(updateByQueryRequest, RequestOptions.DEFAULT);
            log.info("bulkByScrollResponse : " + bulkByScrollResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteById() {
        try (RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));) {
            DeleteRequest deleteRequest = new DeleteRequest("index17_4", "1");// 删除index17_4 id为1的文档
            DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
            log.info("deleteResponse : " + deleteResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRestHighLevelClientUtil() {
        try (RestHighLevelClient client = RestHighLevelClientUtil.getRestHighLevelClient();) {
            DeleteRequest deleteRequest = new DeleteRequest("index1", "1");// 删除index17_4 id为1的文档
            DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
            log.info("deleteResponse : " + deleteResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 嗅探机制——给嗅探器一个节点，它能把集群中的其它节点自动识别出来
     */
    @Test
    public void testSniffer() {
        RestHighLevelClient highLevelClient = null;
        Sniffer sniffer = null;
        try {
            //------------------low-level client设置开始-----------------
            SniffOnFailureListener sniffOnFailureListener = new SniffOnFailureListener();
            RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("localhost", 9201, "http"))
                    .setFailureListener(sniffOnFailureListener);// 设置连接失败监听器，注意：当这里的RestClientBuilder设置SniffOnFailureListener监听器的时候，下面的Sniffer设置setSniffAfterFailureDelayMillis才起作用。
            RestClient restClient = restClientBuilder.build();
            // 将low-level clientBuilder放到high-level client里面
            highLevelClient = new RestHighLevelClient(restClientBuilder);

            // 当使用https协议连接集群的时候要使用NodesSniffer，使用http连接集群无需此设置
//            NodesSniffer nodesSniffer = new ElasticsearchNodesSniffer(
//                    restClient, ElasticsearchNodesSniffer.DEFAULT_SNIFF_REQUEST_TIMEOUT, ElasticsearchNodesSniffer.Scheme.HTTPS);

            // sniffer
            sniffer = Sniffer.builder(highLevelClient.getLowLevelClient())
                    .setSniffIntervalMillis(1000)// 设置正常情况下两次嗅探之间的间隔，或者说每隔多久嗅探一次
                    .setSniffAfterFailureDelayMillis(30000)// 在嗅探失败的时候的重试间隔，注意：上面RestClientBuilder必须设置SniffOnFailureListener监听这里才起作用，否则不起作用
//                    .setNodesSniffer(nodesSniffer)// 使用https连接集群的时候才需要设置
                    .build();

            // 将嗅探器设置到监听器中
            sniffOnFailureListener.setSniffer(sniffer);
            //------------------low-level client设置结束-----------------

            Thread.sleep(10000);
            DeleteRequest deleteRequest = new DeleteRequest("index1", "1");// 删除index17_4 id为1的文档
            DeleteResponse deleteResponse = highLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            log.info("deleteResponse : " + deleteResponse);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sniffer != null) {// 要注意关闭顺序：先关闭sniffer再关闭highLevelClient
                sniffer.close();
            }
            if(highLevelClient != null) {
                try {
                    highLevelClient.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testSniffer2() {
        RestHighLevelClient highLevelClient = null;
        Sniffer sniffer = null;
        try {
            //------------------low-level client设置开始-----------------
            SniffOnFailureListener sniffOnFailureListener = new SniffOnFailureListener();
            RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("localhost", 9201, "http"))
                    .setFailureListener(sniffOnFailureListener);// 设置连接失败监听器，注意：当这里的RestClientBuilder设置SniffOnFailureListener监听器的时候，下面的Sniffer设置setSniffAfterFailureDelayMillis才起作用。
            RestClient restClient = restClientBuilder.build();

            // 当使用https协议连接集群的时候要使用NodesSniffer，使用http连接集群无需此设置
//            NodesSniffer nodesSniffer = new ElasticsearchNodesSniffer(
//                    restClient, ElasticsearchNodesSniffer.DEFAULT_SNIFF_REQUEST_TIMEOUT, ElasticsearchNodesSniffer.Scheme.HTTPS);

            // sniffer
            sniffer = Sniffer.builder(restClient)
                    .setSniffIntervalMillis(1000)// 设置正常情况下两次嗅探之间的间隔，或者说每隔多久嗅探一次
                    .setSniffAfterFailureDelayMillis(30000)// 在嗅探失败的时候的重试间隔，注意：上面RestClientBuilder必须设置SniffOnFailureListener监听这里才起作用，否则不起作用
//                    .setNodesSniffer(nodesSniffer)// 使用https连接集群的时候才需要设置
                    .build();

            // 将嗅探器设置到监听器中
            sniffOnFailureListener.setSniffer(sniffer);
            //------------------low-level client设置结束-----------------

            // 将low-level clientBuilder放到high-level client里面
            highLevelClient = new RestHighLevelClient(restClientBuilder);

            List<Node> nodeList = highLevelClient.getLowLevelClient().getNodes();
            for(Node node : nodeList) {
                log.info("node : " + node);
            }

            Thread.sleep(7000);
            log.info("休眠后...");
            nodeList = highLevelClient.getLowLevelClient().getNodes();
            for(Node node : nodeList) {
                log.info("node : " + node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sniffer != null) {// 要注意关闭顺序：先关闭sniffer再关闭highLevelClient
                sniffer.close();
            }
            if(highLevelClient != null) {
                try {
                    highLevelClient.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
