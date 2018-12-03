/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.grfc.edu.vgviewer.figures.support;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import ru.grfc.edu.vgviewer.figures.Coordinate;

/**
 *
 * @author win
 */
public class FigureParams {

    private int w;
    private int h;
    private int fx;
    private int fy;
    private int lx;
    private int ly;

    public int getLx() {
        return lx;
    }

    public int getLy() {
        return ly;
    }
    private int aw;
    private int ah;
    private boolean fill;
    private boolean blunt;
    private Color color;
    private Coordinate firstPoint;

    public Coordinate getLastPoint() {
        return lastPoint;
    }
    private Coordinate lastPoint;

    public Coordinate getFirstPoint() {
        return firstPoint;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int getFx() {
        return fx;
    }

    public int getFy() {
        return fy;
    }

    public int getAw() {
        return aw;
    }

    public int getAh() {
        return ah;
    }

    public boolean isFill() {
        return fill;
    }

    public boolean isBlunt() {
        return blunt;
    }

    public Color getColor() {
        return color;
    }

    public FigureParams(String parametrs) {
        String[] keyValuePairs = parametrs.split(" ");
        Map<String, String> mapParams = new HashMap<>();
        for (String pair : keyValuePairs) {
            String[] entry = pair.split("=");
            mapParams.put(entry[0].trim(), entry[1].trim());
        }
        try {
            w = Integer.valueOf(mapParams.get("w"));
        } catch (Exception e) {
        }
        try {
            h = Integer.valueOf(mapParams.get("h"));
        } catch (Exception e) {
        }
        try {
            fx = Integer.valueOf(mapParams.get("fx"));
        } catch (Exception e) {
        }
        try {
            fy = Integer.valueOf(mapParams.get("fy"));
        } catch (Exception e) {
        }
        try {
            lx = Integer.valueOf(mapParams.get("lx"));
        } catch (Exception e) {
        }
        try {
            ly = Integer.valueOf(mapParams.get("ly"));
        } catch (Exception e) {
        }
        try {
            aw = Integer.valueOf(mapParams.get("aw"));
        } catch (Exception e) {
        }
        try {
            ah = Integer.valueOf(mapParams.get("ah"));
        } catch (Exception e) {
        }
        try {
            fill = Boolean.valueOf(mapParams.get("fill"));
        } catch (Exception e) {
        }
        try {
            blunt = Boolean.valueOf(mapParams.get("blunt"));
        } catch (Exception e) {
        }
        try {
            String colorStr = String.valueOf(mapParams.get("color"));
            color = stringToColor(colorStr);
        } catch (Exception e) {
        }
        firstPoint = new Coordinate(fx, fy);
        lastPoint = new Coordinate(lx, ly);
    }

    private static Color stringToColor(final String value) {
        if (value == null) {
            return Color.black;
        }
        try {
            return Color.decode(value);
        } catch (NumberFormatException nfe) {
            try {
                final Field f = Color.class.getField(value);
                return (Color) f.get(null);
            } catch (Exception ce) {
                return Color.black;
            }
        }
    }

}
