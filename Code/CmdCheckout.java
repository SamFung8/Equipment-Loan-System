public class CmdCheckout extends RecordedCommand {

    private Member m;
    private Item i;
    private Club c;
    private ItemStatus status;

    @Override
    public void execute(String[] cmdParts) {
        c = c.getInstance();
        try {
            if (c.findMemberID(cmdParts[1])) {
                m = c.findMemberByID(cmdParts[1]);
                if (c.findItemID(cmdParts[2])) {
                    i = c.findItemByID(cmdParts[2]);
                    if (i.getStatus() instanceof ItemStatusAvailable
                            || (i.getStatus() instanceof ItemStatusOnhold && i.getStatus().getID() == m.getID())) {
                        if (m.getCheckoutCount() < 6) {
                            status = i.getStatus();
                            i.setBorrowed(m);
                            addUndoCommand(this);
                            m.setCheckoutCount(m.getCheckoutCount() + 1);
                            clearRedoList();
                            System.out.println("Done.");
                        } else {
                            throw new ExLoanQuotaExceeded();
                        }
                    } else {
                        throw new ExItemNotAvailable();
                    }
                } else {
                    throw new ExItemNotFound();
                }
            } else {
                throw new ExMemberNotFound();
            }
        } catch (ExMemberNotFound e) {
            System.out.println(e.getMessage());
        }catch(ExItemNotFound e){
            System.out.println(e.getMessage());
        }catch(ExItemNotAvailable e){
            System.out.println(e.getMessage());
        }catch(ExLoanQuotaExceeded e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe() {
        i.setStatus(status);
        m.setCheckoutCount(m.getCheckoutCount() - 1);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        i.setBorrowed(m);
        m.setCheckoutCount(m.getCheckoutCount() + 1);
        addUndoCommand(this);
    }

}
