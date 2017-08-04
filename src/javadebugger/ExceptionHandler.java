package javadebugger;

import javax.swing.JOptionPane;

public class ExceptionHandler {
    public void showErrorDialog(String title, String message){
        JOptionPane.showMessageDialog(
            null,
            message,
            title,
        JOptionPane.ERROR_MESSAGE);
    }
}
