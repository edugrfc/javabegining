/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.grfc.edu.vgviewer.figures.support;

import ru.grfc.edu.vgviewer.figures.Figure;

/**
 *
 * @author win
 */
public class FigureTextFormatConverter {
    private final FigureFactory figureFactory;
    
    FigureTextFormatConverter(FigureFactory figureFactory) throws Exception{
        if(figureFactory == null)
            throw new Exception("Figure factory is null"); 
        this.figureFactory = figureFactory;
    }        
            
    public Figure figureFromTextLine(String strLine){
        if(strLine == null || strLine.isEmpty())
            return null;        
        FigureEnum figureType = findFigureTypeByShorName(strLine.substring(0, 2));      
        return figureFactory.getFigure(figureType, strLine);
    }
    
    public String figureToTextLine(Figure figure){
        if(figure == null)
            return null;        
        
        
        return "";
    }
    
    
    private FigureEnum findFigureTypeByShorName(String shortName){
        if(shortName == null || shortName.isEmpty())
            return null;
        FigureEnum figureType = null;
        for (FigureEnum value : FigureEnum.values()) {
            if (value.equalsShortName(shortName)) {
                figureType = value;
            }
        }        
        return figureType;
    }      
    
}
