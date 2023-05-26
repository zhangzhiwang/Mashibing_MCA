package com.mashibing.restClient.controller;

import com.mashibing.restClient.util.ESClientUtil;
import com.mashibing.transportClient.entity.Product;
import com.mashibing.transportClient.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("test")
public class ES_RESTClientController {
    private static final RestHighLevelClient CLIENT = ESClientUtil.getESClientInstance();

    @Autowired
    private IProductService productService;

    @RequestMapping("init")// @RequestMapping如果不指定请求方法，那么默认所有的请求方法都可以接受
    public String init() {
        try {
            // 查询索引是不是存在
            GetIndexRequest getIndexRequest = new GetIndexRequest("index17_8");
            boolean exists = CLIENT.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
            if(exists) {
                DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("index17_8");
                AcknowledgedResponse acknowledgedResponse = CLIENT.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
                if(acknowledgedResponse.isAcknowledged()) {
                    log.info("索引已删除");
                }
            }

            CreateIndexRequest createIndexRequest = new CreateIndexRequest("index17_8");
//            createIndexRequest.mapping();

            List<Product> productList = productService.queryAll();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "ok";
    }
}
