package ru.grfc.edu.vgviewer.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import ru.grfc.edu.vgviewer.figures.support.FigureParams;

/**
 * Прямоугольник
 *
 * Блок операций для блок-схем
 *
 * @author gsv
 */
public class RectangleFigWithLabel extends RectangleFig {
    private String labelText;

    public RectangleFigWithLabel(FigureParams params) {              
        super(params.getW(), params.getH(), params.getFirstPoint(), params.getColor(), params.isFill());
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
