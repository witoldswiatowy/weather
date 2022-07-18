package com.sda;

import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class UserInterface {

    private final LocationController locationController;

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Aplikacja jest uruchomiona\n");

        while (true) {
            System.out.println("Witaj w aplikacji pogodowej\n" +
                    "Co chcesz zrobić?");
            System.out.println(" 1. Pokaż wszystkie lokalizacji");
            System.out.println(" 2. Dodaj nową loklizację ");
            System.out.println(" 3. Pobierz pogodę dla... ");
            System.out.println(" 0. Zamknać aplikację");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    break;
                case 2:
                    createLocation();
                    break;
                case 3:
                    break;
                case 0:
                    return;
            }
        }
    }

    private void createLocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj miasto:");
        String miasto = scanner.nextLine();
        System.out.println("Podaj region/województwo:");
        String region = scanner.nextLine();
        System.out.println("Podaj kraj:");
        String country = scanner.nextLine();
        System.out.println("Podaj długość geograficzną:");
        Integer longitude = scanner.nextInt();
        System.out.println("Podaj szerokość geograficzną:");
        Integer latitude = scanner.nextInt();
        String json = String.format("{\"miasto\": \"%s\",\"region\":\"%s\",\"country\":\"%s\",\"longitude\":\"%s\",\"latitude\":\"%s\"}", miasto, region, country, longitude,latitude);
        System.out.println("Wysyłam HTTP request: " + json);
        String httpResponse = locationController.createLocation(json);
        System.out.println("Odpowiedź z serwera: " + httpResponse);
    }

}
