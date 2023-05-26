package com.mashibing;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan(basePackages = {"com.mashibing.transportClient.mapper"})
@Slf4j
public class HighLevelClientTest {
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
}
