import java.util.List;

/**
 * Observer notified whenever a new digit is added to the phone number.
 */
public interface PhoneObserver {
    void onDigitAdded(int newDigit, List<Integer> currentNumber);
}
