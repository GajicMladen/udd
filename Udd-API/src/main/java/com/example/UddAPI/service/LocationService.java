package com.example.UddAPI.service;

import com.example.UddAPI.dto.LocationDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class LocationService {

    public GeoPoint getGeoPointFromAddress(String address){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://eu1.locationiq.com/v1/search?key=pk.cfb6cb9d055c68355e918199f8f18d75&format=json&q="+address;

        ResponseEntity<List<LocationDTO>> locationIQ = restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(""),
                new ParameterizedTypeReference<List<LocationDTO>>() {});
        double lat = Double.parseDouble(Objects.requireNonNull(locationIQ.getBody()).get(0).getLat());
        double lon = Double.parseDouble(Objects.requireNonNull(locationIQ.getBody()).get(0).getLon());

        return new GeoPoint(lat,lon);
    }
}
