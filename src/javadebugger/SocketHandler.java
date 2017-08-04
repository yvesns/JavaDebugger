package javadebugger;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketHandler {
    private static SocketHandler socketHandlerSingleton;
    private Socket socket;
    private Thread socketThread;
    private String ip = "127.0.0.1";
    private int port = 5554;
    private boolean responseReturned;
    OutputStream outputStream;
    InputStream inputStream;
    Callback nextCallback;
    
    private class SocketThread implements Runnable{
        SocketHandler sh;
        byte[] buffer = new byte[8192];
        int bytesRead;
        
        public SocketThread(SocketHandler sh){
            this.sh = sh;
        }
        
        public void run(){
            while(!sh.socket.isClosed()){
                System.out.println(sh.socket.isClosed());
                try{
                    bytesRead = sh.inputStream.read(buffer);
                } catch(Exception e){
                    System.out.println(e.getMessage());
                }
                
                System.out.println("Response received");
                sh.nextCallback.execute(buffer, bytesRead);
                sh.setResponseReturned(true);
            }
            
            System.out.println("Exiting java thread.");
        }
    }
    
    private SocketHandler(){}
    
    public static SocketHandler getInstance(){
        if(socketHandlerSingleton == null){
            socketHandlerSingleton = new SocketHandler();
        }
        
        return socketHandlerSingleton;
    }
    
    public void startSocketThread() throws Exception{
        socket = new Socket(ip, port);
        outputStream = socket.getOutputStream();
        inputStream = socket.getInputStream();
        socketThread = new Thread(new SocketThread(this));
        socketThread.start();
    }
    
    public void sendCommand(Command c, Callback callback){
        try{
            setResponseReturned(false);
            nextCallback = callback;
            outputStream.write(c.ordinal());
            outputStream.flush();
            while(!getResponseReturned()){}
        }catch(Exception e){
            new ExceptionHandler().showErrorDialog(
                "Socket Error", 
                "An error has ocurred while sending command to jvmti agent."
            );
        }
    }
    
    public synchronized void setResponseReturned(boolean responseReturned){
        this.responseReturned = responseReturned;
    }
    
    public synchronized boolean getResponseReturned(){
        return responseReturned;
    }
}
