/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.grfc.edu.vgviewer.figures.support;

import java.io.File;
import java.util.List;
import ru.grfc.edu.vgviewer.figures.Figure;

/**
 *
 * @author gsv
 */
interface FigureFileReaderWriter { 
        
    boolean saveFiguresToFile(String fileName, List<Figure> figures);  
    
    List<Figure> loadFiguresFromFile(String fileName);   
}
