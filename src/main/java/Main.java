public class Main {
    public static void main(String[] args) {
        var repositories = new RepositoryProvider();
        repositories.setGoalRepository(new Repository<>());
        repositories.setCyclicalItemRepository(new Repository<>());
        repositories.setIncomeRepository(new Repository<>());
        repositories.setExpenseRepository(new Repository<>());
        var settingsProvider = new SettingsProvider();
        var balanceCalculator = new BalanceCalculator(
                repositories.getCyclicalItemRepository(),
                repositories.getExpenseRepository(),
                repositories.getIncomeRepository()
        );
        var viewSource = new ViewSource(new StdinInputSource(), repositories, new BalanceProvider(), settingsProvider, balanceCalculator);


        try {
            viewSource.getSettingsView().execute();
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
