public class ExpensesWithinDaysCommandFitnesseTest {
    String[] dates;
    int[] values;
    String[] categories;
    String days;

    public void setDates(String[] dates) {this.dates = dates;}

    public void setValues(int[] values) {this.values = values;}

    public void setCategories(String[] categories) {this.categories = categories;}

    public void setDays(String days) {this.days = days;}

    public String getExpensesHistory() {
        RepositoryProvider repository  = new RepositoryProvider();
        AddExpenseCommand cmd = new AddExpenseCommand(repository.getExpenseRepository());
        for (int i=0; i<dates.length; i++) {
            cmd.addExpense(String.format("%d %s %s", values[i], categories[i], dates[i]));
        }

        ExpensesWithinDaysCommand expensesWithinDaysCommand = new ExpensesWithinDaysCommand(repository.getExpenseRepository());
        return expensesWithinDaysCommand.getExpensesHistory(days).replace("\n", "");
    }
}
