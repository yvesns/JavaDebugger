package javadebugger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

public class BytecodeHandler {
    private Map<Integer, Bytecode> bytecodes;
    
    public BytecodeHandler() throws Exception{
        bytecodes = new HashMap<Integer, Bytecode>();
        File f = new File("Resources/opcodes.ods");
        Sheet s = SpreadSheet.createFromFile(f).getSheet(0);
        Bytecode b;
        String obs, obDescription;
        
        for(int i = 2; i < s.getRowCount(); i++){
            obs = s.getCellAt("D" + i).getTextValue();
            obs = obs.equals("") ? "" : obs.split(":")[0];
            obDescription = obs.equals("") ? "" : s.getCellAt("D" + i).getTextValue().split(":")[1];
            
            b = new Bytecode(
                    s.getCellAt("A" + i).getTextValue(),
                    Integer.valueOf(s.getCellAt("B" + i).getTextValue(), 16),
                    obs,
                    obDescription,
                    s.getCellAt("E" + i).getTextValue(),
                    s.getCellAt("F" + i).getTextValue()
            );
            
            bytecodes.put(b.getBytecode(), b);
        }
    }
    
    public Bytecode getBytecode(int b){
        return bytecodes.get(b);
    }
}
