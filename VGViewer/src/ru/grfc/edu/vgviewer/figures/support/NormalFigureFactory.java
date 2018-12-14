package ru.grfc.edu.vgviewer.figures.support;

import java.util.logging.Level;
import java.util.logging.Logger;
import ru.grfc.edu.vgviewer.figures.Ellipse;
import ru.grfc.edu.vgviewer.figures.Figure;
import ru.grfc.edu.vgviewer.figures.Line;
import ru.grfc.edu.vgviewer.figures.Parallelogram;
import ru.grfc.edu.vgviewer.figures.RectangleFig;
import ru.grfc.edu.vgviewer.figures.Rhombus;
import ru.grfc.edu.vgviewer.figures.RoundRectangle;

/**
 *
 * @author gsv
 */
public class NormalFigureFactory implements FigureFactory {    
    
    public Figure getFigure(FigureEnum figureType, String parametrs) {
        if (figureType == null || parametrs == null || parametrs.isEmpty()) {
            return null;
        }
        FigureParams figureParams = null;
        try {
            figureParams = new FigureParams(figureType, parametrs);
        } catch (Exception ex) {
            Logger.getLogger(NormalFigureFactory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        if (figureType == FigureEnum.ELLIPSE) {
            return new Ellipse(figureParams.getW(), figureParams.getH(), figureParams.getFirstPoint(), figureParams.getColor(), figureParams.isFill());
        } else if (figureType == FigureEnum.LINE) {
            return new Line(figureParams.getFirstPoint(), figureParams.getLastPoint(), figureParams.getColor());
        } else if (figureType == FigureEnum.PARALLELOGRAM) {
            return new Parallelogram(figureParams.isBlunt(), figureParams.getW(), figureParams.getH(), figureParams.getFirstPoint(), figureParams.getColor(), figureParams.isFill());
        } else if (figureType == FigureEnum.RECTANGLE) {
            return new RectangleFig(figureParams.getW(), figureParams.getH(), figureParams.getFirstPoint(), figureParams.getColor(), figureParams.isFill());
        } else if (figureType == FigureEnum.RHOMBUS) {
            return new Rhombus(figureParams.getW(), figureParams.getFirstPoint(), figureParams.getColor(), figureParams.isFill());
        } else if (figureType == FigureEnum.ROUND_RECTAGLE) {
            return new RoundRectangle(figureParams.getW(), figureParams.getH(), figureParams.getAw(), figureParams.getAh(), figureParams.getFirstPoint(), figureParams.getColor(), figureParams.isFill());
        }
        return null;
    }
}
