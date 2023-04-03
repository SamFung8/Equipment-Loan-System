public class ExRequestQuotaExceeded extends Exception {
    public ExRequestQuotaExceeded() {
        super("Item request quota exceeded.");
    }

    public ExRequestQuotaExceeded(String message) {
        super(message);
    }
}

