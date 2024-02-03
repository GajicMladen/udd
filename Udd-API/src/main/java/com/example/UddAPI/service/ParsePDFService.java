package com.example.UddAPI.service;

import com.example.UddAPI.index.ContractIndex;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class ParsePDFService {
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

        return new ContractIndex(nameSignGov,lastnameSignGov,
                nameSignAgency,lastnameSignAgency,govName,govLevel,addressGov,
                addressAgency,emailGov,emailAgency,title,content,nazivFajla,1);
    }

    public String extractFiled(String fieldName,String text){
        return text.split(fieldName+":")[1].split(";")[0];
    }
}
