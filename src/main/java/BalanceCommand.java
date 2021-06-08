import java.math.BigDecimal;
import java.util.Date;

public class BalanceCommand {
    private final BalanceCalculator balanceCalculator;

    public BalanceCommand(BalanceCalculator balanceCalculator) {
        this.balanceCalculator = balanceCalculator;
    }

    public BigDecimal getTodayBalance() {
        return balanceCalculator.getBalance(new Date());
    }
}
