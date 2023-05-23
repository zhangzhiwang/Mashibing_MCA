package com.mashibing;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan(basePackages = {"com.mashibing.transportClient.mapper"})
@Slf4j
public class HighLevelClientTest {
    @Test
    public void createIndex() {
        // step 1：创建RestHighLevelClient
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder("http://localhost:9200"));// 外面创建的是HighLevelClient，里面传入的RestClient对象是Low-Level的，负责建立连接
    }
}
