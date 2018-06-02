/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.elasticsearch.container;

import com.ff.filip.elasticsearch.administration.ElasticClient;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 *
 * @author filip
 */
@Path("/elastic/proba")
public class JavaMain {

//    Client client;
    @Path("/get")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String get() throws IOException {
//        client = ElasticClient.getInstance().getClient();

        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        IndexResponse response = client.prepareIndex("employee", "id", "1")
                .setSource(jsonBuilder()
                        .startObject().field("name", "Filip")
                        .field("salary", 1000)
                        .field("teamName", "IT")
                        .endObject()).get();

        return response.getResult().toString();
    }
}
