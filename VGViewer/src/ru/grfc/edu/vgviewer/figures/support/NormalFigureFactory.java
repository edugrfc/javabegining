package ru.grfc.edu.vgviewer.figures.support;

import java.util.logging.Level;
import java.util.logging.Logger;
import ru.grfc.edu.vgviewer.figures.Ellipse;
import ru.grfc.edu.vgviewer.figures.Figure;
import ru.grfc.edu.vgviewer.figures.Line;
import ru.grfc.edu.vgviewer.figures.Parallelogram;
import ru.grfc.edu.vgviewer.figures.Rectangle;
import ru.grfc.edu.vgviewer.figures.Rhombus;
import ru.grfc.edu.vgviewer.figures.RoundRectangle;

/**
 *
 * @author gsv
 */
public class NormalFigureFactory {

    private NormalFigureFactory() {
    }

    public static Figure getFigure(FigureEnum figureType, String parametrs) {
        if (figureType == null || parametrs == null || parametrs.isEmpty()) {
            return null;
        }
        FigureParams params = null;
        try {
            params = new FigureParams(figureType, parametrs);
        } catch (Exception ex) {
            Logger.getLogger(NormalFigureFactory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        if (figureType == FigureEnum.ELLIPSE) {
            return new Ellipse(params.getW(), params.getH(), params.getFirstPoint(), params.getColor(), params.isFill(), params.getText(), params.getTextPoint());
        } else if (figureType == FigureEnum.LINE) {
            return new Line(params.getFirstPoint(), params.getLastPoint(), params.getColor(), params.getText(), params.getTextPoint());
        } else if (figureType == FigureEnum.PARALLELOGRAM) {
            return new Parallelogram(params.isBlunt(), params.getW(), params.getH(), params.getFirstPoint(), params.getColor(), params.isFill(), params.getText(), params.getTextPoint());
        } else if (figureType == FigureEnum.RECTANGLE) {
            return new Rectangle(params.getW(), params.getH(), params.getFirstPoint(), params.getColor(), params.isFill(), params.getText(), params.getTextPoint());
        } else if (figureType == FigureEnum.RHOMBUS) {
            return new Rhombus(params.getW(), params.getFirstPoint(), params.getColor(), params.isFill(), params.getText(), params.getTextPoint());
        } else if (figureType == FigureEnum.ROUND_RECTAGLE) {
            return new RoundRectangle(params.getW(), params.getH(), params.getAw(), params.getAh(), params.getFirstPoint(), params.getColor(), params.isFill(), params.getText(), params.getTextPoint());
        }
        return null;
    }
}
