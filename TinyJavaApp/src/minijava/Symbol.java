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
    
    public static LinkedList<Symbol> list;
    
    String identifier;
    TType type;
    
    double dValue;
    int iValue;

    
    public Symbol(String identifier){
        this.identifier = identifier;
        dValue = 0.0;
        iValue = 0;
    }
    
    public Symbol(String identifier, int iValue, TType type){
        this.type = type;
        this.identifier = identifier;
        dValue = 0.0;
        this.iValue = iValue;
    }
    
    public Symbol(String identifier, double dValue, TType type){
        this.type = type;
        this.identifier = identifier;
        this.dValue = dValue;
        iValue = 0;
    }
    
    
    public boolean add(String identifier){
        return list.add(new Symbol(identifier));
                
    }
    
    // exists symbol exists
    public boolean ifExists(String identifier){
        // true found
        
        // false not found
    }
    
    // find symbol, if found return symbol of linked list, if not found return null
    public Symbol findSymbol(String identifier){
        
    }
        
    
    
}
// if list is empty