package com.sda;

public class LocationService {

    LocationRepository locationRepository;
    Location create(String city, String country, Integer longitude, Integer latitude) {
        validator(city, country, longitude, latitude);

        Location location = new Location();
        location.setCity(city);
        location.setCountry(country);
        location.setLongitude(longitude);
        location.setLatitude(latitude);

        return locationRepository.save(location);
    }

    private void validator(String city, String country, Integer longitude, Integer latitude) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("Pole city nie może być puste");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Pole country nie może być puste");
        }
        if (longitude == null) {
            throw new IllegalArgumentException("Pole longitude nie może być puste");
        }
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Długość geograficzna musu mieścić się w przedziale od -180 do 180");
        }
        if (latitude == null) {
            throw new IllegalArgumentException("Pole latitude nie może być puste");
        }
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Szerokość geograficzna musu mieścić się w przedziale od -90 do 90");
        }
    }
}
