import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AddExpenseCommand {
    private final BalanceProvider balanceProvider;
    private static Repository<Expense> expenseRepository;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public AddExpenseCommand(BalanceProvider balanceProvider, Repository<Expense> expenseRepository) {
        this.balanceProvider = balanceProvider;
        this.expenseRepository = expenseRepository;
    }


    public void addExpense(String input) {
        try {
            input = input.trim();
            if (input.isEmpty()) {
                throw new IllegalArgumentException("Expense input cannot be empty");
            }

            var split = input.split(" ");
            Date date;
            if (split.length < 2) {
                date =  Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
            } else {
                date = dateFormat.parse(split[1]);
            }

            var expense = new Expense(new BigDecimal(split[0]), date);
            balanceProvider.removeFromBalance(expense.getValue());
            expenseRepository.add(expense);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not parse input");
        }
    }
    public static Repository<Expense> getExpenseRepository() {
        return expenseRepository;
    }
}
