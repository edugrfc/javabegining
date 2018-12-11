package ru.grfc.edu.vgviewer.figures;

import java.awt.Color;
import java.awt.Graphics;
import ru.grfc.edu.vgviewer.figures.support.FigureParams;

/**
 * Линия
 *
 * (пока вместо стрелки)
 *
 * @author dds
 */
public class LineWithLabel extends Line {
    private String labelText;

    public LineWithLabel(FigureParams params) {       
        super(params.getFirstPoint(), params.getLastPoint(), params.getColor());
        this.labelText = params.getLabelText();
    }
           
    @Override
    public void draw(Graphics g) {
        super.draw(g);        
        drawLabel(g, labelText, this.getLastPoint().getX() + 10, this.getLastPoint().getY() + 10);
    }        
    
    private void drawLabel(Graphics g, String text, int x, int y) {
        if(text == null || text.isEmpty())
            return ;                   
        g.drawString(text, x, y);
    }        
}
