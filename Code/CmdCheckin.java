import java.util.ArrayList;

public class CmdCheckin extends RecordedCommand {

    private Member m;
    private Item i;
    private Club c;
    private ItemStatus status;
    private ArrayList<Member> list;

    @Override
    public void execute(String[] cmdParts) {
        c = c.getInstance();
        m = c.findMemberByID(cmdParts[1]);
        i = c.findItemByID(cmdParts[2]);
        try {
            if (i.getStatus().getID() == m.getID()) {
                addUndoCommand(this);
                m.setCheckoutCount(m.getCheckoutCount() - 1);
                status = i.getStatus();
                list = new ArrayList<>(i.getRequestList());
                i.nextBorrowedMember();
                clearRedoList();
                System.out.println("Done.");
            } else {
                throw new ExItemNotBorrowedByThisMember();
            }
        } catch (ExItemNotBorrowedByThisMember e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void undoMe() {
        if (i.getStatus() instanceof ItemStatusOnhold) {
            Member onholdMember = c.findMemberByID(i.getStatus().getID());
            onholdMember.setRequestCount(onholdMember.getRequestCount() + 1);
            System.out.println("Sorry. " + onholdMember.getID() + " " + onholdMember.getName()
                    + " please ignore the pick up notice for " + i.getID() + " " + i.getName() + ".");
        }
        i.setStatus(status);
        i.setRequestList(list);
        m.setCheckoutCount(m.getCheckoutCount() + 1);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        status = i.getStatus();
        i.nextBorrowedMember();
        list = new ArrayList<>(i.getRequestList());
        m.setCheckoutCount(m.getCheckoutCount() - 1);
        addUndoCommand(this);
    }

}
