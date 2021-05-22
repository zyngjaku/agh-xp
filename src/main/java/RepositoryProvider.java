public class RepositoryProvider {
    private Repository<CyclicalMoneyTransfer> cyclicalItemRepository;
    private Repository<Goal> goalRepository;

    public Repository<Goal> getGoalRepository() {
        return goalRepository;
    }

    public void setGoalRepository(Repository<Goal> goalRepository) {
        this.goalRepository = goalRepository;
    }

    public void setCyclicalItemRepository(Repository<CyclicalMoneyTransfer> cyclicalItemRepository) {
        this.cyclicalItemRepository = cyclicalItemRepository;
    }

    public Repository<CyclicalMoneyTransfer> getCyclicalItemRepository() {
        return cyclicalItemRepository;
    }
}
