package ru.grfc.edu.vgviewer.figures.support;

import java.util.logging.Level;
import java.util.logging.Logger;
import ru.grfc.edu.vgviewer.figures.*;

/**
 *
 * @author gsv
 */
public class NormalFigureWithLabelFactory {
    private NormalFigureWithLabelFactory(){};

    public static Figure getFigure(FigureEnum figureType, String parametrs) {
        if (figureType == null || parametrs == null || parametrs.isEmpty()) {
            return null;
        }
        FigureParams params = null;
        try {
            params = new FigureParams(figureType, parametrs);
        } catch (Exception ex) {
            Logger.getLogger(NormalFigureWithLabelFactory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        if (figureType == FigureEnum.ELLIPSE) {
            return new EllipseWithLabel(params);
        } else if (figureType == FigureEnum.LINE) {
            return new LineWithLabel(params);
        } else if (figureType == FigureEnum.PARALLELOGRAM) {
            return new ParallelogramWithLabel(params);
        } else if (figureType == FigureEnum.RECTANGLE) {
            return new RectangleFigWithLabel(params);
        } else if (figureType == FigureEnum.RHOMBUS) {
            return new RhombusWithLabel(params);
        } else if (figureType == FigureEnum.ROUND_RECTAGLE) {
            return new RoundRectangleWithLabel(params);
        }
        return null;
    }
}
