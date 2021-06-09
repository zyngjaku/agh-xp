import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ExpensesWithinDaysCommandTest {
    @Test
    public void whenExpensesInRangeOfDays_thenReturnExpensesWithinGivenDays() {
        var repository = new ListRepository<Expense>();
        var sut = new AddExpenseCommand(repository);
        var summary = new ExpensesWithinDaysCommand(repository);
        sut.addExpense("10 bike 02-06-2021");
        sut.addExpense("60 01-06-1952");
        var expected = "Date: 02-06-2021, value: 10,00 USD, category: bike\n" +
                "Sum of expenses: 10.0 USD\n";
        assertEquals(expected, summary.getExpensesHistory("50"));
    }

    @Test
    public void whenExpensesOutOfRangeOfDays_thenReturnNoExpenses() {
        var repository = new ListRepository<Expense>();
        var sut = new AddExpenseCommand(repository);
        var summary = new ExpensesWithinDaysCommand(repository);
        sut.addExpense("60 01-06-1952");
        var expected = "No expenses\n";
        assertEquals(expected, summary.getExpensesHistory("50"));
    }

    @Test
    public void whenDaysRangeIsNegative_thenThrowInvalidNumberException() {
        var repository = new ListRepository<Expense>();
        var summary = new ExpensesWithinDaysCommand(repository);
        assertThrows(IllegalArgumentException.class, () -> summary.getExpensesHistory("-3"));
    }
}