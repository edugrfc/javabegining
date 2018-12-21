package ru.grfc.edu.vgviewer.figures.support;

/**
 *
 * @author gsv
 */
public enum FigureEnum {
    ELLIPSE("Элипс",
            "w=20 h=20 fx=10 fy=10 color=#FFCCFF fill=true text=Надпись tx=20 ty=20",
            "^w=\\d{1,4}\\s+h=\\d{1,4}\\s+fx=\\d{1,4}\\s+fy=\\d{1,4}\\s+color="
            // Название цвета
            + "((RED)|(BLUE)|(GREEN)|(YELLOW)|(BLACK)|"
            // Формат записи цвета #C6A6F6 или #FFCCFF
            + "(#?([a-f\\d]{3}|[a-f\\d]{6}))|"
            // Формат записи цвета rgb(255,255,255) - простое регулярное выражение, полноценное длинное
            + "rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\))" + "\\s+fill=((true)|(false))"
            // Надпись и ее координаты 
            + "\\s+text=[A-Za-z0-9а-яА-Я]+\\s+tx=\\d{1,4}\\s+ty=\\d{1,4}$"),
    LINE("Линия",
            "fx=20 fy=20 lx=10 ly=10 color=RED text=Надпись tx=20 ty=20",
            "^fx=\\d{1,4}\\s+fy=\\d{1,4}\\s+lx=\\d{1,4}\\s+ly=\\d{1,4}\\s+color="
            + "((RED)|(BLUE)|(GREEN)|(YELLOW)|(BLACK)|"
            + "(#?([a-f\\d]{3}|[a-f\\d]{6}))|"
            + "rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\))"
            + "\\s+text=[A-Za-z0-9а-яА-Я]+\\s+tx=\\d{1,4}\\s+ty=\\d{1,4}$"),
    PARALLELOGRAM("Параллелограмм",
            "blunt=true w=20 h=20 fx=10 fy=10 color=RED fill=true text=Надпись tx=20 ty=20",
            "^blunt=((true)|(false))\\s+w=\\d{1,4}\\s+h=\\d{1,4}\\s+fx=\\d{1,4}\\s+fy=\\d{1,4}\\s+color="
            + "((RED)|(BLUE)|(GREEN)|(YELLOW)|(BLACK)|"
            + "(#?([a-f\\d]{3}|[a-f\\d]{6}))|"
            + "rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\))"
            + "\\s+fill=((true)|(false))"
            + "\\s+text=[A-Za-z0-9а-яА-Я]+\\s+tx=\\d{1,4}\\s+ty=\\d{1,4}$"),
    RECTANGLE("Прямоугольник",
            "w=20 h=20 fx=10 fy=10 color=RED fill=false text=Надпись tx=20 ty=20",
            "^w=\\d{1,4}\\s+h=\\d{1,4}\\s+fx=\\d{1,4}\\s+fy=\\d{1,4}\\s+color="
            + "((RED)|(BLUE)|(GREEN)|(YELLOW)|(BLACK)|"
            + "(#?([a-f\\d]{3}|[a-f\\d]{6}))|"
            + "rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\))"
            + "\\s+fill=((true)|(false))"
            + "\\s+text=[A-Za-z0-9а-яА-Я]+\\s+tx=\\d{1,4}\\s+ty=\\d{1,4}$"),
    RHOMBUS("Ромб",
            "w=100 fx=200 fy=200 color=rgb(100,200,60) fill=true text=Надпись tx=210 ty=210",
            "^w=\\d{1,4}\\s+fx=\\d{1,4}\\s+fy=\\d{1,4}\\s+color="
            + "((RED)|(BLUE)|(GREEN)|(YELLOW)|(BLACK)|"
            + "(#?([a-f\\d]{3}|[a-f\\d]{6}))|"
            + "rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\))"
            + "\\s+fill=((true)|(false))"
            + "\\s+text=[A-Za-z0-9а-яА-Я]+\\s+tx=\\d{1,4}\\s+ty=\\d{1,4}$"),
    ROUND_RECTAGLE("Закруглённый прямоугольник",
            "w=200 h=200 aw=50 ah=50 fx=150 fy=200 color=#C6A6F6 fill=false text=Надпись tx=160 ty=170",
            "^w=\\d{1,4}\\s+h=\\d{1,4}\\s+aw=\\d{1,4}\\s+ah=\\d{1,4}\\s+fx=\\d{1,4}\\s+fy=\\d{1,4}\\s+color="
            + "((RED)|(BLUE)|(GREEN)|(YELLOW)|(BLACK)|"
            + "(#?([a-f\\d]{3}|[a-f\\d]{6}))|"
            + "rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\))"
            + "\\s+fill=((true)|(false))"
            + "\\s+text=[A-Za-z0-9а-яА-Я]+\\s+tx=\\d{1,4}\\s+ty=\\d{1,4}$");

    private final String name;
    private final String template;
    private final String regExp;

    private FigureEnum(String name, String template, String regExp) {
        this.name = name;
        this.template = template;
        this.regExp = regExp;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getTemplate() {
        return this.template;
    }

    public String getRegExp() {
        return this.regExp;
    }
}
