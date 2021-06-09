import java.math.BigDecimal;

public class GoalSummaryFitnesseTest{
    String title;
    BigDecimal total;
    BigDecimal funds;

    public void setTitle(String title){
        this.title = title;
    }

    public void setTotal(String total){
        this.total = new BigDecimal(total);
    }

    public void setFunds(String funds){
        this.funds = new BigDecimal(funds);
    }

    public String getSummaryTest(){
        Goal goal = new Goal(this.title, this.total);
        GoalSummary goalSummary = new GoalSummary(goal, this.funds);
        return goalSummary.getSummary();
    }
}