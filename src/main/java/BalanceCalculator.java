import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class BalanceCalculator {
    private final Repository<CyclicalMoneyTransfer> cyclicalMoneyTransferRepository;
    private final Repository<Expense> expenseRepository;
    private final Repository<Income> incomeRepository;

    public BalanceCalculator(Repository<CyclicalMoneyTransfer> cyclicalMoneyTransferRepository, Repository<Expense> expenseRepository, Repository<Income> incomeRepository) {
        this.cyclicalMoneyTransferRepository = cyclicalMoneyTransferRepository;
        this.expenseRepository = expenseRepository;
        this.incomeRepository = incomeRepository;
    }

    public BigDecimal getBalance(Date date) {
        var incomes = incomeRepository.getAll();
        var totalIncome = incomes.stream()
                .filter((e) -> alreadyHappened(e.getDate(), date))
                .map(Income::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var expenses = expenseRepository.getAll();
        var totalExpense = expenses.stream()
                .filter((e) -> alreadyHappened(e.getDate(), date))
                .map(Expense::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var cyclicalTransfers = cyclicalMoneyTransferRepository.getAll();
        var totalCyclicalContribution = cyclicalTransfers.stream()
                .map((e) -> calculateContribution(e, date))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalIncome.add(totalCyclicalContribution).subtract(totalExpense);
    }

    private boolean alreadyHappened(Date transferDate, Date calculationDate) {
        return transferDate.before(calculationDate);
    }

    private BigDecimal calculateContribution(CyclicalMoneyTransfer transfer, Date calculationDate) {
        var result = BigDecimal.ZERO;
        var calendar = Calendar.getInstance();
        var trackedDate = transfer.getDateOfFirstTransfer();

        while (trackedDate.before(calculationDate)) {
            result = result.add(transfer.getAmount());

            calendar.setTime(trackedDate);
            calendar.add(Calendar.DAY_OF_MONTH, transfer.getDays());
            trackedDate = calendar.getTime();
        }
        return result;
    }
}
