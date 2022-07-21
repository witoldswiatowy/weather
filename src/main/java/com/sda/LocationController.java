package com.sda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

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
            LocationDTO response = LocationMapper.mapToLocationDTO(location);
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            return String.format("{\"errorMessage\": \"%s\"}", e.getMessage()); //tak było w diary Michała Paukszto
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);                  //todo przy diary było inaczej, sprawdzic
        }
    }
}
