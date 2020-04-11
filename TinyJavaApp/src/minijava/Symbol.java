/*
 * By:     Matthew Fischer
 * Date:   4/10/2020
 */
package minijava;


import java.util.LinkedList;


/**
 *
 * @author Matthew Fischer
 */
public class Symbol {

    public static LinkedList<Symbol> list = new LinkedList<Symbol>();
    
    public String identifier;
    public TType type;
    
    public double dValue;
    public int iValue;

    // CONSTRUCTOR
    public Symbol(String identifier){
        this.identifier = identifier;
        dValue = 0.0;
        iValue = 0;
    }
    
    // FIND SYMBOL
    public static boolean findSymbol(String identifier){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).identifier == identifier)
                return true;
        }
        return false;
    }
}