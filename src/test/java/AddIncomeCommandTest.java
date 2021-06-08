import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class AddIncomeCommandTest {

    @Test
    public void whenCorrectValueIsInput_thenCorrectIncomeIsAdded() {
        var repository = new Repository<Income>();
        var sut = new AddIncomeCommand(repository);
        sut.addIncome("2.22");
        assertEquals(repository.getAll().get(0).getValue().compareTo(new BigDecimal("2.22")), 0);
        assertEquals(repository.getAll().get(0).getDate(), Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    @Test
    public void whenCorrectValueIsInputWithDate_thenCorrectIncomeIsAdded() throws ParseException {
        var repository = new Repository<Income>();
        var sut = new AddIncomeCommand(repository);
        sut.addIncome("2.22 22-02-2222");
        assertEquals(repository.getAll().get(0).getValue().compareTo(new BigDecimal("2.22")), 0);
        assertEquals(repository.getAll().get(0).getDate(), new SimpleDateFormat("dd-MM-yyy").parse("22-02-2222"));
    }

    @Test
    public void whenIncorrectValueIsInputWithDate_thenExceptionIsThrown() throws ParseException {
        var repository = new Repository<Income>();
        var sut = new AddIncomeCommand(repository);
        sut.addIncome("2.22 22-02-2222");
        assertThrows(IllegalArgumentException.class, () -> sut.addIncome("sdff"));
        assertThrows(IllegalArgumentException.class, () -> sut.addIncome(""));
        assertThrows(IllegalArgumentException.class, () -> sut.addIncome("120.2 date"));
        assertThrows(IllegalArgumentException.class, () -> sut.addIncome("120.2"));
    }
}
