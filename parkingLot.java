import java.io.*;
import java.util.*;

public class ParkingLot {
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
        saveCarsToFile("parkinglot.txt", new ArrayList<>(parkedCars.values()));
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

    private void saveCarsToFile(String fileName, List<Car> cars) {
        Map<String, Car> carMap = new HashMap<>();
        for (Car car : cars) {
            carMap.put(car.getCardNumber(), car);
        }
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Car car : cars) {
                printWriter.println(car.getCardNumber() + "," + car.getEntryTime());
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void moveCarToFile(String sourceFileName, String destinationFileName, String cardNumber) {
        try {
            List<Car> tempCars = new ArrayList<>();
            File inputFile = new File(sourceFileName);
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] parts = data.split(",");
                if (parts[0].equals(cardNumber)) {
                    writeToFile(destinationFileName, data);
                } else {
                    Car car = new Car(parts[0], Long.parseLong(parts[1]));
                    tempCars.add(car);
                }
            }
            scanner.close();
            inputFile.delete();
            saveCarsToFile(sourceFileName, tempCars);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(String fileName, String data) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(data);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
