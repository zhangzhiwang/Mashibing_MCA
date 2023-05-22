package com.mashibing.transportClient.service.impl;

import com.mashibing.transportClient.entity.Product;
import com.mashibing.transportClient.mapper.ProductMapper;
import com.mashibing.transportClient.service.IProductService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> queryAll() {
        List<Product> productList = productMapper.queryAll();
        log.info("productList:" + productList);
        return productList;
    }
}
