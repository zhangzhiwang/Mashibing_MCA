package com.mashibing.transportClient.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Product {
   private Long id;
   private String name;
   private String desc;
   private Double price;
   private String lv;
   private String type;
   private Timestamp createtime;
   private String tags;
}
