package com.mashibing;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
public class SpringDataESTest {
    @Autowired
    private RestHighLevelClient highLevelClient;

    @Test
    public void testCreateIndex() throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", 18);

        IndexRequest indexRequest = new IndexRequest("index25")
                .id("1")
                .source(map)
                .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);
        highLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("ok");
    }
}
