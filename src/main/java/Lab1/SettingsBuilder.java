package Lab1;

public class SettingsBuilder {
    private String currency;
    private String language;

    public SettingsBuilder(Builder builder) {
        currency = builder.currency;
        language = builder.language;
    }

    public String getCurrency() {
        return currency;
    }
    public String getLanguage() {
        return language;
    }

    public static final class Builder {
        private String currency;
        private String language;

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public SettingsBuilder build() {
            return new SettingsBuilder(this);
        }
    }
}
