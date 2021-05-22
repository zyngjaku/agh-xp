import java.util.Scanner;

public class StdinInputSource implements InputSource{
    private final Scanner scanner = new Scanner(System.in);
    @Override
    public String read() {
        return scanner.nextLine();
    }
}
