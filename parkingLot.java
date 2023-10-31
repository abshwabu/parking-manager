import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ParkingLot {
    private Map<String, Car> parkedCars;
    private int basePrice;
    private int hourlyPrice;

    public ParkingLot(int basePrice, int hourlyPrice) {
        this.parkedCars = new HashMap<>();
        this.basePrice = basePrice;
        this.hourlyPrice = hourlyPrice;
    }

    public void carEnter(String cardNumber, long entryTime) {
        Car newCar = new Car(cardNumber, entryTime);
        parkedCars.put(cardNumber, newCar);
        saveCarsToFile("parkinglot.txt", parkedCars);
    }

    public double carExit(String cardNumber, long exitTime) {
        Car car = parkedCars.get(cardNumber);
        if (car == null) {
            System.out.println("Car not found in the parking lot.");
            return 0;
        }
        long duration = (exitTime - car.getEntryTime()) / 3600000; // converting milliseconds to hours

        if (duration <= 20) {
            return basePrice;
        } else {
            return basePrice + (duration - 20) * hourlyPrice;
        }
    }

    private void saveCarsToFile(String fileName, Map<String, Car> cars) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Car car : cars.values()) {
                printWriter.println(car.getCardNumber() + "," + car.getEntryTime());
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void moveCarToFile(String sourceFileName, String destinationFileName, String cardNumber) {
    try {
        File inputFile = new File(sourceFileName);
        File outputFile = new File(destinationFileName);
        Scanner scanner = new Scanner(inputFile);
        FileWriter fileWriter = new FileWriter(outputFile, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            if (data.startsWith(cardNumber + ",")) {
                printWriter.println(data);
            }
        }
        printWriter.close();
        scanner.close();
        inputFile.delete();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
