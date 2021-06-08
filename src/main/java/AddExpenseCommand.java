import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

public class AddExpenseCommand {
    private final Repository<Expense> expenseRepository;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public AddExpenseCommand(Repository<Expense> expenseRepository) {
        this.expenseRepository = expenseRepository;
    }


    public void addExpense(String input) {
        try {
            input = input.trim();
            if (input.isEmpty()) {
                throw new IllegalArgumentException("Expense input cannot be empty");
            }

            var split = input.split(" ");
            Date date;
            boolean hasDate = true;
            if (split.length < 2) {
                date =  Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
                hasDate = false;
            } else {
                try {
                    date = dateFormat.parse(split[split.length-1]);
                } catch (Exception e) {
                    hasDate = false;
                    date =  Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
                }
            }
            var categoryStart = 1;
            var categoryEnd = hasDate ? split.length - 1 : split.length;
            String category =  categoryStart >= categoryEnd ? ""
                    : String.join(" ", Arrays.asList(split).subList(categoryStart, categoryEnd));

            var expense = new Expense(new BigDecimal(split[0]), date, category);
            expenseRepository.add(expense);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not parse input");
        }
    }
}
