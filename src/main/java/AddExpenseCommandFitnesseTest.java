import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class AddExpenseCommandFitnesseTest {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String input;

    public void setInput(String input) {
        this.input = input;
    }

    public BigDecimal getValue() {
        var repository = new Repository<Expense>();
        var sut = new AddExpenseCommand(repository);
        sut.addExpense(input);
        return repository.getAll().get(0).getValue();
    }

    public String getDate() {
        var repository = new Repository<Expense>();
        var sut = new AddExpenseCommand(repository);
        sut.addExpense(input);
        return dateFormat.format(repository.getAll().get(0).getDate());
    }
}
