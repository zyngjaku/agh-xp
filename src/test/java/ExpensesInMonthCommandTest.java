import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpensesInMonthCommandTest {
    @Test
    public void whenExpensesInMonthRange_thenReturnExpenses() {
        var repository = new Repository<Expense>();
        var sut = new AddExpenseCommand(repository);
        var summary = new ExpensesInMonthCommand(repository);
        sut.addExpense("40 03-06-2020");
        sut.addExpense("25 house 07-05-2020");
        var expected = "Day: 3, value: 40,00 USD, category: No category\n" +
                "Sum of expenses: 40.0 USD\n";
        assertEquals(expected, summary.getExpensesHistory("06-2020"));
    }

    @Test
    public void whenExpensesOutOfRangeMonth_thenReturnNoExpenses() {
        var repository = new Repository<Expense>();
        var sut = new AddExpenseCommand(repository);
        var summary = new ExpensesInMonthCommand(repository);
        sut.addExpense("60 01-06-1952");
        sut.addExpense("60 01-07-1951");
        var expected = "No expenses in this month\n";
        assertEquals(expected, summary.getExpensesHistory("06-1951"));
    }

    @Test
    public void whenDateIsNotFullFilled_thenThrowInvalidNumberException() {
        var repository = new Repository<Expense>();
        var summary = new ExpensesInMonthCommand(repository);
        assertThrows(IllegalArgumentException.class, () -> summary.getExpensesHistory("-2019"));
        assertThrows(IllegalArgumentException.class, () -> summary.getExpensesHistory("05-"));
    }

    @Test
    public void whenNumberOfMonthIsNotValid_thenThrowInvalidNumberException() {
        var repository = new Repository<Expense>();
        var summary = new ExpensesInMonthCommand(repository);
        assertThrows(IllegalArgumentException.class, () -> summary.getExpensesHistory("15-2019"));
    }
}