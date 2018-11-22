package ru.grfc.edu.awthello;

import java.awt.*;

public class MainFunction {

    public static void main(String[] Args) {

        Frame frame = new Frame("Random Frame");
        frame.addWindowListener(new MyController(frame));
        frame.setSize(500, 500);

        frame.add(new ExibitionCanvas(frame));

        frame.setVisible(true);

    }

}
