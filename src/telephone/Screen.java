import java.util.List;

/**
 * Prints things out to the screen, when needed
 * Printing to the screen:
 * System.out.println("hello");
 */
public class Screen {
    private final PhoneModel model;

    public Screen(PhoneModel model) {
        this.model = model;

        model.addObserver(new PhoneObserver() {
            @Override
            public void onDigitAdded(int newDigit, List<Integer> currentNumber) {
                System.out.println("Pressing: " + newDigit);
            }
        });

        model.addObserver(new PhoneObserver() {
            @Override
            public void onDigitAdded(int newDigit, List<Integer> currentNumber) {
                System.out.println("Agora discando " + formatNumber(currentNumber) + "...");
            }
        });
    }

    private String formatNumber(List<Integer> digits) {
        StringBuilder number = new StringBuilder();
        for (Integer digit : digits) {
            number.append(digit);
        }
        return number.toString();
    }
}
