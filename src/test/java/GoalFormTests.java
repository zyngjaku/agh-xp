import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class GoalFormTests {


    @ParameterizedTest(name = "Name: {0}")
    @CsvSource(value = {"Car", "Big house", "dddd"})
    public void whenAddingValidGoalName_isAccepted(String name) {
        var repository = new GoalRepository();
        var sut = new GoalForm(repository);
        sut.addGoal(name, BigDecimal.valueOf(5.6));
    }

    @ParameterizedTest(name = "Amount: {0}")
    @CsvSource(value = {"5.2", "2000"})
    public void whenAddingValidGoalAmount_isAccepted(double amount) {
        var repository = new GoalRepository();
        var sut = new GoalForm(repository);
        sut.addGoal("House", BigDecimal.valueOf(amount));
    }


    @ParameterizedTest(name = "Amount: {0}")
    @CsvSource(value = {"0.0", "-10.99"})
    public void whenGoalTotalIsNotPositive_isRejected(double amount) {
        var repository = new GoalRepository();
        var sut = new GoalForm(repository);
        assertThrows(IllegalArgumentException.class, () -> {
            sut.addGoal("car", BigDecimal.valueOf(amount));
        });
    }

    @Test
    public void whenGoalNameIsEmptyString_isRejected() {
        var repository = new GoalRepository();
        var sut = new GoalForm(repository);
        assertThrows(IllegalArgumentException.class, () -> {
            sut.addGoal("", BigDecimal.valueOf(24021.12));
        });
    }

    @Test
    public void whenTitleIsNull_isRejected() {
        var repository = new GoalRepository();
        var sut = new GoalForm(repository);
        assertThrows(IllegalArgumentException.class, () -> {
            sut.addGoal(null, BigDecimal.valueOf(123.456));
        });
    }

    @Test
    public void whenAddingValidGoal_goalIsAddedToArray() {
        var repository = new GoalRepository();
        var sut = new GoalForm(repository);
        sut.addGoal("some goal", BigDecimal.valueOf(71.6));
        assertEquals(1, repository.getAll().size());
    }

}
