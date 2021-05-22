public class Main {
    public static void main(String[] args) {
        var repositories = new RepositoryProvider();
        repositories.setGoalRepository(new Repository<>());
        repositories.setCyclicalItemRepository(new Repository<>());
        var viewSource = new ViewSource(new StdinInputSource(), repositories, new BalanceProvider());

        while (true) {
            var view = viewSource.processNextCommand();
            if (view == null) {
                break;
            }
            try {
                view.execute();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
