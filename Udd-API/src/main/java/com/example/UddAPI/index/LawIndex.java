package com.example.UddAPI.index;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "law_index")
//@Setting(settingPath = "/srpski-analizator-config.json")
public class LawIndex {
    @Id
    private String id;
    @Field(type = FieldType.Text, store = true, name = "content_sr")
    private String content;
    @Field(type = FieldType.Text, store = true, name = "server_ime_fajla", index = false)
    private String serverFilename;
    @Field(type = FieldType.Integer, store = true, name = "id_baze_podataka", index = false)
    private Integer idDB;
}