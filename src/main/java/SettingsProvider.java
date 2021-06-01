public class SettingsProvider {
    private String currency = "USD";
    private String language = "EN";

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

    public void deserialize(Serializer serializer) throws SerializationException {
        var newSettings = serializer.loadFromFile();
        setCurrency(newSettings.getCurrency());
        setLanguage(newSettings.getLanguage());
    }

    public void serialize(Serializer serializer) throws SerializationException {
        serializer.writeToFile(this);
    }
}