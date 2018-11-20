import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Lesson1 {
    public static void main(String[] args) {
        Frame f = new Frame();
        f.setSize(600, 300);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                System.exit(0);
            }
        });
        f.add(new MyCanvas());
        f.setVisible(true);
    }
}

class MyCanvas extends Canvas {
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        // размеры окна
        Rectangle bounds = graphics.getClipBounds();
        System.out.println(bounds);
        // находим такой размер шрифта, чтобы надпись s была по ширине в 80% окна
        String s = new String("Hello, GRFC!");
        graphics.setFont(graphics.getFont().deriveFont((float) (0.8 * graphics.getFont().getSize() / graphics.getFontMetrics().stringWidth(s) * bounds.width)));
        // находим ширину и высоту текста, набранного новым шрифтом
        int sWidth = graphics.getFontMetrics().stringWidth(s);
        int sHeight = graphics.getFontMetrics().getAscent();
        // выводим текст
        graphics.drawString(s, (bounds.width / 2 - sWidth / 2), (bounds.height / 2 + sHeight / 2));
        // выводим красную рамку
        graphics.setColor(Color.RED);
        graphics.drawRect(0, 0, bounds.width - 1, bounds.height - 1);
        // выводим зеленый крест
        graphics.setColor(Color.GREEN);
        graphics.drawLine(0, 0, bounds.width - 1, bounds.height - 1);
        graphics.drawLine(0, bounds.height - 1, bounds.width - 1, 0);
        // выводим черный крест
        int diameter = Math.min(bounds.width, bounds.height) / 2;
        graphics.setColor(Color.BLUE);
        graphics.drawOval((bounds.width / 2 - diameter / 2), (bounds.height / 2 - diameter / 2), diameter, diameter);
    }

}
