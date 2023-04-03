import java.util.*;

public class Item implements Comparable<Item> {
    private String id;
    private String name;
    private Day arrivalDate;
    private ItemStatus status;
    private ArrayList<Member> requestList;


    public Item(String id, String name){
        this.id =id;
        this.name = name;
        this.arrivalDate = SystemDate.getInstance().clone();
        this.status = new ItemStatusAvailable();
        this.requestList = new ArrayList<>();
        Club.getInstance().addItem(this);
    }

    public String getID() {
        return id;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        // Learn: "-" means left-aligned
        if((status instanceof ItemStatusBorrowed || status instanceof ItemStatusOnhold) && requestList.size() > 0){
            String line = "";
            for (Member l : requestList) {
                line += " " + l.getID();
            }
            return String.format("%-5s%-17s%11s   %s", id, name, arrivalDate, status.getStatusDescription() +  " + " + requestList.size() + " request(s):" + line);
        }else{
            return String.format("%-5s%-17s%11s   %s", id, name, arrivalDate, status.getStatusDescription());
        }
    }

    public static String getListingHeader() {
        return String.format("%-5s%-17s%11s   %s", "ID", "Name", "  Arrival  ", "Status");  
    }

    public int compareTo(Item another) {
        return this.id.compareTo(another.id);
    }

    public void setBorrowed(Member m){
        status = new ItemStatusBorrowed(m.getID(),m.getName());
    }

    public ItemStatus getStatus(){
        return status;
    }

    public void setStatus(ItemStatus status){
        this.status = status;
    }

    public void setAvailable() {
        this.status = new ItemStatusAvailable();
    }

    public void addRequestList(Member m){
        requestList.add(m);
    }

    public ArrayList<Member> getRequestList(){
        return requestList;
    }

    public void removeRequestList(Member m) {
        requestList.remove(m);
    }

    public boolean checkMemberInRequestList(Member m){
        for (Member m1 : requestList) {
            if(m == m1)
                return true;
        }

        return false;
    }

    public void nextBorrowedMember() {
        if(requestList.size() > 0){
            Member m = requestList.get(0);
            this.setBorrowed(m);
            requestList.remove(0);
            Day next3Day = SystemDate.getInstance().clone();
            next3Day.setNext3Day();
            System.out.println("Item [" + this.getID() + " " + this.getName() + "] is ready for pick up by [" + m.getID() + " " + m.getName() + "]. On hold due on " + next3Day + ".");
            this.setOnhold(m, next3Day);
            m.setRequestCount(m.getRequestCount() - 1);
        }else{
            this.setAvailable();
        }
    }

    public void setOnhold(Member nextM, Day next3Day) {
        status = new ItemStatusOnhold(nextM, next3Day);
    }

    public void setRequestList(ArrayList<Member> requestList) {
        this.requestList = requestList;
    }
}
