import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LastExpensesHistoryCommandTest {

    private Repository<Expense> createExpensesRepository() throws ParseException {
        var expenses = new ListRepository<Expense>();
        var format = new SimpleDateFormat("dd-MM-yyy");
        expenses.add(new Expense(new BigDecimal("240"), format.parse("22-02-2021")));
        expenses.add(new Expense(new BigDecimal("450"), format.parse("11-05-2021")));
        expenses.add(new Expense(new BigDecimal("666"), format.parse("12-05-2021")));
        expenses.add(new Expense(new BigDecimal("730"), format.parse("13-02-2021")));
        expenses.add(new Expense(new BigDecimal("340"), format.parse("14-04-2021")));
        return expenses;
    }

    @Test
    public void whenExpensesArePresent_thenReturnListOfExpenses() throws ParseException {
        var command = new LastExpensesHistoryCommand(createExpensesRepository());
        var expenses = command.getLastExpenses("3");
        assert expenses != null;
        assertEquals(expenses.size(), 3);
    }

    @Test
    public void whenTheNumberOfExpensesIsLessThanPassedArgument_thenReturnAllExpenses() throws ParseException {
        var command = new LastExpensesHistoryCommand(createExpensesRepository());
        var expenses = command.getLastExpenses("6");
        assert expenses != null;
        assertEquals(expenses.size(), 5);
    }

    @Test
    public void whenTheArgumentIsIncorrect_thenThrowException() throws ParseException {
        var command = new LastExpensesHistoryCommand(createExpensesRepository());
        var expenses = command.getLastExpenses("lolo");
        assertThrows(NullPointerException.class, () -> expenses.size());
    }
}
