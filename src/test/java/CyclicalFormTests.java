import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class CyclicalFormTests {

    @ParameterizedTest(name = "Amount: {0}")
    @CsvSource(value = {"100", "200", "4000"})
    public void whenAddingValidExpense_isAccepted(int givenAmount) {
        var sut = new CyclicalForm();
        sut.addCyclicalMoneyTransfer(BigDecimal.valueOf(givenAmount), 4);
    }

    @ParameterizedTest(name = "Amount: {0}")
    @CsvSource(value = {"0.0"})
    public void whenAmountIsZero_isRejected(float givenAmount) {
        var sut = new CyclicalForm();
        assertThrows(IllegalArgumentException.class, () -> {
            sut.addCyclicalMoneyTransfer(BigDecimal.valueOf(givenAmount), 30);
        });
    }

    @ParameterizedTest(name = "Days: {0}")
    @CsvSource(value = {"1", "10", "30"})
    public void whenAddingValidNumberOfDays_isAccepted(int givenDays) {
        var sut = new CyclicalForm();
        sut.addCyclicalMoneyTransfer(BigDecimal.valueOf(100), givenDays);
    }

    @ParameterizedTest(name = "Days: {0}")
    @CsvSource(value = {"0", "-10", "-30"})
    public void whenNumberOfDaysIsZeroOrLess_isRejected(int givenDays) {
        var sut = new CyclicalForm();
        assertThrows(IllegalArgumentException.class, () -> {
            sut.addCyclicalMoneyTransfer(BigDecimal.valueOf(100), givenDays);
        });
    }

    @Test
    public void whenAddingCyclicalMoneyTransfer_newObjectIsAdded() {
        var sut = new CyclicalForm();
        var oldSize = sut.moneyTransfers.size();

        sut.addCyclicalMoneyTransfer(BigDecimal.valueOf(123), 30);
        assertEquals(sut.moneyTransfers.size(), oldSize + 1);
    }
}
