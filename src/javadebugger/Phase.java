package javadebugger;

public enum Phase {
    ONLOAD(1),
    PRIMORDIAL(2),
    LIVE(4),
    START(6),
    DEAD(8);
    
    private int val;
    
    Phase(int val){
        this.val = val;
    }

    public int getVal(){
        return val;
    }
}
