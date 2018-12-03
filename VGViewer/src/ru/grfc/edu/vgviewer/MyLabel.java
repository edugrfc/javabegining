/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.grfc.edu.vgviewer;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;

/**
 *
 * @author mada
 */
//класс для создания пустого лейбла, отделяющего кнопки от компонентов ввода
public class MyLabel extends Label {

    Choice choice;
    TextField text;
    Button button;
    Container cont;

    @Override
    public Dimension getPreferredSize() {
        Insets inset = cont.getInsets();
        //честным путем не получилось, подобрал 81% вручную
        int width = (int)(cont.getWidth()* 0.81) - inset.left - inset.right;
        return new Dimension(width - choice.getWidth() - text.getWidth() - button.getWidth(), 5); //To change body of generated methods, choose Tools | Templates.
    }

    
    

    MyLabel(String s, Choice choice, TextField text, Button button, Container cont) {

        super(s);
        this.button = button;
        this.choice = choice;
        this.text = text;
        this.cont = cont;

    }

}
