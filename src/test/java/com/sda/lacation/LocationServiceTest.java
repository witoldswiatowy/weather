package com.sda.lacation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.LocationRepositoryMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class LocationServiceTest {

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
        Location location = locationService.create("Środa", "Polska", "Wielkopolska", 52, 17);
        // then
        assertThat(location.getId()).isNotNull();
        assertThat(location.getCity()).isEqualTo("Środa");
        assertThat(location.getCountry()).isEqualTo("Polska");
        assertThat(location.getRegion()).isEqualTo("Wielkopolska");
        assertThat(location.getLongitude()).isEqualTo(52);
        assertThat(location.getLatitude()).isEqualTo(17);
    }

    @Test
    void createLocation_whenRegionIsNull_shouldReturnsCorrectLocationObject() {
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

    @Test
    void createLocation_ShouldThrowsAnExceptionWhenCityIsNull() {
        // when
        Throwable throwable = catchThrowable(() -> locationService.create(null, "Polska", null, 52, 17));

        // then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).contains("city");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLocation_ShouldThrowsAnExceptionWhenLongitudeIsNull(){
        //given
        //when
        //then
    }

    @Test
    void createLocation_ShouldThrowsAnExceptionWhenLatitudeIsNull(){
        //given
        //when
        //then
    }

    @Test
    void createLocation_ShouldThrowsAnExceptionWhenLatitudeIsLowerThan181(){
        //given
        //when
        //then
    }

    // todo add createLocation_ShouldThrowsAnExceptionWhenLatitudeIsLowerThan181 ...
}