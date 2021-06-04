import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;


public class ExpensesInMonthCommand {
    private static Repository<Expense> expenseRepository;

    public ExpensesInMonthCommand(Repository<Expense> expenseRepository) {
        this.expenseRepository = expenseRepository;
    }


    public String getExpensesHistory(String input) {
        try {
            input = input.trim();
            if (input.isEmpty()) {
                throw new IllegalArgumentException("Date cannot be empty");
            }

            var split = input.split("-");
            int month;
            int year;
            if (split.length < 2) {
                throw new IllegalArgumentException("Year or month in date is missing");
            } else {
                month = Integer.parseInt(split[0]);
                year = Integer.parseInt(split[1]);
            }

            if (month > 12) {
                throw new IllegalArgumentException("Invalid number of month");
            }

            var builder = new StringBuilder();
            Calendar calendar = Calendar.getInstance();
            BigDecimal sum = BigDecimal.ZERO;

            for (Expense expense : expenseRepository.getAll()) {
                Date date = expense.getDate();
                calendar.setTime(date);
                if (calendar.get(Calendar.MONTH) + 1 == month && calendar.get(Calendar.YEAR) == year) {
                    BigDecimal expenseValue = expense.getValue();
                    sum = sum.add(expenseValue);
                    builder.append(String.format("Day: %d, value: %d", calendar.get(Calendar.DAY_OF_MONTH), expenseValue.intValue()));
                    builder.append('\n');
                }
            }

            if (builder.length() != 0) {
                builder.append("Sum of expanses: ").append(sum.intValue()).append("\n");
            } else {
                builder.append("No expanses in this month\n");
            }

            return builder.toString();

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not parse input");
        }
    }

}
