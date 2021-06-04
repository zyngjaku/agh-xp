import java.util.List;

public class LastExpensesHistoryCommand {

    /**
     * Zwracanie X ostatnich wydatków
     *
     * @param number       - Ilość wydatków do wyświetlenia
     * @param expensesList - Lista wszystkich wydatków
     * @return - zwraca listę X ostatnich wydaktów
     */
    public static List<Expense> getLastExpenses(String number, List<Expense> expensesList) {
        try {
            var amount = Integer.parseInt(number);
            //return (amount > 0 && amount <= expensesList.size()) ? expensesList.subList(expensesList.size() - amount, expensesList.size()) : null;
            return (amount > 0) ? ((amount <= expensesList.size()) ? expensesList.subList(expensesList.size() - amount, expensesList.size()) : expensesList) : null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
