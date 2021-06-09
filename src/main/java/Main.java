public class Main {
    public static void main(String[] args) {
        var repositories = new RepositoryProvider();
        repositories.setGoalRepository(new ListRepository<>());
        repositories.setCyclicalItemRepository(new ListRepository<>());
        repositories.setIncomeRepository(new ListRepository<>());
        repositories.setExpenseRepository(new ListRepository<>());
        var settingsProvider = new SettingsProvider();
        var balanceCalculator = new BalanceCalculator(
                repositories.getCyclicalItemRepository(),
                repositories.getExpenseRepository(),
                repositories.getIncomeRepository()
        );
        var viewSource = new ViewSource(new StdinInputSource(), repositories, settingsProvider, balanceCalculator);


        try {
            viewSource.getSettingsView().execute();
            new HelpView().execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        while (true) {
            var view = viewSource.processNextCommand();
            if (view == null) {
                break;
            }
            try {
                view.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
