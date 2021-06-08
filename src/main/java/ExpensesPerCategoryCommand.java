import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class ExpensesPerCategoryCommand {
    private final Repository<Expense> expenseRepository;
    private final SettingsProvider settingsProvider;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

    public ExpensesPerCategoryCommand(Repository<Expense> expenseRepository, SettingsProvider settingsProvider) {
        this.expenseRepository = expenseRepository;
        this.settingsProvider = settingsProvider;
    }

    public List<String> findByCategory(String categoryName) {
        if (categoryName == null || categoryName.isEmpty()) {
            throw new IllegalArgumentException("Category name must not be null or empty.");
        }

        return getExpensesForCategory(categoryName).stream()
                .map(this::stringifyExpense)
                .collect(Collectors.toList());
    }

    private String stringifyExpense(Expense expense) {
        var date = dateFormatter.format(expense.getDate());
        return String.format("Date: %s, value: %s %s", date, expense.getValue(), settingsProvider.getCurrency());
    }

    private List<Expense> getExpensesForCategory(String categoryName) {
        var expenses = expenseRepository.getAll().stream()
                .filter((expense -> expense.getCategory().equals(categoryName)));
        return expenses.collect(Collectors.toList());
    }
}
