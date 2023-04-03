public class ExMemberNotFound extends Exception {
    public ExMemberNotFound() {
        super("Member not found.");
    }

    public ExMemberNotFound(String message) {
        super(message);
    }
}
