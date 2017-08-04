package javadebugger;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ClassTableModel implements TableModel{
    private ArrayList<String> columnNames;
    private ArrayList<ArrayList> rowData;
    private HashMap<Long, ClassData> classMap;
    private SocketHandler socketHandler;
    private int columnCount = 10;
    
    public ClassTableModel() {
        classMap = new HashMap<>();
        socketHandler = SocketHandler.getInstance();
        columnNames = new ArrayList<>();
        rowData = new ArrayList<>();
        
        socketHandler.sendCommand(Command.getLoadedClasses, 
            new Callback(this){
                @Override
                public void execute(byte[] resultsBuf, int bytesRead){
                    ClassTableModel c = (ClassTableModel)owner;
                    c.buildClassList(resultsBuf, bytesRead);
                    c.buildColumnData();
                    c.buildRowData();
                    System.out.println("Loaded classes information built");
                }
            }
        );
    }
    
    public void buildClassList(byte[] resultsBuf, int bytesRead){
        long id = 0;

        for(int i = 0; i < resultsBuf.length; i += 8){
            for(int j = 0; j < 8; j++){
                id |= resultsBuf[i + j] << (j * 8);
            }
            
            classMap.put(id, new ClassData(id)); 
        }
    }
    
    public void buildColumnData(){
        columnNames.add("JNI Signature");
        columnNames.add("Generic Signature");
        columnNames.add("Source File Name");
        columnNames.add("Status");
        columnNames.add("Modifiers");
        columnNames.add("Minor Version");
        columnNames.add("Major Version");
        columnNames.add("Is Interface");
        columnNames.add("Is Array");
        columnNames.add("Is Modifiable");
    }
    
    public void buildRowData(){
        ArrayList<String> jniSignatures = new ArrayList<>();
        ArrayList<String> genericSignatures = new ArrayList<>();
        ArrayList<String> files = new ArrayList<>();
        ArrayList<Integer> status = new ArrayList<>();
        ArrayList<Integer> modifiers = new ArrayList<>();
        ArrayList<Integer> miVersions = new ArrayList<>();
        ArrayList<Integer> maVersions = new ArrayList<>();
        ArrayList<Boolean> interfaces = new ArrayList<>();
        ArrayList<Boolean> arrays = new ArrayList<>();
        ArrayList<Boolean> modifiables = new ArrayList<>();
        
        for(long l : classMap.keySet()){
            jniSignatures.add(classMap.get(l).getJniSignature());
            genericSignatures.add(classMap.get(l).getGenerictSignature());
            files.add(classMap.get(l).getSourceFileName());
            status.add(classMap.get(l).getClassStatus());
            modifiers.add(classMap.get(l).getModifiers());
            miVersions.add(classMap.get(l).getMinorVersion());
            maVersions.add(classMap.get(l).getMajorVersion());
            interfaces.add(classMap.get(l).isInterface());
            arrays.add(classMap.get(l).isArray());
            modifiables.add(classMap.get(l).isModifiable());
        }
        
        rowData.add(jniSignatures);
        rowData.add(genericSignatures);
        rowData.add(files);
        rowData.add(status);
        rowData.add(modifiers);
        rowData.add(miVersions);
        rowData.add(maVersions);
        rowData.add(interfaces);
        rowData.add(arrays);
        rowData.add(modifiables);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public String getColumnName(int columnIndex) {
        System.out.println("getting column name");
        return columnNames.get(columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rowData.get(columnIndex).get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return classMap.size();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
    }
    
    public void setColumnCount(int count){
        columnCount = count;
    }
}