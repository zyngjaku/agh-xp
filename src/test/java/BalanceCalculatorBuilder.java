public class BalanceCalculatorBuilder {

    private final Repository<Income> incomeRepository;
    private final Repository<Expense> expenseRepository;
    private final Repository<CyclicalMoneyTransfer> cyclicalRepository;

    public BalanceCalculatorBuilder() {
        this.incomeRepository = new Repository<>();
        this.expenseRepository = new Repository<>();
        this.cyclicalRepository = new Repository<>();
    }

    public BalanceCalculator build() {
        return new BalanceCalculator(cyclicalRepository, expenseRepository, incomeRepository);
    }

    public BalanceCalculatorBuilder withIncome(Income givenIncome) {
        incomeRepository.add(givenIncome);
        return this;
    }

    public BalanceCalculatorBuilder withExpense(Expense givenExpense) {
        expenseRepository.add(givenExpense);
        return this;
    }

    public BalanceCalculatorBuilder withCyclicalMoneyTransfer(CyclicalMoneyTransfer givenTransfer) {
        cyclicalRepository.add(givenTransfer);
        return this;
    }
}
