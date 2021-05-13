import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class GoalFormTests {


    @ParameterizedTest(name = "Name: {0}")
    @CsvSource(value = {"Car", "Big house", "dddd"})
    public void whenAddingValidGoalName_isAccepted(String name) {
        var sut = new GoalForm();
        sut.addGoal(name, 5.6);
    }

    @ParameterizedTest(name = "Amount: {0}")
    @CsvSource(value = {"5.2", "2000"})
    public void whenAddingValidGoalAmou_isAccepted(double amount) {
        var sut = new GoalForm();
        sut.addGoal("House", amount);
    }


    @ParameterizedTest(name = "Amount: {0}")
    @CsvSource(value = {"0.0", "-10.99"})
    public void whenGoalTotalIsNotPositive_isRejected(double amount) {
        var sut = new GoalForm();
        assertThrows(IllegalArgumentException.class, () -> {
            sut.addGoal("Invalid goal", amount);
        });
    }

    @ParameterizedTest(name = "Amount: {0}")
    @CsvSource(value = {"5.3", "123.1", "30.123", "99.9999"})
    public void whenAddingAmountWithOneOrMoreThanTwoDecimalPlaces_isRejected(String givenAmount) {
        var sut = new CyclicalForm();
        assertThrows(IllegalArgumentException.class, () -> {
            sut.addCyclicalMoneyTransfer(new BigDecimal(givenAmount), 7);
        });
    }

}
