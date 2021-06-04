public class BalanceView implements View {

    private final BalanceCommand command;

    public BalanceView(BalanceCommand command) {
        this.command = command;
    }

    @Override
    public void execute() {
        try {
            var balance = command.getTodayBalance();
            System.out.println("Balance: " + balance);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
