package com.sda;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest { // todo rename to eg. LocationTest, LocationServiceTest

    LocationService locationService;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        LocationRepository locationRepository = new LocationRepositoryMock();
        locationService = new LocationService(locationRepository);
    }

    @Test
    void createLocation_shouldReturnsCorrectLocationObject() {
        // when
        // todo provide region
        Location location = locationService.create("Środa", "Polska", null, 52, 17);
        // then
        assertThat(location.getId()).isNotNull();
        assertThat(location.getCity()).isEqualTo("Środa");
        assertThat(location.getCountry()).isEqualTo("Polska");
        assertThat(location.getLongitude()).isEqualTo(52);
        assertThat(location.getLatitude()).isEqualTo(17);
    }

    // todo add createLocation_whenRegionIsNull_shouldReturnsCorrectLocationObject

    @Test
    void createLocation_ShouldThrowsAnExceptionWhenCityIsNull() {
        // when
        Throwable throwable = catchThrowable(() -> locationService.create(null, "Polska", null, 52, 17));

        // then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).contains("city");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    // todo add createLocation_ShouldThrowsAnExceptionWhenCountryIsNull
    // todo add createLocation_ShouldThrowsAnExceptionWhenLongitudeIsNull
    // todo add createLocation_ShouldThrowsAnExceptionWhenLatitudeIsNull
    // todo add createLocation_ShouldThrowsAnExceptionWhenLatitudeIsLowerThan181 ...
}