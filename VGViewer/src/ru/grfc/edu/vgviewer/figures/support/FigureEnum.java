package ru.grfc.edu.vgviewer.figures.support;

/**
 *
 * @author gsv
 */
public enum FigureEnum {
    ELLIPSE("Элипс",
            "w=20 h=20 fx=10 fy=10 color=#FFCCFF fill=true",
            "^w=\\d{1,4}\\s+h=\\d{1,4}\\s+fx=\\d{1,4}\\s+fy=\\d{1,4}\\s+color="
            // Название цвета
            + "((RED)|(BLUE)|(GREEN)|(YELLOW)|(BLACK)|" 
            // Формат записи цвета #C6A6F6 или #FFCCFF
            + "(#?([a-f\\d]{3}|[a-f\\d]{6}))|" 
            // Формат записи цвета rgb(255,255,255) - простое регулярное выражение, полноценное длинное
            + "rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\))" 
            + "\\s+fill=((true)|(false))(\\s+label=.*)?$",
            // Сокращённое название
            "EL",
            // Формат вывода в строку
            "w=%4d h=%4d fx=%4d fy=%4d color=%s fill=%s label=%s"
    ),
    LINE("Линия",
            "fx=20 fy=20 lx=10 ly=10 color=RED",
            "^fx=\\d{1,4}\\s+fy=\\d{1,4}\\s+lx=\\d{1,4}\\s+ly=\\d{1,4}\\s+color="
            + "((RED)|(BLUE)|(GREEN)|(YELLOW)|(BLACK)|"
            + "(#?([a-f\\d]{3}|[a-f\\d]{6}))|"
            + "rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\))(\\s+label=.*)?$",
            "LI",
            "fx=%4d fy=%4d lx=%4d ly=%4d color=%s"
    ),
    PARALLELOGRAM("Параллелограмм",
            "blunt=true w=20 h=20 fx=10 fy=10 color=RED fill=true",
            "^blunt=((true)|(false))\\s+w=\\d{1,4}\\s+h=\\d{1,4}\\s+fx=\\d{1,4}\\s+fy=\\d{1,4}\\s+color="
            + "((RED)|(BLUE)|(GREEN)|(YELLOW)|(BLACK)|"
            + "(#?([a-f\\d]{3}|[a-f\\d]{6}))|"
            + "rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\))"
            + "\\s+fill=((true)|(false))(\\s+label=.*)?$",
            "PA",
            "blunt=%s w=%4d h=%4d fx=%4d fy=%4d color=%s fill=%s label=%s"
    ),
    RECTANGLE("Прямоугольник",
            "w=20 h=20 fx=10 fy=10 color=RED fill=false",
            "^w=\\d{1,4}\\s+h=\\d{1,4}\\s+fx=\\d{1,4}\\s+fy=\\d{1,4}\\s+color="            
            + "((RED)|(BLUE)|(GREEN)|(YELLOW)|(BLACK)|"
            + "(#?([a-f\\d]{3}|[a-f\\d]{6}))|"
            + "rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\))"
            + "\\s+fill=((true)|(false))(\\s+label=.*)?$",
            "RE",
            "w=%4d h=%4d fx=%4d fy=%4d color=%s fill=%s label=%s"
    ),
    RHOMBUS("Ромб",
            "w=100 fx=200 fy=200 color=rgb(100,200,60) fill=true",
            "^w=\\d{1,4}\\s+fx=\\d{1,4}\\s+fy=\\d{1,4}\\s+color="
            + "((RED)|(BLUE)|(GREEN)|(YELLOW)|(BLACK)|"
            + "(#?([a-f\\d]{3}|[a-f\\d]{6}))|"
            + "rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\))"
            + "\\s+fill=((true)|(false))(\\s+label=.*)?$",
            "RH",
            "w=%4d fx=%4d fy=%4d color=%s fill=%s label=%s"
    ),
    ROUND_RECTAGLE("Закруглённый прямоугольник",
            "w=200 h=200 aw=50 ah=50 fx=150 fy=200 color=#C6A6F6 fill=false",
            "^w=\\d{1,4}\\s+h=\\d{1,4}\\s+aw=\\d{1,4}\\s+ah=\\d{1,4}\\s+fx=\\d{1,4}\\s+fy=\\d{1,4}\\s+color="
            + "((RED)|(BLUE)|(GREEN)|(YELLOW)|(BLACK)|"
            + "(#?([a-f\\d]{3}|[a-f\\d]{6}))|"
            + "rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\))"
            + "\\s+fill=((true)|(false))(\\s+label=.*)?$",
            "RO",
            "w=%4d h=%4d aw=%4d ah=%4d fx=%4d fy=%4d color=%s fill=%s label=%s"
    );

    private final String name;
    private final String template;
    private final String regExp;
    private final String shortName;
    private final String templateToStr;
    
    private FigureEnum(String name, String template, String regExp, String shortName, String templateToStr) {
        this.name = name;
        this.template = template;
        this.regExp = regExp;
        this.shortName = shortName;
        this.templateToStr = templateToStr;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }
    
    public boolean equalsShortName(String otherShortName) {
        return shortName.equals(otherShortName);
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
    
    public String getShortName() {
        return shortName;
    }
    
    public String getTemplateToStr() {
        return templateToStr;
    }
}
