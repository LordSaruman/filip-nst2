/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.elasticsearch.administration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 *
 * @author filip
 */
public class ElasticClient {

    private static ElasticClient elasticClient;
    private Client client;
    private final String clusterName = "elasticsearch";
    private final String ipAddress = "127.0.0.1";
    private final int port = 9200;

    public static ElasticClient getInstance() {
        if (elasticClient == null) {
            elasticClient = new ElasticClient();
        }
        return elasticClient;
    }

    public Client getClient() {
        return client;
    }

    private ElasticClient() {

        Settings settings = Settings.builder().put("cluster.name", clusterName).build();
        try {
            TransportClient transportClient = new PreBuiltTransportClient(settings);
            transportClient.addTransportAddress(new TransportAddress(InetAddress.getByName(ipAddress), port));
            client = transportClient;
        } catch (UnknownHostException e) {
            return;
        }
    }
}
