
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nag
 */
public  class Order implements Serializable {
    
    private static final long serialVersionUID = -8358632374920029796L;
    
    String descr;
    double price;
    int unit;
    boolean q;

    public Order(String descr, double price, int unit) {
        this.descr = descr;
        this.price = price;
        this.unit = unit;
    }
    public Order(String descr, double price, int unit, String comment) {
        this.descr = descr;
        this.price = price;
        this.unit = unit;
        //this.comment = comment;
    }

}

