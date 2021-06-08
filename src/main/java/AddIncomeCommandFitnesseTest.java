import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class AddIncomeCommandFitnesseTest {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String input;

    public void setInput(String input) {
        this.input = input;
    }

    public BigDecimal getValue() {
        var repository = new Repository<Income>();
        var sut = new AddIncomeCommand(repository);
        sut.addIncome(input);
        return repository.getAll().get(0).getValue();
    }

    public String getDate() {
        var repository = new Repository<Income>();
        var sut = new AddIncomeCommand(repository);
        sut.addIncome(input);
        return dateFormat.format(repository.getAll().get(0).getDate());
    }
}
