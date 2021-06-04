import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ExpensesWithinDaysCommandTest {
    @Test
    public void whenExpensesInRangeOfDays_thenReturnExpensesWithinGivenDays() {
        var balanceProvider = new BalanceProvider();
        var repository = new Repository<Expense>();
        var sut = new AddExpenseCommand(balanceProvider, repository);
        var summary = new ExpensesWithinDaysCommand(repository);
        sut.addExpense("10 02-06-2021");
        sut.addExpense("60 01-06-1952");
        var expected = "Date: 02-06-2021, value: 10\n" +
                "Sum of expanses: 10\n";
        assertEquals(expected, summary.getExpensesHistory("50"));
    }

    @Test
    public void whenExpensesOutOfRangeOfDays_thenReturnNoExpenses() {
        var balanceProvider = new BalanceProvider();
        var repository = new Repository<Expense>();
        var sut = new AddExpenseCommand(balanceProvider, repository);
        var summary = new ExpensesWithinDaysCommand(repository);
        sut.addExpense("60 01-06-1952");
        var expected = "No expanses\n";
        assertEquals(expected, summary.getExpensesHistory("50"));
    }

    @Test
    public void whenDaysRangeIsNegative_thenThrowInvalidNumberException() {
        var repository = new Repository<Expense>();
        var summary = new ExpensesWithinDaysCommand(repository);
        assertThrows(IllegalArgumentException.class, () -> summary.getExpensesHistory("-3"));
    }
}