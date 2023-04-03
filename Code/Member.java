import java.util.ArrayList;

public class Member implements Comparable<Member> {
    private String id;
    private String name;
    private Day joinDate;
    private int checkoutCount;
    private int requestCount;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.joinDate = SystemDate.getInstance().clone();
        this.checkoutCount = 0;
        this.requestCount = 0;
        Club.getInstance().addMember(this);
    }

    @Override
    public String toString() {
        // Learn: "-" means left-aligned
        return String.format("%-5s%-9s%11s%7d%13d", id, name, joinDate, checkoutCount, requestCount);
    }

    public static String getListingHeader() {
        return String.format("%-5s%-9s%11s%11s%13s", "ID", "Name", "Join Date ", "#Borrowed", "#Requested");
    }

    public int compareTo(Member another) {
        return this.id.compareTo(another.id);
    }

    public String getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getCheckoutCount(){
        return this.checkoutCount;
    }

    public void setCheckoutCount(int checkoutCount){
        this.checkoutCount = checkoutCount;
    }

    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }

    public int getRequestCount(){
        return this.requestCount;
    }

}
