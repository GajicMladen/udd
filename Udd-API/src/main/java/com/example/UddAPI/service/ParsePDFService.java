package com.example.UddAPI.service;

import co.elastic.clients.elasticsearch._types.GeoLocation;
import com.example.UddAPI.dto.LocationDTO;
import com.example.UddAPI.index.ContractIndex;
import com.example.UddAPI.index.LawIndex;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ParsePDFService {

    @Autowired
    private LocationService locationService;

    public String extractContent(final MultipartFile multipartFile) {
        String text;

        try (final PDDocument document = PDDocument.load(multipartFile.getInputStream())) {
            final PDFTextStripper pdfStripper = new PDFTextStripper();
            text = pdfStripper.getText(document);
        } catch (final Exception ex) {
            log.error("Error parsing PDF", ex);
            text = "Error parsing PDF";
        }

        return text;
    }

    public ContractIndex parseIndexUgovora(String textUgovora, String nazivFajla){

        String govName = extractFiled("Naziv-u",textUgovora);
        String govLevel = extractFiled("Nivo-u",textUgovora);
        String addressAgency = extractFiled("Adresa-a",textUgovora);
        String addressGov = extractFiled("Adresa-u",textUgovora);
        String emailAgency = extractFiled("Email-a",textUgovora);
        String emailGov = extractFiled("Email-u",textUgovora);
        String title = extractFiled("Naslov",textUgovora);
        String content = extractFiled("Sadrzaj",textUgovora);
        String nameSignAgency = extractFiled("Ime-a",textUgovora);
        String lastnameSignAgency = extractFiled("Prezime-a",textUgovora);
        String nameSignGov = extractFiled("Ime-u",textUgovora);
        String lastnameSignGov = extractFiled("Prezime-u",textUgovora);
        GeoPoint geoPoint = locationService.getGeoPointFromAddress(addressGov);

        return new ContractIndex(nameSignGov,lastnameSignGov,
                nameSignAgency,lastnameSignAgency,govName,govLevel,addressGov,
                addressAgency,emailGov,emailAgency,title,content,nazivFajla,1,geoPoint);
    }

    public LawIndex parseIndex(String textZakona, String nazivFajla){
        return new LawIndex(textZakona,nazivFajla,2);
    }


    public String extractFiled(String fieldName,String text){
        return text.split(fieldName+":")[1].split(";")[0];
    }

}
