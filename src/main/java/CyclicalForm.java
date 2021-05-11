import java.math.BigDecimal;
import java.util.ArrayList;

public class CyclicalForm {

    public ArrayList<CyclicalMoneyTransfer> moneyTransfers;

    public CyclicalForm() {
        moneyTransfers = new ArrayList<>();
    }

    void addCyclicalMoneyTransfer(BigDecimal amount, int days) {
        var moneyTransfer = new CyclicalMoneyTransfer(amount, days);
        moneyTransfers.add(moneyTransfer);
    }

    void printCyclicalMoneyTransfer() {
        for (CyclicalMoneyTransfer m : moneyTransfers) {
            System.out.println(m.toString());
        }
    }

}
