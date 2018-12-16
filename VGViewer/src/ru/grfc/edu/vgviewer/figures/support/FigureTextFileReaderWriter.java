/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.grfc.edu.vgviewer.figures.support;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.grfc.edu.vgviewer.VGViewer;
import ru.grfc.edu.vgviewer.figures.Figure;

/**
 *
 * @author gsv
 */
public class FigureTextFileReaderWriter implements FigureFileReaderWriter {

    private final FigureTextFormatConverter figureTextFormatConverter;

    public FigureTextFileReaderWriter(FigureTextFormatConverter figureTextFormatConverter) {
        this.figureTextFormatConverter = figureTextFormatConverter;
    }

    @Override
    public boolean saveFiguresToFile(String fileName, List<Figure> figures) {
        try (FileWriter fileOutStream = new FileWriter(fileName);
                BufferedWriter bw = new BufferedWriter(fileOutStream);) {
            figures.stream().forEach(fig -> {
                try {                    
                    FigureParams figureParams = new FigureParams(fig.getFigureParameters());                                 
                    bw.write(figureParams.getPrintToFileStr() + "\n");
                } catch (Exception ex) {
                    Logger.getLogger(FigureTextFileReaderWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(VGViewer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public List<Figure> loadFiguresFromFile(String fileName) {
        List<Figure> figures = new ArrayList();
        try (FileInputStream fileInStream = new FileInputStream(fileName);
                BufferedReader br = new BufferedReader(new InputStreamReader(fileInStream));) {
            String strLine;
            while ((strLine = br.readLine()) != null)              
                figures.add(figureTextFormatConverter.figureFromTextLine(strLine));                                            
        } catch (IOException e) {
            Logger.getLogger(VGViewer.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }

        return figures;
    }

}
