/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.elasticsearch.service.search;

/**
 *
 * @author filip
 */
public class ElasticsearchServiceSearch {
    
    private static ElasticsearchServiceSearch elasticsearchServiceSearch;
    
    public static ElasticsearchServiceSearch getInstance(){
        if (elasticsearchServiceSearch == null) {
            elasticsearchServiceSearch = new ElasticsearchServiceSearch();
        }
        return elasticsearchServiceSearch;
    }
}
