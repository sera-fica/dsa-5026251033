package lw01.unguided;

public abstract class WashService implements Biliable {
    private String id;
    private int days;
    
    public WashService(String id, int days) {
        this.id = id;
        this.days = days;
    }

    public String getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

   public abstract int calculateCharge();

   public String label() {
      return "Service";
   }

   public String summary() {
      String var10000 = this.id;
      return var10000 + " | " + this.label() + " | " + this.calculateCharge();
   }
}


