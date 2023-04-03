public class ExItemCurrentlyAvailable extends Exception {
    public ExItemCurrentlyAvailable() {
        super("The item is currently available.");
    }

    public ExItemCurrentlyAvailable(String message) {
        super(message);
    }
}

