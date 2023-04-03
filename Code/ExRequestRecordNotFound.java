public class ExRequestRecordNotFound extends Exception {
    public ExRequestRecordNotFound() {
        super("Request record is not found.");
    }

    public ExRequestRecordNotFound(String message) {
        super(message);
    }
}
