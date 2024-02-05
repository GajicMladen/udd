package com.example.UddAPI.service;

import com.example.UddAPI.dto.AdvancedSearchDTO;
import com.example.UddAPI.index.ContractIndex;
import com.example.UddAPI.repository.ContractRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ContractSearchService {

    private static final String UGOVOR_INDEX = "index_ugovora";

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private LocationService locationService;

    public String createUgovorIndex(ContractIndex contractIndex) {

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(contractIndex.getId())
                .withObject(contractIndex).build();

        return elasticsearchOperations.index(indexQuery, IndexCoordinates.of(UGOVOR_INDEX));
    }
    public List<ContractIndex> findContractsByTitle(final String name) {
        Criteria criteria = new Criteria("title").contains(name);

        Query searchQuery = new CriteriaQuery(criteria);

        SearchHits<ContractIndex> contractHits = elasticsearchOperations
                .search(searchQuery,
                        ContractIndex.class,
                        IndexCoordinates.of(UGOVOR_INDEX));

        return getListOfContracts(contractHits,"title");
    }

    public List<ContractIndex> findContractsByContent(String content) {
//        Criteria criteria = getCriteriaForContent(content);

        SearchHits<ContractIndex> contractHits = contractRepository.findByContentContaining(content);


        return getListOfContracts(contractHits,"content");
    }
    public List<ContractIndex> findContractsByNameAndLastnameSignAgency(String nameAndLastname) {
        String name = nameAndLastname.split(" ")[0];
        String lastname = nameAndLastname.split(" ").length > 1 ? nameAndLastname.split(" ")[1] : "";

        SearchHits<ContractIndex> contractHits = contractRepository
                .findByNameSignAgencyContainingAndLastnameSignAgencyContaining(name,lastname);

        return getListOfContracts(contractHits,"nameSignAgency");
    }

    public List<ContractIndex> findContractsByNameAndLevelGov(String nameAndLevel) {
        String name = nameAndLevel.split(" ")[0];
        String level = nameAndLevel.split(" ").length > 1 ? nameAndLevel.split(" ")[1] : "";

        SearchHits<ContractIndex> contractHits = contractRepository.findByGovNameContainingAndGovLevelContaining(name,level);

        return getListOfContracts(contractHits,"govName");
    }


    public List<ContractIndex> findContractsByAdvancedSearch(AdvancedSearchDTO advancedSearchDTO) {
        Criteria criteria = new Criteria();
        if(advancedSearchDTO.isB_contractContent()){
            upgradeCriteria(criteria,
                    advancedSearchDTO.getContractContentOperator(),
                    getCriteriaForContent(advancedSearchDTO.getContractContent()));
        }
        if(advancedSearchDTO.isB_govNameAndLevel()){
            upgradeCriteria(criteria,
                    advancedSearchDTO.getGovNameAndLevelOperator(),
                    getCriteriaForNameAndLevelGov(advancedSearchDTO.getGovNameAndLevel()));
        }
        if(advancedSearchDTO.isB_nameAndLastnameSignAgency()){
            upgradeCriteria(criteria,
                    advancedSearchDTO.getNameAndLastnameSignAgencyOperator(),
                    getCriteriaForNameAndLastnameSignAgency(advancedSearchDTO.getNameAndLastnameSignAgency()));
        }
        Query searchQuery = new CriteriaQuery(criteria);

        SearchHits<ContractIndex> contractHits = elasticsearchOperations
                .search(searchQuery,ContractIndex.class,IndexCoordinates.of(UGOVOR_INDEX));

        return getListOfContracts(contractHits,"content");
    }

    public List<ContractIndex> searchWithGeoLocation(String address, String distance){

        GeoPoint geoPoint = locationService.getGeoPointFromAddress(address);

        Query searchQuery = NativeQuery.builder()
                .withQuery(query -> query.geoDistance(geoDistanceQuery ->
                        geoDistanceQuery.field("location").distance(distance)
                                .location(geoLocation -> geoLocation.latlon(latLonGeoLocation ->
                                        latLonGeoLocation.lon(geoPoint.getLon()).lat(geoPoint.getLat()))))).build();

        SearchHits<ContractIndex> contractHits = elasticsearchOperations
                .search(searchQuery,ContractIndex.class,IndexCoordinates.of(UGOVOR_INDEX));

        return getListOfContracts(contractHits,"content");
    }
    private Criteria getCriteriaForNameAndLastnameSignAgency(String nameAndLastname){
        String name = nameAndLastname.split(" ")[0];
        String lastname = nameAndLastname.split(" ").length > 1 ? nameAndLastname.split(" ")[1] : "";
        return new Criteria("ime_p_a").contains(name).and(new Criteria("prezime_p_a").contains(lastname));
    }

    private Criteria getCriteriaForNameAndLevelGov(String nameAndLevel){
        String name = nameAndLevel.split(" ")[0];
        String level = nameAndLevel.split(" ").length > 1 ? nameAndLevel.split(" ")[1] : "";
        return new Criteria("naziv_uprave").contains(name).and(new Criteria("nivo_uprave").contains(level));
    }

    private Criteria getCriteriaForContent(String content){
        return new Criteria("sadrzaj_sr").contains(content);
    }
    private void upgradeCriteria(Criteria criteria, String operator, Criteria newCriteria){
        switch (operator) {
            case "AND" -> criteria.and(newCriteria);
            case "OR" -> criteria.or(newCriteria);
            case "NOT" -> criteria.and(newCriteria.not());
        }
    }

    private List<ContractIndex> getListOfContracts(SearchHits<ContractIndex> searchHits,String fieldName){

        List<ContractIndex> contracts = new ArrayList<ContractIndex>();
        searchHits.forEach( contractHit -> {
            ContractIndex contractIndex = contractHit.getContent();
            if(contractHit.getHighlightField(fieldName).size()>0)
                contractIndex.setHighlight(contractHit.getHighlightField(fieldName).get(0));
            contracts.add(contractHit.getContent());

        });
        return contracts;
    }
}
