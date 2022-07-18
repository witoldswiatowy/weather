package com.sda;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

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
        Location location = locationService.create("Środa", "Polska", 52, 17);
        // then
        assertThat(location.getId()).isNotNull();
        assertThat(location.getCity()).isEqualTo("Środa");
        assertThat(location.getCountry()).isEqualTo("Polska");
        assertThat(location.getLongitude()).isEqualTo(52);
        assertThat(location.getLatitude()).isEqualTo(17);
    }

    @Test
    void createLocation_ShuldThrowsAnExceptionWhenCityIsNull() {
        // when
        Throwable throwable = catchThrowable(() -> locationService.create(null, "Polska", 52, 17));

        // then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).contains("city");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }

}