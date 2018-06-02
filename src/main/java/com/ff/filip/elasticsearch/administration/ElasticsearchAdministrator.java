/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.elasticsearch.administration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.io.Streams;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.IndexNotFoundException;

/**
 *
 * @author filip
 */
public class ElasticsearchAdministrator {

    public void createAllIndexes() {
        for (ESIndex index : ESIndex.values()) {
            createIndex(index.toString(), index.getTypes());
        }
    }

    private void createIndex(String indexName, String... types) {
        indexName = indexName.toLowerCase();

        Client client = (Client) ElasticClient.getInstance().getClient();

        boolean exists = client.admin().indices().prepareExists(indexName)
                .execute().actionGet().isExists();

        if (!exists) {
            client.admin().indices().prepareCreate(indexName).get();

            for (String type : types) {
                try {
                    String mappingContent = copyToStringFromClasspath("/com/ff/filip/elasticsearch/mappings/" + type.toLowerCase() + "-mapping.json");

                    client.admin().indices().preparePutMapping(indexName.toLowerCase())
                            .setType(type)
                            .setSource(mappingContent, XContentType.JSON)
                            .get();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        }
    }

    private static String copyToStringFromClasspath(String path) throws IOException {
        InputStream is = Streams.class.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException("Resource [" + path + "] not found in classpath");
        }
        return Streams.copyToString(new InputStreamReader(is, Charset.defaultCharset()));
    }

    public boolean deleteAllIndexes() {
        return deleteIndexesByName(ESIndex.values());
    }

    public boolean deleteIndexesByName(ESIndex[] indexes) {
        try {
            Client client = (Client) ElasticClient.getInstance().getClient();
            String[] indexArray = Arrays.stream(indexes).map(i -> i.name().toLowerCase()).toArray(String[]::new);
            DeleteIndexResponse response = client.admin().indices().delete(new DeleteIndexRequest(indexArray)).actionGet();

            return response.isAcknowledged();
        } catch (IndexNotFoundException e) {
            System.err.println("Index does not exist so it can't be deleted");
            return false;
        }
    }
}
