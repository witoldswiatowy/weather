package com.sda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class LocationController {

    private final ObjectMapper objectMapper;
    private final LocationService locationService;

    public String createLocation(String json) {
        try {
            LocationDTO locationDTO = objectMapper.readValue(json, LocationDTO.class);
            Location location = locationService.create(
                    locationDTO.getCity(),
                    locationDTO.getCountry(),
                    locationDTO.getRegion(),
                    locationDTO.getLongitude(),
                    locationDTO.getLatitude());
            LocationDTO response = mapToLocationDTO(location);
            return objectMapper.writeValueAsString(response);
        } catch (
                JsonProcessingException e) { // todo add IllegalArgumentException handling (JsonProcessingException | IllegalArgumentException e)
            return String.format("{\"errorMessage\": \"%s\"}", e.getMessage()); //tak było w diary Michała Paukszto
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

    //todo dokończyć !!!!!!!!!
    public String getLocation() { // todo rename to getLocations
        try {
            List<Location> listOfAllLocation = locationService.getAll();
            List<LocationDTO> listOfAllLocationDTO = listOfAllLocation.stream()
                    .map(this::mapingToLocationDTO)
                    .collect(Collectors.toList());
            return objectMapper.writeValueAsString(listOfAllLocationDTO);
        } catch (Exception e) {
            return String.format("{\"errorMessage\": \"%s\"}", e.getMessage());
        }
    }

    LocationDTO mapingToLocationDTO(Location location) {
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
