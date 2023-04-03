import java.util.ArrayList;

public class CmdStartNewDay extends RecordedCommand {
    private String sDay1, sDay2;
    private ArrayList<Item> items;

    @Override
    public void execute(String[] cmdParts) {
        SystemDate sd = SystemDate.getInstance();
        sDay1 = sd.toString();
        sDay2 = cmdParts[1];
        sd.set(sDay2);
        checkOnholdPeriod();
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        SystemDate sd = SystemDate.getInstance();
        sd.set(sDay1);
        addRedoCommand(this);

    }

    @Override
    public void redoMe() {
        SystemDate sd = SystemDate.getInstance();
        sd.set(sDay2);
        addUndoCommand(this);

    }

    public void checkOnholdPeriod(){
        Club c = Club.getInstance();
        items = c.getAllItems();
        for (Item i : items) {
            if(i.getStatus() instanceof ItemStatusOnhold){
                Day nowDay = SystemDate.getInstance().clone();
                Day onholDay = i.getStatus().getDay();
                if(Day.checkNext3Day(nowDay, onholDay)){
                    System.out.println("On hold period is over for " + i.getID() + " " + i.getName() + ".");
                    i.nextBorrowedMember();
                }
            }
        }
    }

}
