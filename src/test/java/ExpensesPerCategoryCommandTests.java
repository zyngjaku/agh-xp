import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;


public class ExpensesPerCategoryCommandTests {
    private final SettingsProvider mockSettingsProvider = new SettingsProvider();

    @Test
    public void givenNoCategoriesUnderGivenCategory_returnEmptyList() {
        var expenseRepository = new ListRepository<Expense>();
        var sut = new ExpensesPerCategoryCommand(expenseRepository, mockSettingsProvider);

        var results = sut.findByCategory("Sample category");
        assertEquals(0, results.size());
    }

    @Test
    public void givenSingleExpenseUnderSearchedCategory_returnSingleItem() throws ParseException {
        var expenseRepository = new ListRepository<Expense>();
        expenseRepository.add(new Expense(new BigDecimal(123), getDateFrom("21-01-2021"), "Category A"));
        var sut = new ExpensesPerCategoryCommand(expenseRepository, mockSettingsProvider);

        var results = sut.findByCategory("Category A");
        assertEquals(1, results.size());
    }

    @Test
    public void givenExpensesFromDifferentCategories_returnOnlyExpensesFromExpectedCategory() throws ParseException {
        var expenseRepository = new ListRepository<Expense>();
        expenseRepository.add(new Expense(new BigDecimal(123), getDateFrom("21-01-2021"), "Category A"));
        expenseRepository.add(new Expense(new BigDecimal(200), getDateFrom("25-02-2021"), "Category B"));
        expenseRepository.add(new Expense(new BigDecimal(300), getDateFrom("23-03-2021"), "Category B"));
        expenseRepository.add(new Expense(new BigDecimal(4199), getDateFrom("29-01-2021"), "Category A"));
        expenseRepository.add(new Expense(new BigDecimal(99), getDateFrom("30-02-2021"), "Category A"));
        var sut = new ExpensesPerCategoryCommand(expenseRepository, mockSettingsProvider);

        var results = sut.findByCategory("Category A");
        assertEquals(3, results.size());
    }

    @Test
    public void givenMultipleCategoriesAndAskingForNonexistentCategory_returnNoResults() throws ParseException {
        var expenseRepository = new ListRepository<Expense>();
        expenseRepository.add(new Expense(new BigDecimal(123), getDateFrom("21-01-2021"), "Category A"));
        expenseRepository.add(new Expense(new BigDecimal(200), getDateFrom("25-02-2021"), "Category B"));
        expenseRepository.add(new Expense(new BigDecimal(300), getDateFrom("23-03-2021"), "Category B"));
        expenseRepository.add(new Expense(new BigDecimal(4199), getDateFrom("29-01-2021"), "Category C"));
        expenseRepository.add(new Expense(new BigDecimal(99), getDateFrom("30-02-2021"), "Category C"));
        var sut = new ExpensesPerCategoryCommand(expenseRepository, mockSettingsProvider);

        var results = sut.findByCategory("Nonexistent");
        assertEquals(0, results.size());
    }

    @Test
    public void givenEmptyCategoryName_throwInvalidArgumentException() {
        var expenseRepository = spy(new ListRepository<Expense>());

        var sut = new ExpensesPerCategoryCommand(expenseRepository, mockSettingsProvider);

        assertThrows(IllegalArgumentException.class, () -> {
            sut.findByCategory("");
        });
    }

    @Test
    public void givenNullCategoryName_throwInvalidArgumentException() {
        var expenseRepository = spy(new ListRepository<Expense>());

        var sut = new ExpensesPerCategoryCommand(expenseRepository, mockSettingsProvider);

        assertThrows(IllegalArgumentException.class, () -> {
            sut.findByCategory(null);
        });
    }

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    private Date getDateFrom(String date) throws ParseException {
        return dateFormat.parse(date);
    }
}
