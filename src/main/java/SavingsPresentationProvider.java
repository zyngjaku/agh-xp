public class SavingsPresentationProvider {

    String currency;

    public SavingsPresentationProvider(String currency) {
        this.currency = currency;
    }

    public String getRepresentation(long value) {
        long rest = Math.abs(value % 100);
        String minus = (value < 0) ? "-" : "";
        value = Math.abs(value / 100);
        return String.format("%s%d.%02d %s", minus, value, rest, currency);
    }
}
