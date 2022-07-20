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
    void createLocation_ShouldThrowsAnExceptionWhenLongitudeIsNull() {
        //when
        Throwable throwable = catchThrowable(() -> locationService.create("Środa", "Polska", "Wielkopolska", null, 17));
        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).contains("longitude");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLocation_ShouldThrowsAnExceptionWhenLatitudeIsNull() {
        //when
        Throwable throwable = catchThrowable(() -> locationService.create("Środa", "Polska", "Wielkopolska", 52, null));
        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).contains("latitude");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLocation_ShouldThrowsAnExceptionWhenLatitudeIsLowerThanMinus181() {
        //when
        Throwable throwable = catchThrowable(() -> locationService.create("Środa", "Polska", "Wielkopolska", -181, 17));
        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).contains("Długość");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLocation_ShouldThrowsAnExceptionWhenLatitudeIsBiggerThan181() {
        //when
        Throwable throwable = catchThrowable(() -> locationService.create("Środa", "Polska", "Wielkopolska", 181, 17));
        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).contains("Długość");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLocation_ShouldThrowsAnExceptionWhenLongitudeIsLowerThanMinus91() {
        //when
        Throwable throwable = catchThrowable(() -> locationService.create("Środa", "Polska", "Wielkopolska", 52, -91));
        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).contains("Szerokość");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLocation_ShouldThrowsAnExceptionWhenLongitudeIsBiggerThan91() {
        //when
        Throwable throwable = catchThrowable(() -> locationService.create("Środa", "Polska", "Wielkopolska", 17, 91));
        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).contains("Szerokość");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void createLocation_ShouldThrowsAnExceptionWhenCityIsBlank() {
        //when
        Throwable throwable = catchThrowable(() -> locationService.create("     ", "Polska", "Wielkopolska", 52, 17));
        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).contains("city");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }
    //todo adding test cases with blank fielde i other parameters like top example
}
