package ru.grfc.edu.vgviewer.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import ru.grfc.edu.vgviewer.figures.support.FigureParams;

/**
 * Прямоугольник с закругленными краями
 *
 * Блок-терминатор
 *
 * @author gsv
 */
public class RoundRectangleWithLabel extends RoundRectangle {
    private String labelText;       
    
    public RoundRectangleWithLabel(FigureParams params) {              
        super(params.getW(), params.getH(), params.getAw(), params.getAh(), params.getFirstPoint(), params.getColor(), params.isFill());
        this.labelText = params.getLabelText();      
        this.figureType = params.getFigureType();
    }
           
    @Override
    public void draw(Graphics g) {
        super.draw(g);        
        drawLabel(g, labelText, this.getFirstPoint().getX() + this.getWidth() + 10, this.getFirstPoint().getY() + this.getHeight() + 10);
    }        
    
    private void drawLabel(Graphics g, String text, int x, int y) {
        if(text == null || text.isEmpty())
            return ;                   
        g.drawString(text, x, y);
    }     
    
    @Override
    public Map<String, Object> getFigureParameters(){
        Map<String, Object> mapParams = super.getFigureParameters();       
        mapParams.put("labelText", this.labelText);                
        return mapParams;
    }

}
