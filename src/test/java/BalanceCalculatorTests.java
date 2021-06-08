import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BalanceCalculatorTests {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Test
    void givenEmptyRepositories_returnZero() throws ParseException {
        var sut = new BalanceCalculatorBuilder().build();

        var expectedBalance = BigDecimal.ZERO;
        var actualBalance = sut.getBalance(getSampleDate());
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void givenExampleIncomes_returnSumOfIncomes() throws ParseException {
        var sut = new BalanceCalculatorBuilder()
                .withIncome(new Income(BigDecimal.valueOf(100), getDateFrom("10-06-2020")))
                .withIncome(new Income(BigDecimal.valueOf(200), getDateFrom("11-06-2020")))
                .build();

        var expectedBalance = BigDecimal.valueOf(300);
        var actualBalance = sut.getBalance(getSampleDate());
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void givenExampleExpenses_returnSumOfExpenses() throws ParseException {
        var sut = new BalanceCalculatorBuilder()
                .withExpense(new Expense(BigDecimal.valueOf(20.49), getDateFrom("10-06-2020")))
                .withExpense(new Expense(BigDecimal.valueOf(180.49), getDateFrom("11-06-2020")))
                .build();

        var expectedBalance = BigDecimal.valueOf(-200.98);
        var actualBalance = sut.getBalance(getSampleDate());
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void givenExampleIncomesAndExpenses_returnExpectedBalance() throws ParseException {
        var sut = new BalanceCalculatorBuilder()
                .withIncome(new Income(BigDecimal.valueOf(1200), getDateFrom("10-06-2020")))
                .withExpense(new Expense(BigDecimal.valueOf(199), getDateFrom("11-06-2020")))
                .withExpense(new Expense(BigDecimal.valueOf(99), getDateFrom("15-06-2020")))
                .build();

        var expectedBalance = BigDecimal.valueOf(902);
        var actualBalance = sut.getBalance(getSampleDate());
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void givenIncomeInTheFuture_returnBalanceWithoutThatIncome() throws ParseException {
        var givenPastIncome = new Income(BigDecimal.valueOf(100), getDateFrom("10-06-2020"));
        var givenFutureIncome = new Income(BigDecimal.valueOf(100), getDateFrom("01-12-2020"));
        var sut = new BalanceCalculatorBuilder()
                .withIncome(givenPastIncome)
                .withIncome(givenFutureIncome)
                .build();

        var expectedBalance = givenPastIncome.getValue();
        var actualBalance = sut.getBalance(getSampleDate());
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void givenExpenseInTheFuture_returnBalanceWithoutThatExpense() throws ParseException {
        var givenPastExpense = new Expense(BigDecimal.valueOf(100), getDateFrom("10-06-2020"));
        var givenFutureExpense = new Expense(BigDecimal.valueOf(100), getDateFrom("01-12-2020"));
        var sut = new BalanceCalculatorBuilder()
                .withExpense(givenPastExpense)
                .withExpense(givenFutureExpense)
                .build();

        var expectedBalance = givenPastExpense.getValue().negate();
        var actualBalance = sut.getBalance(getSampleDate());
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void givenRecentCyclicalTransfer_transferAmountIsSubtractedOnce() throws ParseException {
        var givenTransferAmount = BigDecimal.valueOf(100);
        var givenTransferFirstDate = getDateFrom("17-06-2020");

        var sut = new BalanceCalculatorBuilder()
                .withCyclicalMoneyTransfer(new CyclicalMoneyTransfer(givenTransferAmount, 30, givenTransferFirstDate))
                .build();

        var expectedBalance = givenTransferAmount;
        var actualBalance = sut.getBalance(getSampleDate());
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void givenTwoRecentCyclicalTransfers_returnExpectedBalance() throws ParseException {
        var givenFirstAmount = BigDecimal.valueOf(100);
        var givenOtherAmount = BigDecimal.valueOf(-333);
        var givenTransferFirstDate = getDateFrom("17-06-2020");
        var givenTransferOtherDate = getDateFrom("19-06-2020");

        var sut = new BalanceCalculatorBuilder()
                .withCyclicalMoneyTransfer(new CyclicalMoneyTransfer(givenFirstAmount, 30, givenTransferFirstDate))
                .withCyclicalMoneyTransfer(new CyclicalMoneyTransfer(givenOtherAmount, 7, givenTransferOtherDate))
                .build();

        var expectedBalance = givenFirstAmount.add(givenOtherAmount);
        var actualBalance = sut.getBalance(getSampleDate());
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void givenOldCyclicalTransferThatShouldBeCountedTwice_returnExpectedBalance() throws ParseException {
        var givenTransferAmount = BigDecimal.valueOf(-100);
        var givenTransferDate = getDateFrom("15-05-2020");

        var sut = new BalanceCalculatorBuilder()
                .withCyclicalMoneyTransfer(new CyclicalMoneyTransfer(givenTransferAmount, 30, givenTransferDate))
                .build();

        var expectedBalance = BigDecimal.valueOf(-200);
        var actualBalance = sut.getBalance(getSampleDate());
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void givenCyclicalTransferFromTheFuture_returnUnaffectedBalance() throws ParseException {
        var givenTransferAmount = BigDecimal.valueOf(-100);
        var givenTransferDate = getDateFrom("15-05-2031");

        var sut = new BalanceCalculatorBuilder()
                .withCyclicalMoneyTransfer(new CyclicalMoneyTransfer(givenTransferAmount, 30, givenTransferDate))
                .build();

        var actualBalance = sut.getBalance(getSampleDate());
        assertEquals(BigDecimal.ZERO, actualBalance);
    }

    private Date getDateFrom(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    private Date getSampleDate() throws ParseException {
        return getDateFrom("20-06-2020");
    }
}
