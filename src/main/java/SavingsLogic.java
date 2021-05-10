import java.util.Arrays;

public class SavingsLogic {
    long savings(Month[] values){
        long[] saves = new long[values.length];
        for (int i = 0; i < values.length; i++){
            saves[i] = values[i].getSavings();
        }

        return Arrays.stream(saves).sum();
    }
}
