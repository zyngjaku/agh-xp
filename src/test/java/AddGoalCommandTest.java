import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddGoalCommandTest {
    @ParameterizedTest(name = "Name: {0}")
    @CsvSource(value = {"Car", "Big house", "dddd", "A fairly complex name."})
    public void whenAddingValidGoalName_isAccepted(String name) {
        var sut = new AddGoalCommand(new ListRepository<>());
        sut.addGoal(name + " 5.60");
    }

    @ParameterizedTest(name = "Amount: {0}")
    @CsvSource(value = {"5.20", "2000"})
    public void whenAddingValidGoalAmount_isAccepted(String amount) {
        var sut = new AddGoalCommand(new ListRepository<>());
        sut.addGoal("House " + amount);
    }


    @ParameterizedTest(name = "Amount: {0}")
    @CsvSource(value = {"0.00", "-10.99"})
    public void whenGoalTotalIsNotPositive_isRejected(String amount) {
        var sut = new AddGoalCommand(new ListRepository<>());
        assertThrows(IllegalArgumentException.class, () -> {
            sut.addGoal("car " + amount);
        });
    }

    @ParameterizedTest(name = "Amount: {0}")
    @CsvSource(value = {"0.00", "10.999"})
    public void whenNumberOfDecimalPlacesIsIncorrect_isRejected(String amount) {
        var sut = new AddGoalCommand(new ListRepository<>());
        assertThrows(IllegalArgumentException.class, () -> {
            sut.addGoal("car " + amount);
        });
    }

    @Test
    public void whenOnlyOneParameterIsGiven_isRejected() {
        var sut = new AddGoalCommand(new ListRepository<>());
        assertThrows(IllegalArgumentException.class, () -> {
            sut.addGoal("goal");
        });
    }

    @Test
    public void whenAddingValidGoal_goalIsAddedToArray() {
        var repository = new ListRepository<Goal>();
        var sut = new AddGoalCommand(repository);
        sut.addGoal("goal 12");
        assertEquals(1, repository.getAll().size());
    }

    @Test
    public void whenAddingInputWithTrailingAndLeadingSpaces_spacesAreRemoved() {
        var repository = new ListRepository<Goal>();
        var sut = new AddGoalCommand(repository);
        sut.addGoal("        Expensive TV 6999.99  ");
        assertEquals("Expensive TV", repository.getAll().get(0).getTitle());
    }
}