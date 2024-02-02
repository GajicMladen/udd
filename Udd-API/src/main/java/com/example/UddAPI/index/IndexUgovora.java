package com.example.UddAPI.index;

import com.example.UddAPI.model.ZahtevZaIndexiranjeUgovora;
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
//@Setting(settingPath = "/srpski-analizator-config.json")
public class IndexUgovora {
    @Id
    private String id;
    @Field(type = FieldType.Text, store = true, name = "ime")
    private String ime;
    @Field(type = FieldType.Text, store = true, name = "prezime")
    private String prezime;
    @Field(type = FieldType.Text, store = true, name = "ime_vlade")
    private String imeVlade;
    @Field(type = FieldType.Text, store = true, name = "nivo_uprave")
    private String nivoUprave;
    @Field(type = FieldType.Text, store = true, name = "sadrzaj_sr")
    private String sadrzajSr;
    @Field(type = FieldType.Text, store = true, name = "adresa")
    private String adresa;
    @GeoPointField
    @Field(store = true, name = "lokacija")
    private GeoPoint lokacija;
    @Field(type = FieldType.Text, store = true, name = "server_ime_fajla", index = false)
    private String serverImeFajla;
    @Field(type = FieldType.Integer, store = true, name = "id_baze_podataka", index = false)
    private Integer idBazePodataka;
    public IndexUgovora(ZahtevZaIndexiranjeUgovora zahtev) {
        this.ime = zahtev.getIme();
        this.prezime = zahtev.getPrezime();
        this.imeVlade = zahtev.getImeVlade();
        this.nivoUprave = zahtev.getNivoUprave();
        this.adresa = zahtev.getAdresa();
        this.sadrzajSr = zahtev.getSadrzaj();

    }
}
