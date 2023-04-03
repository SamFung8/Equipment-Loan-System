public class ExItemBorrowedSameMember extends Exception {
    public ExItemBorrowedSameMember() {
        super("The item is already borrowed by the same member.");
    }

    public ExItemBorrowedSameMember(String message) {
        super(message);
    }
}
