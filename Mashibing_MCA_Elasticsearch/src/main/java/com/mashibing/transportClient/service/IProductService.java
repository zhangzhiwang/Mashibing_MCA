package com.mashibing.transportClient.service;

import com.mashibing.transportClient.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> queryAll();
}
