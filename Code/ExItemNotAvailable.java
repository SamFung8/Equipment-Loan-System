public class ExItemNotAvailable extends Exception {
    public ExItemNotAvailable() {
        super("Item not available.");
    }

    public ExItemNotAvailable(String message) {
        super(message);
    }
}
