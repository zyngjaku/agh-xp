public class SettingsCommand {
    private final SettingsProvider settingsProvider;

    public SettingsCommand(SettingsProvider settingsProvider) {
        this.settingsProvider = settingsProvider;
    }

    public void handleInput(String nextLine) {
        clearSettings();

        if (nextLine == null || nextLine.isEmpty()) {
            return;
        }
        var split = nextLine.split(" ");
        if (split.length == 0) {
            return;
        }
        var cmd = split[0];
        if (cmd.equals("save") || cmd.equals("load")) {
            if (split.length < 2) {
                throw new IllegalArgumentException("Path is not supplied");
            }
            var serializer = new Serializer(split[1]);

            try {
                if (cmd.equals("save")) {
                    settingsProvider.serialize(serializer);
                } else {
                    settingsProvider.deserialize(serializer);
                }
            } catch (SerializationException exc) {
                System.out.println("Failed to load or save settings: " + exc.getMessage());
            }
        }
    }

    private void clearSettings() {
        System.clearProperty("currency");
        System.clearProperty("language");
        System.clearProperty("startingBalance");
    }
}
