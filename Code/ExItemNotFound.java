public class ExItemNotFound  extends Exception {
    public ExItemNotFound() {
        super("Item not found.");
    }

    public ExItemNotFound(String message) {
        super(message);
    }
}