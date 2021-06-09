public class BalanceView implements View {

    private final BalanceCommand command;

    public BalanceView(BalanceCommand command) {
        this.command = command;
    }

    @Override
    public void execute() {
        try {
            var balance = command.getTodayBalance();
            var currency = System.getProperties().getProperty("currency", new SettingsProvider().getCurrency());
            System.out.println("Balance: " + balance + " " + currency);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
