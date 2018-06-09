/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.elasticsearch.service.search;

import com.ff.filip.domen.Ispit;
import com.ff.filip.domen.Mesto;
import com.ff.filip.domen.Student;
import com.ff.filip.elasticsearch.administration.ESIndex;
import com.ff.filip.elasticsearch.administration.ElasticClient;
import com.ff.filip.jsf.mb.MBIspit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

/**
 *
 * @author filip
 */
public class ElasticsearchServiceSearch {

    private static ElasticsearchServiceSearch elasticsearchServiceSearch;

    public static ElasticsearchServiceSearch getInstance() {
        if (elasticsearchServiceSearch == null) {
            elasticsearchServiceSearch = new ElasticsearchServiceSearch();
        }
        return elasticsearchServiceSearch;
    }

    public SearchResponse getSearchResponseIspit(String wildCardQuery) {
        QueryBuilder qb = QueryBuilders.queryStringQuery(wildCardQuery + "*")
                .defaultField("NazivIspita")
                .defaultOperator(Operator.AND);

        SearchResponse searchResponse = ElasticClient.getInstance().getClient()
                .prepareSearch(ESIndex.ISPIT.name().toLowerCase())
                .setTypes(ESIndex.ISPIT.getTypes()[0])
                .setQuery(qb)
                .execute().actionGet();

        return searchResponse;
    }

    public Ispit getIspit(SearchHit hit) {
        Ispit temporary = null;
        Map<String, Object> map = hit.getSourceAsMap();
        int sifraIspita = Integer.parseInt(map.get("SifraIspita").toString());
        String nazivIspita = map.get("NazivIspita").toString();
        temporary = new Ispit(sifraIspita, nazivIspita);

        return temporary;
    }

    public SearchResponse getSearchResponseStudent(String wildCardQuery) {
        QueryBuilder qb = QueryBuilders.queryStringQuery(wildCardQuery + "*")
                .defaultField("Ime")
                .defaultField("Prezime")
                .defaultOperator(Operator.OR);

        SearchResponse searchResponse = ElasticClient.getInstance().getClient()
                .prepareSearch(ESIndex.STUDENT.name().toLowerCase())
                .setTypes(ESIndex.STUDENT.getTypes()[0])
                .setQuery(qb)
                .execute().actionGet();

        return searchResponse;
    }

    public SearchResponse getSearchResponseStudentBoolQuery(String wildCardQuery, Mesto mesto) {
        BoolQueryBuilder booleanQuery = QueryBuilders.boolQuery();

        QueryBuilder qb = QueryBuilders.queryStringQuery(wildCardQuery + "*")
                .defaultField("Ime")
                .defaultField("Prezime")
                .defaultOperator(Operator.OR);

        QueryBuilder mestoIdQuery = QueryBuilders.termQuery("mesto.Ptt", mesto.getPtt());
        booleanQuery.must(qb);
        booleanQuery.must(mestoIdQuery);

        SearchResponse searchResponse = ElasticClient.getInstance().getClient()
                .prepareSearch(ESIndex.STUDENT.name().toLowerCase())
                .setTypes(ESIndex.STUDENT.getTypes()[0])
                .setQuery(booleanQuery)
                .execute().actionGet();

        return searchResponse;
    }

    public Student getStudent(SearchHit hit) {
        Student temporary = null;
        Map<String, Object> map = hit.getSourceAsMap();
        Map<String, Object> newMap = new HashMap<>();

        String brIndeksa = map.get("BrInd").toString();
        String ime = map.get("Ime").toString();
        String prezime = map.get("Prezime").toString();

        Map<String, Object> innerObject = (Map<String, Object>) map.get("mesto");
        int pttMesto = Integer.parseInt(innerObject.get("Ptt").toString());
        String nazivMestaM = innerObject.get("NazivMesta").toString();
        Mesto mesto = new Mesto(pttMesto, nazivMestaM);

        temporary = new Student(brIndeksa, ime, prezime, mesto);
        
        return temporary;
    }

}
