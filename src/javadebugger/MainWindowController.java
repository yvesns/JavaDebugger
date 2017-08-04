package javadebugger;

import java.io.File;
import javax.swing.JFileChooser;

public class MainWindowController{
    ProcessHandler processHandler;
    SocketHandler socketHandler;
    BytecodeHandler bytecodeHandler;
    MWCCallbackFactory factory;
    MainWindow mw;
    
    MainWindowController(MainWindow mw){
        this.mw = mw;
        factory = new MWCCallbackFactory(this);
    }
    
    public void openFileMenuItemActionPerformed(MainWindow mw, java.awt.event.ActionEvent evt) {                                                 
        JFileChooser fc = new JFileChooser();
        
        if(fc.showOpenDialog(mw) != JFileChooser.APPROVE_OPTION){
            return;
        }
        
        File file = fc.getSelectedFile();
        
        try{
            processHandler = new ProcessHandler(file.getAbsolutePath());
            socketHandler = SocketHandler.getInstance();
            bytecodeHandler = new BytecodeHandler();
            
            processHandler.startProcess();
            socketHandler.startSocketThread();
            
            do{
                socketHandler.sendCommand(Command.getPhase, factory.createCallback(Command.getPhase));
                System.out.println(processHandler.getPhase());
                Thread.sleep(1000);
            }while(processHandler.getPhase() != Phase.LIVE.getVal());
            
            socketHandler.sendCommand(Command.getCurrentMethodBytes, factory.createCallback(Command.getCurrentMethodBytes));
        } catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void resumeButtonActionPerformed(MainWindow mw, java.awt.event.ActionEvent evt){
        socketHandler.sendCommand(Command.resume, factory.createCallback(Command.resume));
    }
    
    public void showMethodBytes(byte[] results, int byteCount){
        BytecodeInternalFrame bif = new BytecodeInternalFrame(bytecodeHandler, results, byteCount);
        mw.add(bif);
    }
}