package com.sda;

import com.sda.lacation.Location;
import com.sda.lacation.LocationRepository;

import java.util.List;
import java.util.Optional;

public class LocationRepositoryMock implements LocationRepository {

    @Override
    public Location save(Location location) {
        location.setId(1L);
        return location;
    }

    @Override
    public List<Location> findAll() {
        return null;
    }

    @Override
    public Optional<Location> findById(Long id) {
        return Optional.empty();
    }
}
