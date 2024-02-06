package com.example.UddAPI.controller;

import com.example.UddAPI.dto.AdvancedSearchDTO;
import com.example.UddAPI.index.ContractIndex;
import com.example.UddAPI.service.MinioAdapter;
import com.example.UddAPI.service.ParsePDFService;
import com.example.UddAPI.service.ContractSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/contracts")
@Slf4j
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

        String contractText = parsePDFService.extractContent(file);
        ContractIndex contractIndex = parsePDFService.parseIndexUgovora(contractText, file.getOriginalFilename());
        ugovorSearchService.createUgovorIndex(contractIndex);

        this.minioAdapter.uploadFile(file.getOriginalFilename(),file.getBytes());

        log.info(String.format("STATISTIC-LOG | %s %s -> %s -> %s ;",
                contractIndex.getNameSignAgency(),
                contractIndex.getLastnameSignAgency(),
                contractIndex.getGovName(),
                contractIndex.getAddressGov()));

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
    @PostMapping("/findByTitle")
    public ResponseEntity<List<ContractIndex>> findContractsByTitle(@RequestParam("title") String naslov){
        List<ContractIndex> contracts = ugovorSearchService.findContractsByTitle(naslov);
        return ResponseEntity.ok().body(contracts);
    }

    @PostMapping("/findByNameAndLastnameSignAgency")
    public ResponseEntity<List<ContractIndex>> findContractsByNameLastnameAgency(@RequestParam("nameAndLastName") String nameAndLastname){
        List<ContractIndex> contracts = ugovorSearchService.findContractsByNameAndLastnameSignAgency(nameAndLastname);
        return ResponseEntity.ok().body(contracts);
    }

    @PostMapping("/findByNameAndLevelGov")
    public ResponseEntity<List<ContractIndex>> findContractsByNameLevelGov(@RequestParam("nameAndLevel") String nameAndLevel){
        List<ContractIndex> contracts = ugovorSearchService.findContractsByNameAndLevelGov(nameAndLevel);
        return ResponseEntity.ok().body(contracts);
    }
    @PostMapping("/findByContent")
    public ResponseEntity<List<ContractIndex>> findContractsByContent(@RequestParam("content") String content){
        List<ContractIndex> contracts = ugovorSearchService.findContractsByContent(content);
        return ResponseEntity.ok().body(contracts);
    }

    @PostMapping(value = "/advancedSearch",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ContractIndex>> findContractsByAdvancedSearch(@RequestBody AdvancedSearchDTO advancedSearchDTO){
        List<ContractIndex> contracts = ugovorSearchService.findContractsByAdvancedSearch(advancedSearchDTO);
        return ResponseEntity.ok().body(contracts);
    }

    @PostMapping(value = "/geoLocationSearch")
    public ResponseEntity<List<ContractIndex>> findContractsByGeoLocation(@RequestParam("address") String address,@RequestParam("distance") String distance){
        List<ContractIndex> contracts = ugovorSearchService.searchWithGeoLocation(address,distance);
        return ResponseEntity.ok().body(contracts);
    }
}
