public class ItemStatusOnhold implements ItemStatus{
    private String id;
    private String name;
    private Day onholdDay;


    public ItemStatusOnhold(Member nextM, Day next3Day) {
        this.id = nextM.getID();
        this.name = nextM.getName();
        this.onholdDay = next3Day;
    }

    @Override
    public String getStatusDescription() {
        return "On holdshelf for " + id + " " + name + " until " + onholdDay;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public Day getDay() {
        return onholdDay;
    }
    
}
