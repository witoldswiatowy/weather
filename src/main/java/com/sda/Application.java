package com.sda;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;

public class Application {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        LocationRepository locationRepository = new LocationRepository(sessionFactory);
        LocationService locationService = new LocationService(locationRepository);
        LocationController locationController = new LocationController(objectMapper, locationService);
        UserInterface userInterface = new UserInterface(locationController);
        userInterface.run();//todo zaimplementowac run w userInterface
    }
}
