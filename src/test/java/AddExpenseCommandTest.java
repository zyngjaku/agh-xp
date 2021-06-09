import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddExpenseCommandTest {
    @Test
    public void whenCorrectValueIsInput_thenCorrectExpenseIsAdded() {
        var repository = new ListRepository<Expense>();
        var sut = new AddExpenseCommand(repository);
        sut.addExpense("2.22");
        assertEquals(repository.getAll().get(0).getValue().compareTo(new BigDecimal("2.22")), 0);
        assertEquals(repository.getAll().get(0).getDate(), Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        assertEquals(repository.getAll().get(0).getCategory(), "No category");
    }

    @Test
    public void whenCorrectValueIsInputWithDate_thenCorrectExpenseIsAdded() throws ParseException {
        var repository = new ListRepository<Expense>();
        var sut = new AddExpenseCommand(repository);
        sut.addExpense("2.22 22-02-2222");
        assertEquals(repository.getAll().get(0).getValue().compareTo(new BigDecimal("2.22")), 0);
        assertEquals(repository.getAll().get(0).getDate(), new SimpleDateFormat("dd-MM-yyy").parse("22-02-2222"));
        assertEquals(repository.getAll().get(0).getCategory(), "No category");
    }

    @Test
    public void whenCorrectValueIsInputWithDateAndCategory_thenCorrectExpenseIsAdded() throws ParseException {
        var repository = new ListRepository<Expense>();
        var sut = new AddExpenseCommand(repository);
        sut.addExpense("2.22 Some category 22-02-2222");
        assertEquals(repository.getAll().get(0).getValue().compareTo(new BigDecimal("2.22")), 0);
        assertEquals(repository.getAll().get(0).getDate(), new SimpleDateFormat("dd-MM-yyy").parse("22-02-2222"));
        assertEquals(repository.getAll().get(0).getCategory(), "Some category");
    }

    @Test
    public void whenCorrectValueIsInputWithCategory_thenCorrectExpenseIsAdded() throws ParseException {
        var repository = new ListRepository<Expense>();
        var sut = new AddExpenseCommand(repository);
        sut.addExpense("2.22 Some category");
        assertEquals(repository.getAll().get(0).getValue().compareTo(new BigDecimal("2.22")), 0);
        assertEquals(repository.getAll().get(0).getDate(), Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        assertEquals(repository.getAll().get(0).getCategory(), "Some category");
    }

    @Test
    public void whenIncorrectValueIsInputWithDate_thenExceptionIsThrown() throws ParseException {
        var repository = new ListRepository<Expense>();
        var sut = new AddExpenseCommand(repository);
        sut.addExpense("2.22 22-02-2222");
        assertThrows(IllegalArgumentException.class, () -> sut.addExpense("sdff"));
        assertThrows(IllegalArgumentException.class, () -> sut.addExpense(""));
        assertThrows(IllegalArgumentException.class, () -> sut.addExpense("120.2 date"));
    }
}
