package com.quikido.auth.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quikido.auth.dto.RouteDetails;
import com.quikido.auth.entity.ActiveRide;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RouteService {
    @Value("${google.maps.api.key}")
    private String apiKey;

    public RouteDetails getOptimizedRoute(double pickupLat, double pickupLng, double dropLat, double dropLng) {
        String url = String.format(
                "https://maps.googleapis.com/maps/api/directions/json?origin=%f,%f&destination=%f,%f&key=%s",
                pickupLat, pickupLng, dropLat, dropLng, apiKey
        );

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, RouteDetails.class);
    }
    public RouteDetails parseRouteResponse(String response) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);

        String distance = root.path("routes").get(0).path("legs").get(0).path("distance").path("text").asText();
        String duration = root.path("routes").get(0).path("legs").get(0).path("duration").path("text").asText();
        String polyline = root.path("routes").get(0).path("overview_polyline").path("points").asText();

        return new RouteDetails(distance, duration, polyline);
    }
}