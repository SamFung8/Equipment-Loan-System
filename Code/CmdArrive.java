public class CmdArrive extends RecordedCommand {

    private Item i;
    private Club c;

    @Override
    public void execute(String[] cmdParts) {
        c = c.getInstance();
        try {
            if (c.findItemID(cmdParts[1])) {
                throw new ExItemIdInUse(
                        "Item ID already in use: " + cmdParts[1] + " " + c.findItemNameByID(cmdParts[1]));
            } else {
                i = new Item(cmdParts[1], cmdParts[2]);
                addUndoCommand(this);
                clearRedoList();
                System.out.println("Done.");
            }
        } catch (ExItemIdInUse e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe() {
        Club c = Club.getInstance();
        c.removeItem(i);
        addRedoCommand(this);

    }

    @Override
    public void redoMe() {
        Club c = Club.getInstance();
        c.addItem(i);
        addUndoCommand(this);

    }

}
