package ru.grfc.edu.vgviewer.figures.support;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.grfc.edu.vgviewer.figures.Coordinate;
import ru.grfc.edu.vgviewer.figures.Figure;

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
    private String labelText;
    private String name;
    
    private String printToFileStr;

    private FigureEnum figureType;

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

    public String getLabelText() {
        return labelText;
    }

    public FigureEnum getFigureType() {
        return figureType;
    }

    public String getPrintToFileStr() {
        return printToFileStr;
    }
    
    public String getName() {
        return name;
    }

    public FigureParams(Map<String, Object> mapParams) throws Exception {
        FigureEnum figureType = (FigureEnum) mapParams.get("figureType");
        if (figureType == null) {
            throw new Exception("Didn't set figure type");
        }

        // Устанавливаем всем приватным полям данного класса значения
        List<Field> allPrivateFields = getPrivateFields(FigureParams.class);
        for (Map.Entry<String, Object> entry : mapParams.entrySet()) {
            Field field = null;
            try {               
                field = getFieldByName(allPrivateFields, entry.getKey());
                field.set(this, entry.getValue());
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(FigureParams.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(FigureParams.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(FigureParams.class.getName()).log(Level.SEVERE, null, ex);
            }
        };

        String needParamsStr = figureType.getTemplateToStr();
        String[] keyValuePairs = needParamsStr.split(" ");
        List<String> listParams = new ArrayList();
        for (String pair : keyValuePairs) {
            String[] entry = pair.split("=");
            listParams.add(entry[0].trim());
        }

        List params = new ArrayList();
        for (String elem : listParams) {
            Field field = null;
            try {
                if ("label".equalsIgnoreCase(elem)) {
                    params.add(this.labelText == null ? "" : this.labelText);
                } else {
                    field = getFieldByName(allPrivateFields, elem);

                    if ("color".equalsIgnoreCase(elem)) {
                        Color color = (Color) field.get(this);
                        String str = "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
                        params.add(str);
                    } else if ("fill".equalsIgnoreCase(elem) || "blunt".equalsIgnoreCase(elem)) {
                        String str = (boolean) field.get(this) ? "true" : "false";
                        params.add(str);
                    } else {
                        params.add(field.get(this));
                    }
                }
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(FigureParams.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(FigureParams.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(FigureParams.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        this.printToFileStr = "";        
        for (int i = 0; i < listParams.size(); i++) {
            this.printToFileStr = this.printToFileStr + " " + listParams.get(i) + "=" + params.get(i);
        }

        this.printToFileStr = figureType.getShortName() + this.printToFileStr;//String.format(figureType.getTemplateToStr(), params.toArray());
    }

    public FigureParams(FigureEnum figureType, String parametrs) throws Exception {
        if (figureType == null) {
            throw new Exception("Didn't set figure type");
        }
        this.figureType = figureType;
        Pattern pattern = Pattern.compile(figureType.getRegExp(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(parametrs);
        if (!matcher.matches()) {
            throw new Exception("Can't parse parametrs string: " + parametrs);
        }

        String[] keyValuePairs = parametrs.split(" ");
        Map<String, String> mapParams = new HashMap<>();
        for (String pair : keyValuePairs) {
            String[] entry = pair.split("=");
            if (entry.length == 2) {
                mapParams.put(entry[0].trim(), entry[1].trim());
            } else if (entry.length == 1) {
                mapParams.put(entry[0].trim(), "");
            } else {
                throw new Exception("Error parser values: " + Arrays.toString(entry));
            }
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
            if (mapParams.get("label") != null) {
                labelText = String.valueOf(mapParams.get("label"));
            }
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

    private List<Field> getPrivateFields(Class<?> type) {
        List<Field> result = new ArrayList<Field>();

        Class<?> i = type;
        while (i != null && i != Object.class) {
            for (Field field : i.getDeclaredFields()) {
                if (!field.isSynthetic()) {
                    result.add(field);
                }
            }
            i = i.getSuperclass();
        }
        return result;
    }

    private Field getFieldByName(List<Field> allFields, String fieldName) throws NoSuchFieldException {
        String internedName = fieldName.intern();
        for (int i = 0; i < allFields.size(); i++) {
            if (allFields.get(i).getName() == internedName) {
                return allFields.get(i);
            }
        }
        throw new NoSuchFieldException("fieldName = " + fieldName);
    }

}
