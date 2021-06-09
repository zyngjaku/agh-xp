import java.util.ArrayList;
import java.util.List;

public class ListRepository<T> implements Repository<T> {

    private final List<T> items = new ArrayList<>();

    public List<T> getAll() {
        return items;
    }

    public void add(T item) {
        items.add(item);
    }
}