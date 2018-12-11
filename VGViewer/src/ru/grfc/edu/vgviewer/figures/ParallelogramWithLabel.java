package ru.grfc.edu.vgviewer.figures;

import java.awt.Color;
import java.awt.Graphics;
import ru.grfc.edu.vgviewer.figures.support.FigureParams;

/**
 * Параллелограм
 *
 * Блок для ввода-вывода данных
 *
 * @author gsv
 */
public class ParallelogramWithLabel extends Parallelogram {
    private String labelText;

    public ParallelogramWithLabel(FigureParams params) {       
        super(params.isBlunt(), params.getW(), params.getH(), params.getFirstPoint(), params.getColor(), params.isFill());
        this.labelText = params.getLabelText();
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
}
