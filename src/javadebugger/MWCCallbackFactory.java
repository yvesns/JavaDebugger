package javadebugger;

import java.util.Arrays;

public class MWCCallbackFactory {
    MainWindowController mwc;
    
    public MWCCallbackFactory(MainWindowController mwc){
        this.mwc = mwc;
    }
    
    public Callback createCallback(Command cmd){
        Callback c = null;
        
        if(cmd == Command.resume){
            c = createResumeCallback();
        } else if(cmd == Command.getCurrentMethodBytes){
            c = createGetCurrentMethodBytesCallback();
        } else if(cmd == Command.getPhase){
            c = createGetPhaseCallback();
        }
        
        return c;
    }
    
    private Callback createResumeCallback(){
        return new Callback(){
            @Override
            public void execute(byte[] resultsBuf, int byteCount){}
        };
    }
    
    private Callback createGetCurrentMethodBytesCallback(){
        return new Callback(mwc){
            @Override
            public void execute(byte[] resultsBuf, int byteCount){
                MainWindowController mwc = (MainWindowController)owner;
                mwc.showMethodBytes(resultsBuf, byteCount);
            }
        };
    }
    
    private Callback createGetPhaseCallback(){
        return new Callback(mwc){
            @Override
            public void execute(byte[] resultsBuf, int byteCount){
                MainWindowController mwc = (MainWindowController)owner;
                int phase = 0;
                
                for(int i = 0; i < byteCount; i++){
                    phase |= resultsBuf[i] << (i * 8);
                }
                
                mwc.processHandler.setPhase(phase);
            }
        };
    }
}