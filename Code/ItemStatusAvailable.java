public class ItemStatusAvailable implements ItemStatus{
    private String id;
    private String name;
    private Day arriveDate;


    @Override
    public String getStatusDescription() {
        return "Available";
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public Day getDay() {
        return arriveDate;
    }
    
}
