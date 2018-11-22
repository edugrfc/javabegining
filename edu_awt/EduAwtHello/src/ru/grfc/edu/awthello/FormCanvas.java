import java.awt.*;

class FormCanvas extends Canvas {
    public void paint(Graphics g) {
        int widthToPrint = this.size().width - 1;
        int heightToPrint = this.size().height - 1;
        // 1)	Обрамить окно тонкой линией
        g.setColor(Color.RED);
        g.drawRect(0, 0, widthToPrint, heightToPrint);

        // 2)	Перекрестие из угла в угол;
        g.setColor(Color.GREEN);
        g.drawLine(0, 0, widthToPrint, heightToPrint);
        g.drawLine(widthToPrint, 0, 0, heightToPrint);

        // 3)	Круг в центре с диаметром = половине минимума ширины или высоты окна;
        int xCenter = widthToPrint / 2;
        int yCenter = heightToPrint / 2;
        int minDiam = xCenter <= yCenter ? xCenter : yCenter;
        g.setColor(Color.BLUE);
        g.drawOval(xCenter - minDiam / 2, yCenter - minDiam / 2, minDiam, minDiam);

        //4)	Текст “Hello, ГРЧЦ” В центре. Шириной в ~80% от ширины окна
        String strToPaint = "Hello, ГРЧЦ";
        g.setColor(Color.ORANGE);
        drawCentered80PctString(g, strToPaint, new Rectangle(widthToPrint, heightToPrint));
    }

    private void drawCentered80PctString(Graphics g, String text, Rectangle rect) {
        String fontName = "Times New Roman";
        Font baseFont = new Font(fontName, 0, 10);
        int strWidth = g.getFontMetrics(baseFont).stringWidth(text);
        float mult = (float) (0.825 * rect.width / strWidth);
        int newFontSize = Math.round(baseFont.getSize() * mult);
        g.setFont(new Font(fontName, 0, (int) newFontSize));
        FontMetrics metrics = g.getFontMetrics();
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(text, x, y);
    }
}