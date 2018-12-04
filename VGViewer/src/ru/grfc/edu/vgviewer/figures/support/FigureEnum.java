/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.grfc.edu.vgviewer.figures.support;

/**
 *
 * @author win
 */
public enum FigureEnum {
    ELLIPSE("Элипс",
            "w=20 h=20 fx=10 fy=10 color=RED fill=true",
            "w=\\d{1,4}\\s+h=\\d{1,4}\\s+fx=\\d{1,4}\\s+fy=\\d{1,4}\\s+color=[RED]\\s+fill=[true|false]"),
    LINE("Линия",
            "fx=20 fy=20 lx=10 ly=10 color=RED",
            "fx=\\d{1,4}\\s+fy=\\d{1,4}\\s+lx=\\d{1,4}\\s+ly=\\d{1,4}\\s+color=[RED]"),
    PARALLELOGRAM("Параллелограмм",
            "blunt=true w=20 h=20 fx=10 fy=10 color=RED fill=true",
            "fill=[true|false]\\s+w=\\d{1,4}\\s+h=\\d{1,4}\\s+fx=\\d{1,4}\\s+fy=\\d{1,4}\\s+color=[RED]\\s+fill=[true|false]"),
    RECTANGLE("Прямоугольник",
            "w=20 h=20 fx=10 fy=10 color=RED fill=true",
            "w=\\d{1,4}\\s+h=\\d{1,4}\\s+fx=\\d{1,4}\\s+fy=\\d{1,4}\\s+color=[RED]\\s+fill=[true|false]"),
    RHOMBUS("Ромб",
            "w=20 fx=10 fy=10 color=RED fill=true",
            "w=\\d{1,4}\\s+fx=\\d{1,4}\\s+fy=\\d{1,4}\\s+color=[RED]\\s+fill=[true|false]"),
    ROUND_RECTAGLE("Закруглённый прямоугольник",
            "w=200 h=200 aw=30 ah=30 fx=10 fy=10 color=RED fill=true",
            "w=\\d{1,4}\\s+h=\\d{1,4}\\s+aw=d{1,4}\\s+ah=d{1,4}\\s+fx=\\d{1,4}\\s+fy=\\d{1,4}\\s+color=[RED]\\s+fill=[true|false]");

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
