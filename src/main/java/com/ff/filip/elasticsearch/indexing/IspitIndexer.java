/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.elasticsearch.indexing;

import com.ff.filip.domen.Ispit;
import com.ff.filip.elasticsearch.administration.ESIndex;
import com.ff.filip.elasticsearch.administration.ElasticClient;
import java.io.IOException;
import java.util.List;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

/**
 *
 * @author filip
 */
public class IspitIndexer {

    public void indexStudents(List<Ispit> ispits) {
        System.out.println("Indexing ispits");
        for (Ispit ispit : ispits) {
            indexIspit(ispit);
        }
    }

    private void indexIspit(Ispit ispit) {
        System.out.println("Indexing ispit " + ispit);

        try {
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();

            builder.field("SifraIspita", ispit.getSifraIspita());
            builder.field("NazivIspita", ispit.getNazivIspita());

            builder.endObject();

            @SuppressWarnings("unused")
            IndexResponse response = ElasticClient.getInstance().getClient()
                    .prepareIndex(
                            ESIndex.ISPIT.name().toLowerCase(),
                            ESIndex.ISPIT.getTypes()[0],
                            String.valueOf(ispit.getSifraIspita()))
                    .setSource(builder)
                    .get();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
