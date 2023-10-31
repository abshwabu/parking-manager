import java.util.Scanner;

public class Admin {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(20, 10);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 1 if the car is entering the parking lot, 2 if the car is exiting: ");
        int option = scanner.nextInt();
        if (option == 1) {
            System.out.println("Enter the card number: ");
            String cardNumber = scanner.next();
            parkingLot.carEnter(cardNumber, System.currentTimeMillis());
            System.out.println("Car added to the parking lot.");
        } else if (option == 2) {
            System.out.println("Enter the card number: ");
            String cardNumber = scanner.next();
            double totalPrice = parkingLot.carExit(cardNumber, System.currentTimeMillis());
            System.out.println("Total Price: $" + totalPrice);
            // Move the car to the other file
            parkingLot.moveCarToFile("parkinglot.txt", "theCarsThatLeft.txt", cardNumber);
        } else {
            System.out.println("Invalid option.");
        }
    }
}
