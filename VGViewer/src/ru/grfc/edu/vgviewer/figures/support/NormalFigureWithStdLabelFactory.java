package ru.grfc.edu.vgviewer.figures.support;

import java.util.logging.Level;
import java.util.logging.Logger;
import ru.grfc.edu.vgviewer.figures.*;

/**
 *
 * @author gsv
 */
public class NormalFigureWithStdLabelFactory implements FigureFactory {   

    public Figure getFigure(FigureEnum figureType, String parametrs) {
        if (figureType == null || parametrs == null || parametrs.isEmpty()) {
            return null;
        }
        FigureParams figureParams = null;
        try {
            figureParams = new FigureParams(figureType, parametrs);
        } catch (Exception ex) {
            Logger.getLogger(NormalFigureWithStdLabelFactory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        
        
        if (figureType == FigureEnum.ELLIPSE) {
            return new EllipseWithLabel(figureParams);
        } else if (figureType == FigureEnum.LINE) {
            return new LineWithLabel(figureParams);
        } else if (figureType == FigureEnum.PARALLELOGRAM) {
            return new ParallelogramWithLabel(figureParams);
        } else if (figureType == FigureEnum.RECTANGLE) {
            return new RectangleFigWithLabel(figureParams);
        } else if (figureType == FigureEnum.RHOMBUS) {
            return new RhombusWithLabel(figureParams);
        } else if (figureType == FigureEnum.ROUND_RECTAGLE) {
            return new RoundRectangleWithLabel(figureParams);
        }
        return null;
    }    
}
