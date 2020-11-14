package lesson_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TripApplication {

    private static int idNumber = 1;

    public static void main(String[] args) {

        List<Trip> trips = new ArrayList<>();

        while (true) {
            System.out.println("Program menu:");
            System.out.println("1. Add new trip");
            System.out.println("2. Delete trip");
            System.out.println("3. Show all trips");
            System.out.println("4. Exit");

            System.out.println();

            System.out.println("Enter menu item number to execute:");

            Scanner scanner = new Scanner(System.in);
            int userChoice = Integer.parseInt(scanner.nextLine());

            switch (userChoice) {
                case 1 -> {
                    System.out.println("Enter trip name: ");
                    String tripName = scanner.nextLine();
                    System.out.println("Enter a date the trip is starting: ");
                    String startDate = scanner.nextLine();
                    System.out.println("Enter a date the trip finishes: ");
                    String finishDate = scanner.nextLine();
                    System.out.println("Enter a trip description: ");
                    String detailDescription = scanner.nextLine();
                    Trip trip = new Trip(tripName, startDate, finishDate, detailDescription);
                    trip.setIdNumber(idNumber);
                    idNumber++;
                    trips.add(trip);
                    System.out.println("Your trip was added to list.");

                }
                case 2 -> {
                    System.out.println("Enter trip name: ");
                    String tripName = scanner.nextLine();
                    if (trips.removeIf(items -> items.getTripName().equals(tripName)))
                        System.out.println("A trip " + tripName + " was removed from list.");
                    else System.out.println("A trip " + tripName + " was not found.");
                }
                case 3 -> {
                    System.out.println("Here is a list of trips: ");
                    for (Trip item : trips) {
                        System.out.println(item);
                    }
                    System.out.println("This is the end.");
                }
                case 4 -> {
                    System.out.println("Good by!");
                    System.exit(0);
                }
            }
        }
    }
}