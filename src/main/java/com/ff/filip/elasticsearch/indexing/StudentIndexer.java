/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.elasticsearch.indexing;

import com.ff.filip.domen.Student;
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
public class StudentIndexer {

    public void indexStudents(List<Student> students) {
        System.out.println("Indexing students");
        for (Student student : students) {
            indexStudent(student);
        }
    }

    private void indexStudent(Student student) {
        System.out.println("Indexing student " + student);

        try {
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();

            builder.field("BrInd", student.getBrInd());
            builder.field("Ime", student.getIme() != null ? student.getIme() : "");
            builder.field("Prezime", student.getPrezime() != null ? student.getPrezime() : "");
            builder.field("Mesto", student.getMesto());

            builder.endObject();

            @SuppressWarnings("unused")
            IndexResponse response = ElasticClient.getInstance().getClient()
                    .prepareIndex(
                            ESIndex.STUDENT.name().toLowerCase(),
                            ESIndex.STUDENT.getTypes()[0],
                            String.valueOf(student.getBrInd()))
                    .setSource(builder)
                    .get();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
