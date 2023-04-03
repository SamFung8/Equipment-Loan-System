import java.util.ArrayList;
import java.util.Collections; //Provides sorting

public class Club {
    private ArrayList<Member> allMembers;
    private static Club instance = new Club();
    private ArrayList<Item> allItems;

    private Club() {
        allMembers = new ArrayList<>();
        allItems = new ArrayList<>();
    }

    public static Club getInstance() {
        return instance;
    }

    public void addMember(Member m) {// See how it is called in Member constructor (Step 3)
        allMembers.add(m);
        Collections.sort(allMembers); // allMembers
    }

    public void listClubMembers() {
        System.out.println(Member.getListingHeader()); // Member
        for (Member m : allMembers)
            System.out.println(m); // m or m.toString()
    }

    public void removeMember(Member m) {
        allMembers.remove(m);
    }

    public boolean findMemberID(String id) {
        for (Member m : allMembers) {
            if (m.getID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public String findMemberNameByID(String id){
        for (Member m : allMembers) {
            if (m.getID().equals(id)) {
                return m.getName();
            }
        }
        return "";
    }

    public boolean findItemID(String id) {
        for (Item i : allItems) {
            if (i.getID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public String findItemNameByID(String id) {
        for (Item i : allItems) {
            if (i.getID().equals(id)) {
                return i.getName();
            }
        }
        return "";
    }

    public void listClubItems() {
        System.out.println(Item.getListingHeader()); // Member
        for (Item i : allItems)
            System.out.println(i); // m or m.toString()
    }

    public void addItem(Item i) {// See how it is called in Member constructor (Step 3)
        allItems.add(i);
        Collections.sort(allItems); // allMembers
    }

    public void removeItem(Item i) {
        allItems.remove(i);
    }

    public Member findMemberByID(String id) {
        for (Member m : allMembers) {
            if (m.getID().equals(id)) {
                return m;
            }
        }
        return null;
    }

    public Item findItemByID(String id) {
        for (Item i : allItems) {
            if (i.getID().equals(id)) {
                return i;
            }
        }
        return null;
    }

    public ArrayList<Item> getAllItems(){
        return allItems;
    }

}
