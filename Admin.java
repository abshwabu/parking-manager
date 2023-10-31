import java.util.Scanner;

public class Admin {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(20, 10);
        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;
            while (!exit) {
                System.out.println("Enter 1 if the car is entering the parking lot, 2 if the car is exiting, 3 to exit the program: ");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Enter the card number: ");
                        String cardNumber = scanner.next();
                        parkingLot.carEnter(cardNumber, System.currentTimeMillis());
                        System.out.println("Car added to the parking lot.");
                        break;
                    case 2:
                        System.out.println("Enter the card number: ");
                        String cardNum = scanner.next();
                        double price = parkingLot.carExit(cardNum, System.currentTimeMillis());
                        System.out.println("Total Price: $" + price);
                        parkingLot.moveCarToFile("parkinglot.txt", "theCarsThatLeft.txt", cardNum);
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            }
        }
    }
}
