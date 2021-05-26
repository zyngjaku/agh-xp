import java.util.Scanner;

public class SettingsView implements View {

    private final SettingsCommand settingsCommand;

    public SettingsView(SettingsCommand settingsCommand) {

        this.settingsCommand = settingsCommand;
    }

    @Override
    public void execute() {
        var scanner = new Scanner(System.in);
        System.out.println("'save {filename}' to save current configuration\n'load {filename}' to load existing configuration\nEnter to retain current (default) configuration");
        try {
            settingsCommand.handleInput(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
