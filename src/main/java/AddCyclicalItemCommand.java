import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AddCyclicalItemCommand {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public AddCyclicalItemCommand(Repository<CyclicalMoneyTransfer> cyclicalItemRepository) {
        this.cyclicalItemRepository = cyclicalItemRepository;
    }

    private final Repository<CyclicalMoneyTransfer> cyclicalItemRepository;

    public void addCyclicalItem(String input) {
        var words = input.split(" ");
        if (words.length < 2) {
            throw new IllegalArgumentException("Two parameters required");
        }
        try {
            var amount = new BigDecimal(words[0]);
            var days = Integer.parseInt(words[1]);
            var dateOfFirstTransfer = getDateOfFirstTransfer(words);

            cyclicalItemRepository.add(new CyclicalMoneyTransfer(amount, days, dateOfFirstTransfer));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid input format: " + e.getMessage());
        }
    }

    private Date getDateOfFirstTransfer(String[] words) throws ParseException {
        Date date;
        if (words.length < 3) {
            date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        } else {
            date = dateFormat.parse(words[2]);
        }
        return date;
    }


}
