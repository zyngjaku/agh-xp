import java.util.ArrayList;
import java.util.List;

public class SavingsLogicFitnesseTest {
    long[] savings;

    public void setSavings(long[] savings) {this.savings = savings;}

    public long savingsTest(){
        SavingsLogic savingsLogic = new SavingsLogic();
        List<Month> months = new ArrayList<Month>();
        for (long value : this.savings){
            Month month = new Month(2021,1);
            month.setSavings((value));
            months.add(month);
        }
        Month[] simpleArray = new Month[ months.size() ];
        months.toArray(simpleArray);
        return savingsLogic.savings(simpleArray);
    }
}
