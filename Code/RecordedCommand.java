import java.util.*;

public abstract class RecordedCommand implements Command{
    private static ArrayList<RecordedCommand> undoList = new ArrayList<>();
    private static ArrayList<RecordedCommand> redoList = new ArrayList<>();

    public abstract void undoMe();

    public abstract void redoMe();

    protected static void addUndoCommand(RecordedCommand cmd) {
        undoList.add(cmd);
    }

    protected static void addRedoCommand(RecordedCommand cmd) {
        redoList.add(cmd);
    }

    protected static void clearRedoList() {
        redoList.clear();
    }

    public static void undoOneCommand() {
        if (undoList.size() >= 1)
            undoList.remove(undoList.size() - 1).undoMe();
        else
            System.out.println("Nothing to undo.");
    }

    public static void redoOneCommand() {
        if (redoList.size() >= 1)
            redoList.remove(redoList.size() - 1).redoMe();
        else
            System.out.println("Nothing to redo.");
    }
}
