import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
public class BalanceCalculatorFitnesseTest
{
    BigDecimal amount;
    int days;
    Date dateOfFirstTransfer;
    BigDecimal[] expenseAmounts;
    Date[] expenseDates;
    BigDecimal[] incomeAmounts;
    Date[] incomeDates;
    Date resultDate;
    BalanceCalculator balanceCalculator;

    public void setResultDate(String resultDate) {
        this.resultDate = java.sql.Date.valueOf(LocalDate.parse(resultDate));
    }

    public void setAmount(String amount) {
        if (amount != null && !amount.equals("")) {
            this.amount = new BigDecimal(amount);
        }
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setDateOfFirstTransfer(String date) {
        if (date != null && !date.equals("")) {
            this.dateOfFirstTransfer = java.sql.Date.valueOf(LocalDate.parse(date));
        }
    }

    public void setExpenseAmounts(long[] expenseAmounts) {
        if (expenseAmounts == null) {
            this.expenseAmounts = new BigDecimal[0];
            return;
        }
        this.expenseAmounts = new BigDecimal[expenseAmounts.length];

        for(int i=0; i<expenseAmounts.length; i++) {
            this.expenseAmounts[i] = new BigDecimal(expenseAmounts[i]);
        }
    }

    public void setExpenseDates(String[] expenseDates) {
        if (expenseDates == null) {
            this.expenseDates = new Date[0];
            return;
        }
        this.expenseDates = new Date[expenseDates.length];

        for(int i=0; i<expenseDates.length; i++) {
            this.expenseDates[i] = java.sql.Date.valueOf(LocalDate.parse(expenseDates[i]));
        }
    }

    public void setIncomeAmounts(long[] incomeAmounts) {
        if (incomeAmounts == null) {
            this.incomeAmounts = new BigDecimal[0];
            return;
        }
        this.incomeAmounts = new BigDecimal[incomeAmounts.length];

        for(int i=0; i<incomeAmounts.length; i++) {
            this.incomeAmounts[i] = new BigDecimal(incomeAmounts[i]);
        }
    }

    public void setIncomeDates(String[] incomeDates) {
        if (incomeDates == null) {
            this.incomeDates = new Date[0];
            return;
        }
        this.incomeDates = new Date[incomeDates.length];

        for(int i=0; i<incomeDates.length; i++) {
            this.incomeDates[i] = java.sql.Date.valueOf(LocalDate.parse(incomeDates[i]));
        }
    }

    public String getBalance() {
        Repository<CyclicalMoneyTransfer> cyclicalMoneyTransferRepository = new Repository<>();
        Repository<Expense> expenseRepository = new Repository<>();
        Repository<Income> incomeRepository = new Repository<>();

        if (days != 0) {
            cyclicalMoneyTransferRepository.add(new CyclicalMoneyTransfer(this.amount, this.days, dateOfFirstTransfer));
        }

        for(int i=0; i<expenseAmounts.length; i++) {
            expenseRepository.add(new Expense(expenseAmounts[i], expenseDates[i]));
        }

        for(int i=0; i<incomeAmounts.length; i++) {
            incomeRepository.add(new Income(incomeAmounts[i], incomeDates[i]));
        }

        balanceCalculator = new BalanceCalculator(cyclicalMoneyTransferRepository, expenseRepository, incomeRepository);

        return String.valueOf(balanceCalculator.getBalance(resultDate));
    }
}