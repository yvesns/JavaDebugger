package javadebugger;

public class ProcessHandler {
    private Process process;
    private String agentPath = "C:\\Users\\03711082335\\U++\\upp\\out\\MyApps\\MINGWx64.Dll\\JVMTIAgent.dll";
    private String jarPath;
    private int phase;
    
    public ProcessHandler(String jarPath) throws Exception{
        this.jarPath = jarPath;
    }
    
    public void startProcess() throws Exception{
        String[] cmdArray = {"java", "-agentpath:" + agentPath, "-jar", jarPath};
        ProcessBuilder pb = new ProcessBuilder(cmdArray);
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
        pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
        process = pb.start();
    }
    
    public synchronized int getPhase() {
        return phase;
    }

    public synchronized void setPhase(int phase) {
        this.phase = phase;
    }
}
