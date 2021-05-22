import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AddCyclicalItemCommandTest {
    @ParameterizedTest(name = "Amount: {0}")
    @CsvSource(value = {"100", "200", "4000"})
    public void whenAddingValidExpense_isAccepted(String givenAmount) {
        var sut = new AddCyclicalItemCommand(new Repository<>());
        sut.addCyclicalItem(givenAmount + " 4");
    }

    @ParameterizedTest(name = "Amount: {0}")
    @CsvSource(value = {"0.0"})
    public void whenAmountIsZero_isRejected(String givenAmount) {
        var sut = new AddCyclicalItemCommand(new Repository<>());
        assertThrows(IllegalArgumentException.class, () -> {
            sut.addCyclicalItem(givenAmount + " 30");
        });
    }

    @ParameterizedTest(name = "Days: {0}")
    @CsvSource(value = {"1", "10", "30"})
    public void whenAddingValidNumberOfDays_isAccepted(String givenDays) {
        var sut = new AddCyclicalItemCommand(new Repository<>());
        sut.addCyclicalItem("10 " + givenDays);
    }

    @ParameterizedTest(name = "Days: {0}")
    @CsvSource(value = {"0", "-10", "-30"})
    public void whenNumberOfDaysIsZeroOrLess_isRejected(String givenDays) {
        var sut = new AddCyclicalItemCommand(new Repository<>());
        assertThrows(IllegalArgumentException.class, () -> {
            sut.addCyclicalItem("10 " + givenDays);
        });
    }

    @ParameterizedTest(name = "Amount: {0}")
    @CsvSource(value = {"5.00", "123.99", "30000.05"})
    public void whenAddingAmountWithTwoDecimalPlaces_isAccepted(String givenAmount) {
        var sut = new AddCyclicalItemCommand(new Repository<>());
        sut.addCyclicalItem(givenAmount + " 30");
    }

    @ParameterizedTest(name = "Amount: {0}")
    @CsvSource(value = {"5.3", "123.1", "30.123", "99.9999"})
    public void whenAddingAmountWithOneOrMoreThanTwoDecimalPlaces_isRejected(String givenAmount) {
        var sut = new AddCyclicalItemCommand(new Repository<>());
        assertThrows(IllegalArgumentException.class, () -> {
            sut.addCyclicalItem(givenAmount + " 3");
        });
    }

    @Test
    public void whenAddingCyclicalMoneyTransfer_newObjectIsAdded() {
        var repo = new Repository<CyclicalMoneyTransfer>();
        var sut = new AddCyclicalItemCommand(repo);
        var oldSize = repo.getAll().size();

        sut.addCyclicalItem("222 2");
        assertEquals(repo.getAll().size(), oldSize + 1);
    }
}