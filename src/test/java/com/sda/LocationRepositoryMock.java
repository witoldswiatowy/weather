package com.sda;

public class LocationRepositoryMock implements LocationRepository {

    @Override
    public Location save(Location location) {
        location.setId(1L);
        return location;
    }
}
