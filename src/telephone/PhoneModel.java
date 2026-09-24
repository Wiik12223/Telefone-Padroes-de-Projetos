import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Store a phone number, digit-by-digit
 */
public class PhoneModel {
    private final List<Integer> digits = new ArrayList<>();
    private final List<PhoneObserver> observers = new ArrayList<>();

    public void addDigit(int newDigit) {
        digits.add(newDigit);
        notifyObservers(newDigit);
    }

    public List<Integer> getDigits() {
        return Collections.unmodifiableList(digits);
    }

    public void addObserver(PhoneObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(int newDigit) {
        List<Integer> currentNumber = getDigits();
        for (PhoneObserver observer : observers) {
            observer.onDigitAdded(newDigit, currentNumber);
        }
    }
}
