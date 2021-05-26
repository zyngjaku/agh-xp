public class InvalidInputView implements View {
    private final String reason;

    public InvalidInputView(String reason) {
        this.reason = reason;
    }

    @Override
    public void execute() {
        System.out.println(reason);
    }
}
