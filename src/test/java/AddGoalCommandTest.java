import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class AddGoalCommandTest {
    @ParameterizedTest(name = "Name: {0}")
    @CsvSource(value = {"Car", "Big-house", "dddd"})
    public void whenAddingValidGoalName_isAccepted(String name) {
        var sut = new AddGoalCommand(new Repository<>());
        sut.addGoal(name + " 5.6");
    }

    @ParameterizedTest(name = "Amount: {0}")
    @CsvSource(value = {"5.2", "2000"})
    public void whenAddingValidGoalAmount_isAccepted(String amount) {
        var sut = new AddGoalCommand(new Repository<>());
        sut.addGoal("House " + amount);
    }


    @ParameterizedTest(name = "Amount: {0}")
    @CsvSource(value = {"0.0", "-10.99"})
    public void whenGoalTotalIsNotPositive_isRejected(String amount) {
        var sut = new AddGoalCommand(new Repository<>());
        assertThrows(IllegalArgumentException.class, () -> {
            sut.addGoal("car " + amount);
        });
    }

    @Test
    public void whenOnlyOneParameterIsGiven_isRejected() {
        var sut = new AddGoalCommand(new Repository<>());
        assertThrows(IllegalArgumentException.class, () -> {
            sut.addGoal("goal");
        });
    }

    @Test
    public void whenAddingValidGoal_goalIsAddedToArray() {
        var repository = new Repository<Goal>();
        var sut = new AddGoalCommand(repository);
        sut.addGoal("goal 12");
        assertEquals(1, repository.getAll().size());
    }
}