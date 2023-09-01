package com.mashibing.springDataES.config;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

//@Configuration
public class ESConfig extends AbstractElasticsearchConfiguration {
    @Override
//    @Bean
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo("127.0.0.1:9200").build();
        RestHighLevelClient highLevelClient = RestClients.create(clientConfiguration).rest();
        return highLevelClient;
    }
}
