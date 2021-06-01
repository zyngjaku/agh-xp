import java.math.BigDecimal;

public class BalanceProvider {
    BigDecimal balance = BigDecimal.ZERO;
    public BigDecimal getBalance() {
        return balance;
    }

    public void addToBalance(BigDecimal value) {
        balance = balance.add(value);
    }

}
