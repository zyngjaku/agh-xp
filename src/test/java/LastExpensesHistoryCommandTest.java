import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LastExpensesHistoryCommandTest {

    List<Expense> expensesList = new ArrayList<>();

    private void addExpenses() throws ParseException {
        expensesList.add(new Expense(new BigDecimal("240"), new SimpleDateFormat("dd-MM-yyy").parse("22-02-2021")));
        expensesList.add(new Expense(new BigDecimal("450"), new SimpleDateFormat("dd-MM-yyy").parse("11-05-2021")));
        expensesList.add(new Expense(new BigDecimal("666"), new SimpleDateFormat("dd-MM-yyy").parse("12-05-2021")));
        expensesList.add(new Expense(new BigDecimal("730"), new SimpleDateFormat("dd-MM-yyy").parse("13-02-2021")));
        expensesList.add(new Expense(new BigDecimal("340"), new SimpleDateFormat("dd-MM-yyy").parse("14-04-2021")));
    }

    @Test
    public void whenExpensesArePresent_thenReturnListOfExpenses() throws ParseException {
        addExpenses();
        var expenses = LastExpensesHistoryCommand.getLastExpenses("3", expensesList);
        assert expenses != null;
        assertEquals(expenses.size(), 3);
    }

    @Test
    public void whenTheNumberOfExpensesIsLessThanPassedArgument_thenThrowException() throws ParseException {
        addExpenses();
        var expenses = LastExpensesHistoryCommand.getLastExpenses("6", expensesList);
        assertThrows(NullPointerException.class, () -> expenses.size());
    }

    @Test
    public void whenTheArgumentIsIncorrect_thenThrowException() throws ParseException {
        addExpenses();
        var expenses = LastExpensesHistoryCommand.getLastExpenses("lolo", expensesList);
        assertThrows(NullPointerException.class, () -> expenses.size());
    }
}
