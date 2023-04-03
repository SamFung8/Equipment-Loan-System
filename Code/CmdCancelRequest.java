import java.util.ArrayList;

public class CmdCancelRequest extends RecordedCommand {

    private Member m;
    private Item i;
    private Club c;
    private ArrayList<Member> list;

    @Override
    public void execute(String[] cmdParts) {

        c = c.getInstance();
        try {
            if (c.findMemberID(cmdParts[1])) {
                m = c.findMemberByID(cmdParts[1]);
                if (c.findItemID(cmdParts[2])) {
                    i = c.findItemByID(cmdParts[2]);
                    if (i.checkMemberInRequestList(m)) {
                        list = new ArrayList<>(i.getRequestList());
                        i.removeRequestList(m);
                        m.setRequestCount(m.getRequestCount() - 1);
                        addUndoCommand(this);
                        clearRedoList();
                        System.out.println("Done.");
                    } else {
                        throw new ExRequestRecordNotFound();
                    }
                } else {
                    throw new ExItemNotFound();
                }
            } else {
                throw new ExMemberNotFound();
            }
        } catch (ExMemberNotFound e) {
            System.out.println(e.getMessage());
        } catch (ExItemNotFound e){
            System.out.println(e.getMessage());
        } catch (ExRequestRecordNotFound e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe() {
        i.setRequestList(list);
        m.setRequestCount(m.getRequestCount() + 1);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        list = new ArrayList<>(i.getRequestList());
        i.removeRequestList(m);
        m.setRequestCount(m.getRequestCount() - 1);
        addUndoCommand(this);
    }

}
