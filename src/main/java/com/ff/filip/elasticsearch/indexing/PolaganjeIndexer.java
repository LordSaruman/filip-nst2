/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.elasticsearch.indexing;

import com.ff.filip.domen.Polaganje;
import com.ff.filip.elasticsearch.administration.ESIndex;
import com.ff.filip.elasticsearch.administration.ElasticClient;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
/**
 *
 * @author filip
 */
public class PolaganjeIndexer {

    public void indexPolaganjeList(List<Polaganje> listPolaganje) {
        System.out.println("Indexing listPolaganje");
        for (Polaganje polaganje : listPolaganje) {
            indexPolaganje(polaganje);
        }
    }

    private void indexPolaganje(Polaganje polaganje) {
        System.out.println("Indexing polaganje " + polaganje);

        try {
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();

            builder.field("PolaganjeId", polaganje.getIdPolaganje());
//            builder.field("BrInd", polaganje.getStudent().getBrInd());
//            builder.field("SifraIspitnogRoka", polaganje.getIspitniRok().getSifraIspitnogRoka());
//            builder.field("SifraIspita", polaganje.getIspit().getSifraIspita());
//            builder.field("Ocena", polaganje.getOcena());
//            builder.field("Datum", polaganje.getDatum() != null ? polaganje.getDatum() : new Date());
            builder.endObject();

            @SuppressWarnings("unused")
            IndexResponse response = ElasticClient.getInstance().getClient()
                    .prepareIndex(
                            ESIndex.POLAGANJE.name().toLowerCase(),
                            ESIndex.POLAGANJE.getTypes()[0],
                            String.valueOf(polaganje.getIdPolaganje()))
                    .setSource(builder)
                    .get();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
