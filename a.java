import java.awt.*;
import java.awt.event.*;

public class a {
	
    public static class MainWindow extends Frame implements WindowListener {
	
        @Override
        public void windowActivated(WindowEvent e) {
            System.out.println("windowActivated");
        }

        @Override
        public void windowClosed(WindowEvent e) {
            System.out.println("windowClosed");
        }

        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("windowClosing");
            //dispose();
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            System.out.println("windowDeactivated");
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            System.out.println("windowDeiconified");
        }

        @Override
        public void windowIconified(WindowEvent e) {
            System.out.println("windowIconified");
        }

        @Override
        public void windowOpened(WindowEvent e) {
            System.out.println("windowOpened");
        }
    }

    public static void main(String args[]) {
        System.out.println("Hello, GRFC!");
        Frame f = new MainWindow();
        f.addWindowListener(f);
        f.setSize(300, 300);
        f.setVisible(true);
    }

}