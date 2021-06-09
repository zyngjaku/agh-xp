import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingsLogicTests {

    Month month1 = new Month(12, 2020);
    Month month2 = new Month(1, 2021);
    Month month3 = new Month(2, 2021);

    @Test
    public void sum_SumValuesOfNMonthsExpectsPositiveSum() throws ArithmeticException {
        Month[] values = new Month[]{month1, month2, month3};
        values[0].setSavings((3000));
        values[1].setSavings((5000));
        values[2].setSavings((-2500));
        long expected = 5500;
        var suminator = new SavingsLogic();
        long sum = suminator.savings(values);
        assertEquals(expected, sum);
    }

    @Test
    public void sum_SumValuesOfNMonthsExpectsNegativeSum() throws ArithmeticException {
        Month[] values = new Month[]{month1, month2, month3};
        values[0].setSavings((-3000));
        values[1].setSavings((5000));
        values[2].setSavings((-2500));
        long expected = -500;
        var suminator = new SavingsLogic();
        long sum = suminator.savings(values);
        assertEquals(expected, sum);
    }

    @Test
    public void sum_SumValuesOfNMonthsExpectsZeroWithoutMonths() throws ArithmeticException {
        Month[] values = new Month[]{};
        long expected = 0;
        var suminator = new SavingsLogic();
        long sum = suminator.savings(values);
        assertEquals(expected, sum);
    }
}