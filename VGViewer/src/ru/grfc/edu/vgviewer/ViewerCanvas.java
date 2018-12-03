/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.grfc.edu.vgviewer;

import java.awt.*;
import java.util.List;
import ru.grfc.edu.vgviewer.figures.Figure;

/**
 *
 * @author win
 */
public class ViewerCanvas extends Canvas {
        private  List<Figure> figures;
              
        public void setFigures(List<Figure> figures) {
            this.figures = figures;            
        }

        @Override
        public void paint(Graphics g) {
            int widthToPrint = this.size().width - 1;
            int heightToPrint = this.size().height - 1;            
            g.setColor(Color.RED);
            g.drawRect(0, 0, widthToPrint, heightToPrint);
                        
            if(figures != null){
                figures.forEach((figure) -> {
                    figure.draw(g);
                });
            }
        }
    }
