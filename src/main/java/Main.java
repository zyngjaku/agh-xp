import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        while (true) {
            var view = viewSource.processNextCommand();
            if (view == null) {
                break;
            }
            try {
                view.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
