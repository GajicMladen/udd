package com.example.UddAPI.index;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "index_ugovora")
@Setting(settingPath = "/srpski-analizator-config.json")
public class ContractIndex {
    @Id
    private String id;

    @Field(type = FieldType.Text, store = true, name = "ime_p_u")
    private String nameSignGov;
    @Field(type = FieldType.Text, store = true, name = "prezime_p_u")
    private String lastnameSignGov;
    @Field(type = FieldType.Text, store = true, name = "ime_p_a")
    private String nameSignAgency;
    @Field(type = FieldType.Text, store = true, name = "prezime_p_a")
    private String lastnameSignAgency;
    @Field(type = FieldType.Text, store = true, name = "naziv_uprave")
    private String govName;
    @Field(type = FieldType.Text, store = true, name = "nivo_uprave")
    private String govLevel;
    @Field(type = FieldType.Text, store = true, name = "adresa_u")
    private String addressGov;

    @Field(type = FieldType.Text, store = true, name = "adresa_a")
    private String addressAgency;

    @Field(type = FieldType.Text, store = true, name = "email_u")
    private String emailGov;

    @Field(type = FieldType.Text, store = true, name = "email_a")
    private String emailAgency;
    @Field(type = FieldType.Text, store = true, name = "title")
    private String title;
    @Field(type = FieldType.Text, store = true, name = "sadrzaj_sr",analyzer = "serbian_simple", searchAnalyzer = "serbian_simple")
    private String content;

    @GeoPointField
    @Field(store = true, name = "location")
    private GeoPoint location;

    @Field(type = FieldType.Text, store = true, name = "server_ime_fajla", index = false)
    private String serverFilename;

    @Field(type = FieldType.Integer, store = true, name = "id_baze_podataka", index = false)
    private Integer idBP;

    private String highlight;

    public ContractIndex(String nameSignGov, String lastnameSignGov, String nameSignAgency, String lastnameSignAgency, String govName, String govLevel, String addressGov, String addressAgency, String emailGov, String emailAgency, String title, String content, String serverFilename, Integer idBP,GeoPoint geoPoint) {
        this.nameSignGov = nameSignGov;
        this.lastnameSignGov = lastnameSignGov;
        this.nameSignAgency = nameSignAgency;
        this.lastnameSignAgency = lastnameSignAgency;
        this.govName = govName;
        this.govLevel = govLevel;
        this.addressGov = addressGov;
        this.addressAgency = addressAgency;
        this.emailGov = emailGov;
        this.emailAgency = emailAgency;
        this.title = title;
        this.content = content;
        this.serverFilename = serverFilename;
        this.idBP = idBP;
        this.location = geoPoint;

    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }
}
