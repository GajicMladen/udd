package com.example.UddAPI.model;

public class ZahtevZaIndexiranjeUgovora {

    private String ime;
    private String prezime;
    private String imeVlade;
    private String NivoUprave;
    private String Adresa;

    private String Sadrzaj;

    public String getSadrzaj() {
        return Sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        Sadrzaj = sadrzaj;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getImeVlade() {
        return imeVlade;
    }

    public void setImeVlade(String imeVlade) {
        this.imeVlade = imeVlade;
    }

    public String getNivoUprave() {
        return NivoUprave;
    }

    public void setNivoUprave(String nivoUprave) {
        NivoUprave = nivoUprave;
    }

    public String getAdresa() {
        return Adresa;
    }

    public void setAdresa(String adresa) {
        Adresa = adresa;
    }
}
