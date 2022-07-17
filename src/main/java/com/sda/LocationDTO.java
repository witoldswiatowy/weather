package com.sda;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class LocationDTO {
    private Long id;
    private String city;
    private String region;
    private String country;
    private Integer longitude;
    private Integer latitude;
    LocalDateTime createdDate;
}
