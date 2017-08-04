package javadebugger;

public abstract class Callback {
    Object owner;
    
    public Callback(){}
    
    public Callback(Object owner){
        this.owner = owner;
    }
    
    public abstract void execute(byte[] resultsBuf, int bytesRead);
}
