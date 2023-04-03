public class ExUnknowCommand extends Exception {
    public ExUnknowCommand() {
        super("Unknown command - ignored.");
    }

    public ExUnknowCommand(String message) {
        super(message);
    }
}
