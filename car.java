import java.io.Serializable;

public class Car implements Serializable {
    private String cardNumber;
    private long entryTime;

    public Car(String cardNumber, long entryTime) {
        this.cardNumber = cardNumber;
        this.entryTime = entryTime;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public long getEntryTime() {
        return entryTime;
    }
}
