package ru.grfc.edu.vgviewer.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import ru.grfc.edu.vgviewer.figures.support.FigureEnum;

/**
 * Абстрактый класс фигуры
 *
 * @author dds
 */
abstract public class Figure implements java.io.Serializable {        
    FigureEnum figureType;
    
    //начальная координата фигуры (левый верххний угол)
    Coordinate firstPoint;

    //цвет контура фигуры
    Color color;

    //наличие заливки
    boolean fill;

    public Figure(Coordinate firstPoint, Color color, boolean fill) {
        this.firstPoint = firstPoint;
        this.color = color;
        this.fill = fill;
    }    

    public Coordinate getFirstPoint() {
        return firstPoint;
    }

    public Color getColor() {
        return color;
    }

    public boolean isFill() {
        return fill;
    }

    //метод, который отрисовывает фигуру
    public abstract void draw(Graphics g);

    //имя фигуры
    public abstract String getName();
       
    public Map<String, Object> getFigureParameters(){
        Map<String, Object> mapParams = new HashMap();        
        mapParams.put("figureType", this.figureType);
        mapParams.put("firstPoint", this.firstPoint);
        mapParams.put("fx", this.firstPoint.getX());
        mapParams.put("fy", this.firstPoint.getY());
        mapParams.put("color", this.color);
        mapParams.put("fill", this.fill);                
        return mapParams;
    }
}
