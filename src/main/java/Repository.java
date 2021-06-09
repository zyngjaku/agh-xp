import java.util.ArrayList;
import java.util.List;

public interface Repository<T> {

    public List<T> getAll();

    public void add(T item);
}