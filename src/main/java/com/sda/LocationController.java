package com.sda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LocationController {

    public String createLocation(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        LocationService locationService = new LocationService();
        try {
            LocationDTO locationDTO = objectMapper.readValue(json, LocationDTO.class);
            Location location = locationService
                    .create(locationDTO.getCity(),
                            locationDTO.getCountry(),
                            locationDTO.getLongitude(),
                            locationDTO.getLatitude());
            LocationDTO response = mapToLocationDTO(location);
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e); //todo przy diary było inaczej, sprawdzic
        }
    }

    private LocationDTO mapToLocationDTO(Location location) {
        return LocationDTO.builder()
                .id(location.getId())
                .city(location.getCity())
                .region(location.getRegion())
                .country(location.getCountry())
                .longitude(location.getLongitude())
                .latitude(location.getLatitude())
                .build();
    }
}
