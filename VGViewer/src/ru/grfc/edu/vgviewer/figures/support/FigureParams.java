package ru.grfc.edu.vgviewer.figures.support;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.grfc.edu.vgviewer.figures.Coordinate;

/**
 *
 * @author gsv
 */
public class FigureParams {

    private final static String PRIMITIVE_RGB_REGEXP = "rgb\\((\\d{1,3}),\\s*(\\d{1,3}),\\s*(\\d{1,3})\\)";

    private int w;
    private int h;
    private int fx;
    private int fy;
    private int lx;
    private int ly;
    private int aw;
    private int ah;
    private boolean fill;
    private boolean blunt;
    private Color color;
    private Coordinate firstPoint;
    private Coordinate lastPoint;
    private Coordinate textPoint;
    private String text;
    private int tx;
    private int ty;

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

    public int getLx() {
        return lx;
    }

    public int getLy() {
        return ly;
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

    public Coordinate getLastPoint() {
        return lastPoint;
    }

    public Coordinate getFirstPoint() {
        return firstPoint;
    }

    public Coordinate getTextPoint() {
        return textPoint;
    }

    public String getText() {
        return text;
    }

    public int getTx() {
        return tx;
    }

    public int getTy() {
        return ty;
    }

    public FigureParams(FigureEnum figureType, String parametrs) throws Exception {
        Pattern pattern = Pattern.compile(figureType.getRegExp(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(parametrs);
        if (!matcher.matches()) {
            throw new Exception("Can't parse parametrs string");
        }

        String[] keyValuePairs = parametrs.split(" ");
        Map<String, String> mapParams = new HashMap<>();
        for (String pair : keyValuePairs) {
            String[] entry = pair.split("=");
            mapParams.put(entry[0].trim(), entry[1].trim());
        }
        try {
            text = mapParams.get("text");
        } catch (Exception e) {
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
        try {
            text = String.valueOf(mapParams.get("text"));
        } catch (Exception e) {
        }
        try {
            tx = Integer.valueOf(mapParams.get("tx"));
        } catch (Exception e) {
        }
        try {
            ty = Integer.valueOf(mapParams.get("ty"));
        } catch (Exception e) {
        }

        firstPoint = new Coordinate(fx, fy);
        lastPoint = new Coordinate(lx, ly);
        textPoint = new Coordinate(tx, ty);
    }

    public static Color stringToColor(final String value) {
        if (value == null || value.isEmpty()) {
            return Color.black;
        }

        // Формат rgb(255,255,255)
        Pattern pattern = Pattern.compile(PRIMITIVE_RGB_REGEXP, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches()) {
            int r = Integer.valueOf(matcher.group(1));
            int g = Integer.valueOf(matcher.group(2));
            int b = Integer.valueOf(matcher.group(3));
            return new Color(r, g, b);
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
