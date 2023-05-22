package com.mashibing.transportClient.controller;

import com.mashibing.transportClient.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("queryAll")
    private void queryAll() {
        productService.queryAll();
    }
}
