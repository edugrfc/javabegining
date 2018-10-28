import java.awt.*;
import java.awt.event.*;

public class a {
	
    public static class MainWindow extends Frame {
            
        public MainWindow() {
            WindowListener listener = new MainWindowListener();
            addWindowListener(listener);
        }
            
        private class MainWindowListener extends WindowAdapter {
	
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
            
        }

    }

    public static void main(String args[]) {
        System.out.println("Hello, GRFC!");
        Frame f = new MainWindow();
        f.setSize(300, 300);
        f.setVisible(true);
    }

}