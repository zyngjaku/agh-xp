public class ExpensesInMonthCommandFitnesseTest {
    String[] dates;
    int[] values;
    String[] categories;
    String month;

    public void setDates(String[] dates) {this.dates = dates;}

    public void setValues(int[] values) {this.values = values;}

    public void setCategories(String[] categories) {this.categories = categories;}

    public void setMonth(String month) {this.month = month;}

    public String getExpensesHistory() {
        RepositoryProvider repository  = new RepositoryProvider();
        AddExpenseCommand cmd = new AddExpenseCommand(repository.getExpenseRepository());
        for (int i=0; i<dates.length; i++) {
            cmd.addExpense(String.format("%d %s %s", values[i], categories[i], dates[i]));
        }

        ExpensesInMonthCommand expensesInMonthCommand = new ExpensesInMonthCommand(repository.getExpenseRepository());
        return expensesInMonthCommand.getExpensesHistory(month).replace("\n", "");
    }
}