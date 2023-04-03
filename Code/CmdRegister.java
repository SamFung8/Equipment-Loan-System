public class CmdRegister extends RecordedCommand {

    private Member m;
    private Club c;

    @Override
    public void execute(String[] cmdParts) {
        c = c.getInstance();
        try {
            if (c.findMemberID(cmdParts[1])) {
                throw new ExMemberIdInUse(
                        "Member ID already in use: " + cmdParts[1] + " " + c.findMemberNameByID(cmdParts[1]));
            } else {
                m = new Member(cmdParts[1], cmdParts[2]);
                addUndoCommand(this);
                clearRedoList();
                System.out.println("Done.");
            }
        } catch (ExMemberIdInUse e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe() {
        Club c = Club.getInstance();
        c.removeMember(m);
        addRedoCommand(this);

    }

    @Override
    public void redoMe() {
        Club c = Club.getInstance();
        c.addMember(m);
        addUndoCommand(this);

    }

}
