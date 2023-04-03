public class ExItemNotBorrowedByThisMember  extends Exception {
    public ExItemNotBorrowedByThisMember() {
        super("The item is not borrowed by this member.");
    }

    public ExItemNotBorrowedByThisMember(String message) {
        super(message);
    }
}
