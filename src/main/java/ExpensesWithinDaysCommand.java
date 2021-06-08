import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;


public class ExpensesWithinDaysCommand {
    private static Repository<Expense> expenseRepository;

    public ExpensesWithinDaysCommand(Repository<Expense> expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public static long diffDates(Date date, Date date2) {
        return ChronoUnit.DAYS.between(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public String getExpensesHistory(String input) {
        try {
            input = input.trim();
            if (input.isEmpty()) {
                throw new IllegalArgumentException("The number of days was not specified");
            }

            var days = Integer.parseInt(input);
            var builder = new StringBuilder();
            Date now = new Date();
            BigDecimal sum = BigDecimal.ZERO;
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

            if (days < 0) {
                throw new IllegalArgumentException("The number of days must be greater than 0");
            }

            for (Expense expense : expenseRepository.getAll()) {
                Date expenseDate = expense.getDate();

                if (diffDates(expenseDate, now) <= days) {
                    BigDecimal expenseValue = expense.getValue();
                    sum = sum.add(expenseValue);
                    builder.append(String.format("Date: %s, value: %d", formatter.format(expenseDate), expenseValue.intValue()));
                    builder.append('\n');
                }
            }

            if (builder.length() != 0) {
                builder.append("Sum of expanses: ").append(sum.intValue()).append("\n");
            } else {
                builder.append("No expanses\n");
            }

            return builder.toString();

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not parse input");
        }
    }

}
