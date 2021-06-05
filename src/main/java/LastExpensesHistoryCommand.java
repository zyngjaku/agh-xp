import java.util.List;

public class LastExpensesHistoryCommand {

    private final Repository<Expense> expenseRepository;

    public LastExpensesHistoryCommand(Repository<Expense> expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getLastExpenses(String number) {
        var expensesList = expenseRepository.getAll();
        try {
            var amount = Integer.parseInt(number);
            //return (amount > 0 && amount <= expensesList.size()) ? expensesList.subList(expensesList.size() - amount, expensesList.size()) : null;
            return (amount > 0) ? ((amount <= expensesList.size()) ? expensesList.subList(expensesList.size() - amount, expensesList.size()) : expensesList) : null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
