import java.math.BigDecimal;

public class BalanceProvider {
    private static BigDecimal balance = BigDecimal.ZERO;
    public static BigDecimal getBalance() {
        return balance;
    }

    public void addToBalance(BigDecimal value) {
        balance = balance.add(value);
    }

    public void removeFromBalance(BigDecimal value) {
        balance = balance.subtract(value);
    }
}
