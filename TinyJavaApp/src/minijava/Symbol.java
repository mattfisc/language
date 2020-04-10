/*
 * By:     Matthew Fischer
 * Date:   
 */
package minijava;


import java.util.LinkedList;


/**
 *
 * @author Matthew Fischer
 */
public class Symbol {

    private String identifier;
    private TType type;
    
    private double dValue;
    private int iValue;

    // CONSTRUCTOR
    public Symbol(String identifier){
        this.identifier = identifier;
        dValue = 0.0;
        iValue = 0;
    }
    
    
    
    // SETTERS AND GETTERS
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public TType getType() {
        return type;
    }

    public void setType(TType type) {
        this.type = type;
    }

    public double getdValue() {
        return dValue;
    }

    public void setdValue(double dValue) {
        this.dValue = dValue;
    }

    public int getiValue() {
        return iValue;
    }

    public void setiValue(int iValue) {
        this.iValue = iValue;
    }
        
    
    
}