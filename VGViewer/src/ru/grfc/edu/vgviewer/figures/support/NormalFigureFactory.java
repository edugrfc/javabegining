/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.grfc.edu.vgviewer.figures.support;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import ru.grfc.edu.vgviewer.figures.Coordinate;
import ru.grfc.edu.vgviewer.figures.Ellipse;
import ru.grfc.edu.vgviewer.figures.Figure;
import ru.grfc.edu.vgviewer.figures.Line;
import ru.grfc.edu.vgviewer.figures.Parallelogram;
import ru.grfc.edu.vgviewer.figures.Rectangle;
import ru.grfc.edu.vgviewer.figures.Rhombus;
import ru.grfc.edu.vgviewer.figures.RoundRectangle;


/**
 *
 * @author win
 */
public class NormalFigureFactory {
    public static Figure getFigure(FigureEnum figureType, String parametrs){
        if(figureType == null || parametrs == null || parametrs.isEmpty()){            
            return null;
        }
        FigureParams params = new FigureParams(parametrs);
                
        if(figureType == FigureEnum.ELLIPSE){
            return new Ellipse(params.getW(), params.getH(), params.getFirstPoint(), params.getColor(), params.isFill());
        }
        else if(figureType == FigureEnum.LINE){
            return new Line(params.getFirstPoint(), params.getLastPoint(), params.getColor());
        }
        else if(figureType == FigureEnum.PARALLELOGRAM){
            return new Parallelogram(params.isBlunt(), params.getW(), params.getH(), params.getFirstPoint(), params.getColor(), params.isFill());
        }
        else if(figureType == FigureEnum.RECTANGLE){
            return new Rectangle(params.getW(), params.getH(), params.getFirstPoint(), params.getColor(), params.isFill());
        }
        else if(figureType == FigureEnum.RHOMBUS){
            return new Rhombus(params.getW(), params.getFirstPoint(), params.getColor(), params.isFill());
        }
        else if(figureType == FigureEnum.ROUND_RECTAGLE){
            return new RoundRectangle(params.getW(), params.getH(), params.getAw(), params.getAh(), params.getFirstPoint(), params.getColor(), params.isFill());
        }                                       
        return null;
    }
}
