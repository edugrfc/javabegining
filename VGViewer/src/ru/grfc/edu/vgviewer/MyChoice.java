/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.grfc.edu.vgviewer;

import java.awt.Choice;

/**
 *
 * @author mada
 */
//селектор с выбором фигур
public class MyChoice extends Choice {

    MyChoice() {

        super();
        super.add("Rectangle");
        super.add("Square");
        super.add("Line");
        super.add("Ellipse");
        super.add("Circle");

    }

}
