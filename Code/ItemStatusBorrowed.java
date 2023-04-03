public class ItemStatusBorrowed implements ItemStatus{
    private String id;
    private String name;
    private Day borrowDate;

    public ItemStatusBorrowed (String id, String name){
        this.id = id;
        this.name = name;
        this.borrowDate = SystemDate.getInstance().clone();
    }

    @Override
    public String getStatusDescription() {
        return "Borrowed by " + id + " " + name + " on " + borrowDate;//id, name, borrow date 
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public Day getDay(){
        return borrowDate;
    }
    
}
