package com.onlinebookstore.onlinebookstore.entities.rest;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private Long userId;
    private List<String> isbnList;



}
