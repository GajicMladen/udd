package com.example.UddAPI.controller;

import com.example.UddAPI.index.ContractIndex;
import com.example.UddAPI.index.LawIndex;
import com.example.UddAPI.service.LawSearchService;
import com.example.UddAPI.service.MinioAdapter;
import com.example.UddAPI.service.ParsePDFService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/laws")
@Slf4j
public class LawController {


    @Autowired
    private ParsePDFService parsePDFService;

    @Autowired
    private MinioAdapter minioAdapter;

    @Autowired
    private LawSearchService lawSearchService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        String contractText = parsePDFService.extractContent(file);
        LawIndex lawIndex = parsePDFService.parseIndex(contractText, file.getOriginalFilename());
        lawSearchService.createLawIndex(lawIndex);

        this.minioAdapter.uploadFile(file.getOriginalFilename(),file.getBytes());

        return ResponseEntity.ok().body("");
    }

    @PostMapping("/download")
    public ResponseEntity<?> downloadFile(@RequestParam("filename") String filename){
        byte[] bytes = minioAdapter.getFile(filename);
        ByteArrayResource resource =  new ByteArrayResource(bytes);

        return ResponseEntity.ok()
                .contentLength(bytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @PostMapping("/findByContent")
    public ResponseEntity<List<LawIndex>> findContractsByTitle(@RequestParam("content") String content){
        List<LawIndex> contracts = lawSearchService.findLawsByContent(content);
        return ResponseEntity.ok().body(contracts);
    }
}
