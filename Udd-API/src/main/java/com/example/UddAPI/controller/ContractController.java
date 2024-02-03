package com.example.UddAPI.controller;

import com.example.UddAPI.index.ContractIndex;
import com.example.UddAPI.service.MinioAdapter;
import com.example.UddAPI.service.ParsePDFService;
import com.example.UddAPI.service.ContractSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    @Autowired
    private ContractSearchService ugovorSearchService;

    @Autowired
    private ParsePDFService parsePDFService;

    @Autowired
    private MinioAdapter minioAdapter;

    @GetMapping("/test")
    public ResponseEntity<?> testEndpoint() throws InterruptedException {

        Thread.sleep(2 * 1000);
        return ResponseEntity.ok().body("");
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws  IOException {

        String textUgovora = parsePDFService.extractContent(file);
        ContractIndex contractIndex = parsePDFService.parseIndexUgovora(textUgovora, file.getOriginalFilename());
        ugovorSearchService.createUgovorIndex(contractIndex);

        this.minioAdapter.uploadFile(file.getOriginalFilename(),file.getBytes());

        return ResponseEntity.ok().body("");
    }

    @GetMapping("/findByTitle")
    public ResponseEntity<List<ContractIndex>> findContractsByTitle(@RequestParam("title") String naslov){
        List<ContractIndex> ugovori = ugovorSearchService.findContractsByTitle(naslov);
        return ResponseEntity.ok().body(ugovori);
    }

}
