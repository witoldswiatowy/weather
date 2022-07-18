package com.sda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class LocationController {

        private final ObjectMapper objectMapper;
        private final LocationService locationService;

    public String createLocation(String json) {
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
            return String.format("{\"errorMessage\": \"%s\"}", e.getMessage()); //tak było w diary Michała Paukszto
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);                  //todo przy diary było inaczej, sprawdzic
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

    public String getLocation() {
        List<Location> listOfAllLocation = locationService.getAll();
        return null;
    }
}
