import java.math.BigDecimal;

public class AddCyclicalItemCommand {
    public AddCyclicalItemCommand(Repository<CyclicalMoneyTransfer> cyclicalItemRepository) {
        this.cyclicalItemRepository = cyclicalItemRepository;
    }

    private final Repository<CyclicalMoneyTransfer>  cyclicalItemRepository;

    public void addCyclicalItem(String input) {
        var words = input.split(" ");
        if (words.length < 2) {
            throw new IllegalArgumentException("Two parameters required");
        }
        try {
            var amount = new BigDecimal(words[0]);
            var days = Integer.parseInt(words[1]);
            cyclicalItemRepository.add(new CyclicalMoneyTransfer(amount, days));
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Invalid input format: " + e.getMessage());
        }
    }


}
