package com.sda;

import java.util.Optional;

public class LocationRepositoryMock implements LocationRepository {

    @Override
    public Location save(Location location) {
        location.setId(1L);
        return location;
    }

    @Override
    public Optional<Location> findById(Long id) {
        return Optional.empty();
    }
}
