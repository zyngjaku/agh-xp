public class BalanceView implements View {

    @Override
    public void execute() {
        try {
            var balance = BalanceProvider.getBalance();
            System.out.println("Balance: " + balance);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
