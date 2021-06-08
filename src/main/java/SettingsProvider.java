import java.math.BigDecimal;

public class SettingsProvider {
    private String currency = "USD";
    private String language = "EN";
    private String startingBalance = "0";

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStartingBalance() { return startingBalance; }

    public void setStartingBalance(String startingBalance) {
        this.startingBalance = startingBalance;
    }

    public void deserialize(Serializer serializer) throws SerializationException {
        var newSettings = serializer.loadFromFile();
        setCurrency(newSettings.getCurrency());
        setLanguage(newSettings.getLanguage());
        setStartingBalance(newSettings.getStartingBalance());
        System.setProperty("currency", currency);
        System.setProperty("language", language);
        System.setProperty("startingBalance",startingBalance);
    }

    public void serialize(Serializer serializer) throws SerializationException {
        serializer.writeToFile(this);
    }
}
