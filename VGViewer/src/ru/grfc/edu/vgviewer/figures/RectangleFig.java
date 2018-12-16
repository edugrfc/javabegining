package ru.grfc.edu.vgviewer.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

/**
 * Прямоугольник
 *
 * Блок операций для блок-схем
 *
 * @author dds
 */
public class RectangleFig extends Figure {

    //ширина
    int width;

    //высота
    int height;

    public RectangleFig(int width, int height, Coordinate firstPoint, Color color, boolean fill) {
        super(firstPoint, color, fill);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (fill) {
            g.fillRect(firstPoint.getX(), firstPoint.getY(), width, height);
        } else {
            g.drawRect(firstPoint.getX(), firstPoint.getY(), width, height);
        }
    }

    @Override
    public String getName() {
        return width == height ? "Квадрат" : "Прямоугольник";
    }
    
    @Override
    public Map<String, Object> getFigureParameters(){
        Map<String, Object> mapParams = super.getFigureParameters();                
        mapParams.put("w", this.width);      
        mapParams.put("h", this.height);  
        mapParams.put("name", getName());                
        return mapParams;
    }

}
