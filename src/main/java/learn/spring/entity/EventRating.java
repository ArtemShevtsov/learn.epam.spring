package learn.spring.entity;

public enum EventRating {
    HIGHT(3), MIDDLE(2), LOW(1);

    private int val;

    EventRating(int val){
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static EventRating valueOf(int val){
        for(EventRating v: EventRating.values()){
            if(v.val == val){
                return v;
            }
        }
        throw new IllegalArgumentException("EventRaiting not found!");
    }
}
