public class ExLoanQuotaExceeded extends Exception {
    public ExLoanQuotaExceeded() {
        super("Loan quota exceeded.");
    }

    public ExLoanQuotaExceeded(String message) {
        super(message);
    }
}
