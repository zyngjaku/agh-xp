import java.math.BigDecimal;
public class GoalSummaryCommandFitnesseTest {
    String[] titles;
    int[] value;

    public void setTitles(String[] titles) {this.titles = titles;}

    public void setValue(int[] value) {this.value = value;}

    public String getSummaryText() {
        Repository<Goal> goalRepository = new Repository<>();
        for (int i=0; i<titles.length; i++) {
            goalRepository.add(new Goal(titles[i], BigDecimal.valueOf(value[i])));
        }

        GoalSummaryCommand goalSummaryCommand = new GoalSummaryCommand(new BalanceProvider(), goalRepository);
        return goalSummaryCommand.getSummaryText().replace("\n", "");
    }
}
