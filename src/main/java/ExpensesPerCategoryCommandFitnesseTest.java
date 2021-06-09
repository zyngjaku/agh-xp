import java.util.List;

public class ExpensesPerCategoryCommandFitnesseTest {
    String[] dates;
    int[] values;
    String[] categories;
    String category;

    public void setDates(String[] dates) {this.dates = dates;}

    public void setValues(int[] values) {this.values = values;}

    public void setCategories(String[] categories) {this.categories = categories;}

    public void setCategory(String category) {this.category = category;}

    public List<String> getExpensesPerCategory() {
        RepositoryProvider repository = new RepositoryProvider();
        SettingsProvider settings = new SettingsProvider();
        AddExpenseCommand cmd = new AddExpenseCommand(repository.getExpenseRepository());
        for (int i=0; i<dates.length; i++) {
            cmd.addExpense(String.format("%d %s %s", values[i], categories[i], dates[i]));
        }
        ExpensesPerCategoryCommand expensesWithinDaysCommand = new ExpensesPerCategoryCommand(repository.getExpenseRepository(), settings);
        return expensesWithinDaysCommand.findByCategory(category);
    }
}
