package com.sda;

public class Application {
    public static void main(String[] args) {

        LocationController locationController = new LocationController();
        UserInterface userInterface = new UserInterface(locationController);
        userInterface.run();//todo zaimplementowac run w userInterface
    }
}
