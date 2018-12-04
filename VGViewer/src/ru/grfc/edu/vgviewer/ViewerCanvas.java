package ru.grfc.edu.vgviewer;

import java.awt.*;
import java.util.List;
import ru.grfc.edu.vgviewer.figures.Figure;

/**
 *
 * @author gsv
 */
public class ViewerCanvas extends Canvas {

    private List<Figure> figures;

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
    }

    @Override
    public void paint(Graphics g) {
        int widthToPrint = this.size().width - 1;
        int heightToPrint = this.size().height - 1;
        g.setColor(Color.RED);
        g.drawRect(0, 0, widthToPrint, heightToPrint);

        if (figures != null) {
            figures.forEach((figure) -> {
                figure.draw(g);
            });
        }
    }
}
