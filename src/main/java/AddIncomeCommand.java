import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class AddIncomeCommand {
    private final BalanceProvider balanceProvider;
    private final Repository<Income> incomeRepository;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public AddIncomeCommand(BalanceProvider balanceProvider, Repository<Income> incomeRepository) {
        this.balanceProvider = balanceProvider;
        this.incomeRepository = incomeRepository;
    }

    public void addIncome(String input) {
        try {
            input = input.trim();
            if (input.isEmpty()) {
                throw new IllegalArgumentException("Input cannot be empty");
            }

            var split = input.split(" ");
            Date date;
            if (split.length < 2) {
                date =  Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
            } else {
                date = dateFormat.parse(split[1]);
            }

            var income = new Income(new BigDecimal(split[0]), date);
            balanceProvider.addToBalance(income.getValue());
            incomeRepository.add(income);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not parse input");
        }
    }
}
