/*
 * By:     Matthew Fischer
 * Date:   
 */
package minijava;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 *
 * @author Matthew Fischer
 */
public class Symbol {
    
    private static LinkedList<Symbol> list;
    
    private String identifier;
    private TType type;
    
    private double dValue;
    private int iValue;

    
    public Symbol(String identifier){
        this.identifier = identifier;
        dValue = 0.0;
        iValue = 0;
    }
    

    public boolean add(String identifier){
        return list.add(new Symbol(identifier));
    }
    
    
    // find symbol, if found return symbol of linked list, if not found return null
    public boolean findSymbol(String identifier){
        return list.contains(new Symbol(identifier));
    }
        
    
    
}
// if list is empty