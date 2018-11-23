package ru.grfc.edu.awthello;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Dralina D.
 */
public class EduAwtHello {

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setSize(300, 300);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        Canvas canvas = new MyCanvas();
        frame.add(canvas);
        frame.setVisible(true);
    }

    private static class MyCanvas extends Canvas {

        @Override
        public void paint(Graphics g) {
            int width = getWidth();
            int height = getHeight();

            g.setColor(Color.red);
            g.drawRect(0, 0, width - 1, height - 1);

            g.setColor(Color.green);
            g.drawLine(0, 0, width, height);
            g.drawLine(0, height, width, 0);

            int d = width > height ? (height / 2) : (width / 2);
            g.setColor(Color.black);
            g.drawOval((width - d) / 2, (height - d) / 2, d, d);

            String text = "Hello, GRFC!";
            int stringWidth = g.getFontMetrics().stringWidth(text);
            Font font = new Font(Font.MONOSPACED, Font.BOLD, (int) (width * 0.8 / (stringWidth / g.getFontMetrics().charWidth('H'))));
            g.setFont(font);
            g.setColor(Color.magenta);
            stringWidth = g.getFontMetrics().stringWidth(text);
            int stringHeight = g.getFontMetrics().getHeight();
            g.drawChars(text.toCharArray(), 0, text.length(), width / 2 - stringWidth / 2, height / 2 + stringHeight / 4);

        }
    }
}

