import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ListRepositoryTest {
    @Test
    public void newRepositoryShouldBeEmpty() {
        var repository = new ListRepository<Integer>();
        assertEquals(0, repository.getAll().size());
    }

    @Test
    public void whenAddingItem_RepositoryShouldContainIt() {
        var repository = new ListRepository<Integer>();
        repository.add(2);
        assertEquals(1, repository.getAll().size());
        assertEquals(2, repository.getAll().get(0));
    }
}
