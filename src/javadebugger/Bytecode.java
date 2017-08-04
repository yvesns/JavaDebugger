package javadebugger;

public class Bytecode {
    private String mnemonic;
    private int bytecode;
    private int otherBytesCount;
    private String otherBytesDescription;
    private String stackDescription;
    private String description;
    private boolean hasOtherBytes;
    private boolean hasVariableOtherBytes = false;
    
    public Bytecode(String mnemonic, int bytecode, String otherBytesNum, String otherBytesDescription, String stackDescription, String description) {
        this.mnemonic = mnemonic;
        this.bytecode = bytecode;
        this.otherBytesDescription = otherBytesDescription;
        this.stackDescription = stackDescription;
        this.description = description;
        this.otherBytesCount = otherBytesNum.equals("") ? 0 : Integer.valueOf(otherBytesNum.substring(0,1));
        this.hasOtherBytes = !otherBytesNum.equals("");
        
        if(otherBytesNum.length() > 1){
            hasVariableOtherBytes = true;
        }
    }
    
    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public int getBytecode() {
        return bytecode;
    }

    public void setBytecode(int bytecode) {
        this.bytecode = bytecode;
    }

    public int getOtherBytesCount() {
        return otherBytesCount;
    }

    public void setOtherBytesNum(int otherBytesCount) {
        this.otherBytesCount = otherBytesCount;
    }

    public String getOtherBytesDescription() {
        return otherBytesDescription;
    }

    public void setOtherBytesDescription(String otherBytesDescription) {
        this.otherBytesDescription = otherBytesDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean hasOtherBytes(){
        return this.hasOtherBytes;
    }
}