public class ExSameMemberAlreadyRequest extends Exception {
    public ExSameMemberAlreadyRequest() {
        super("The same member has already requested the item.");
    }

    public ExSameMemberAlreadyRequest(String message) {
        super(message);
    }
}
