public class CmdRequest extends RecordedCommand {
    private Member m;
    private Item i;
    private Club c;

    @Override
    public void execute(String[] cmdParts) {
        c = c.getInstance();
        try {
            if (c.findMemberID(cmdParts[1])) {
                m = c.findMemberByID(cmdParts[1]);
                if (c.findItemID(cmdParts[2])) {
                    i = c.findItemByID(cmdParts[2]);
                    if (i.checkMemberInRequestList(m)) {
                        throw new ExSameMemberAlreadyRequest();
                    } else {
                        if (i.getStatus() instanceof ItemStatusAvailable
                                || (i.getStatus() instanceof ItemStatusOnhold && i.getStatus().getID() == m.getID())) {
                            throw new ExItemCurrentlyAvailable();
                        } else {
                            if (i.getStatus() instanceof ItemStatusBorrowed
                                    || i.getStatus() instanceof ItemStatusOnhold) {
                                if (i.getStatus().getID() == m.getID()) {
                                    throw new ExItemBorrowedSameMember();
                                } else {
                                    if (m.getRequestCount() < 3) {
                                        m.setRequestCount(m.getRequestCount() + 1);
                                        i.addRequestList(m);
                                        System.out.println("Done. This request is no. " + i.getRequestList().size()
                                                + " in the queue.");
                                        addUndoCommand(this);
                                        clearRedoList();
                                    } else {
                                        throw new ExRequestQuotaExceeded();
                                    }
                                }
                            }
                        }
                    }
                } else {
                    throw new ExItemNotFound();
                }
            } else {
                throw new ExMemberNotFound();
            }
        } catch (ExMemberNotFound e) {
            System.out.println(e.getMessage());
        } catch (ExItemNotFound e) {
            System.out.println(e.getMessage());
        } catch (ExSameMemberAlreadyRequest e) {
            System.out.println(e.getMessage());
        } catch (ExItemCurrentlyAvailable e) {
            System.out.println(e.getMessage());
        } catch (ExItemBorrowedSameMember e) {
            System.out.println(e.getMessage());
        } catch (ExRequestQuotaExceeded e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe() {
        i.removeRequestList(m);
        m.setRequestCount(m.getRequestCount() - 1);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        i.addRequestList(m);
        m.setRequestCount(m.getRequestCount() + 1);
        addUndoCommand(this);
    }

}
