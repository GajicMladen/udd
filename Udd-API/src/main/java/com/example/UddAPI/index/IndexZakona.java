package com.example.UddAPI.index;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "index_zakona")
//@Setting(settingPath = "/srpski-analizator-config.json")
public class IndexZakona {
    @Id
    private String id;
    @Field(type = FieldType.Text, store = true, name = "content_sr")
    private String sadrzajSr;
    @Field(type = FieldType.Text, store = true, name = "server_ime_fajla", index = false)
    private String serverImeFajla;
    @Field(type = FieldType.Integer, store = true, name = "id_baze_podataka", index = false)
    private Integer idBazePodataka;
}