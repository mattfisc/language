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
    public String identifierType;
    public String value;
    public double dValue;
    public int iValue;

    // CONSTRUCTOR
    public Symbol(Token identifierType,Token identifier,Token value){
        if(identifierType != null)
            this.identifier = identifier.text;
        if(identifier != null)
            this.identifier = identifier.text;
        if(value != null)
            this.value = value.text;
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