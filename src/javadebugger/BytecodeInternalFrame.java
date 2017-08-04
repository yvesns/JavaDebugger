package javadebugger;

import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableColumn;

public class BytecodeInternalFrame extends JInternalFrame{
    private BytecodeHandler bytecodeHandler;
    private byte[] results;
    private int byteCount;
    
    private JTable bytecodeJTable;
    private TableColumn bytecodeColumn;
    private TableColumn mnemonicColumn;
    
    private JPanel bytecodeJPanel;
    private JLabel bytecodeJLabel;
    private JTextArea bytecodeJTextArea;
    
    private JPanel mnemonicJPanel;
    private JLabel mnemonicJLabel;
    private JTextArea mnemonicJTextArea;
    
    public BytecodeInternalFrame(BytecodeHandler bytecodeHandler, byte[] results, int byteCount){
        super("Bytecodes", true, true);
        this.bytecodeHandler = bytecodeHandler;
        this.results = results.clone();
        this.byteCount = byteCount;
        
        buildVisualComponents();
        showBytecodes();
    }
    
    private void buildVisualComponents(){
        setBounds(0, 0, 300, 300);
        setVisible(true);
        setLocation(300, 300);
        setLayout(new FlowLayout());
        
        /*bytecodeJPanel = new JPanel();
        bytecodeJTable = new JTable(new ClassTableModel());
        bytecodeColumn = new TableColumn();
        mnemonicColumn = new TableColumn();
        
        bytecodeColumn.setHeaderValue("Bytecode");
        mnemonicColumn.setHeaderValue("Mnemonic");
        
        bytecodeJTable.addColumn(bytecodeColumn);
        bytecodeJTable.addColumn(mnemonicColumn);
        
        bytecodeJPanel.add(bytecodeJTable);
        add(bytecodeJPanel);*/
        
        bytecodeJPanel = new JPanel();
        bytecodeJPanel.setLayout(new BoxLayout(bytecodeJPanel, BoxLayout.Y_AXIS));
        bytecodeJLabel = new JLabel("Bytecode");
        bytecodeJLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bytecodeJTextArea = new JTextArea();
        bytecodeJTextArea.setEditable(false);
        bytecodeJPanel.add(bytecodeJLabel);
        bytecodeJPanel.add(bytecodeJTextArea);
        
        mnemonicJPanel = new JPanel();
        mnemonicJPanel.setLayout(new BoxLayout(mnemonicJPanel, BoxLayout.Y_AXIS));
        mnemonicJLabel = new JLabel("Mnemonic");
        mnemonicJLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mnemonicJTextArea = new JTextArea();
        mnemonicJTextArea.setEditable(false);
        mnemonicJPanel.add(mnemonicJLabel);
        mnemonicJPanel.add(mnemonicJTextArea);
        
        add(bytecodeJPanel);
        add(mnemonicJPanel);
    }
    
    private void showBytecodes(){
        Bytecode bc;
        System.out.println("showMethodBytes");
        
        int i = 0;
        while(i < byteCount){
            bc = bytecodeHandler.getBytecode(results[i] & 0xFF);
            
            bytecodeJTextArea.setText(bytecodeJTextArea.getText() + results[i]);
            
            String s = mnemonicJTextArea.getText();
            s += bc != null ? " " + bc.getMnemonic() : "";
            mnemonicJTextArea.setText(s);
            
            if(bc != null && bc.hasOtherBytes()){
                for(int j = 0; j < bc.getOtherBytesCount(); j++){
                    i++;
                    bytecodeJTextArea.setText(bytecodeJTextArea.getText() + " " + results[i]);
                }
            }
            
            bytecodeJTextArea.setText(bytecodeJTextArea.getText() + "\n");
            mnemonicJTextArea.setText(mnemonicJTextArea.getText() + "\n");
            
            i++;
        }
    }
}