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
    
    public double dValue;
    public int iValue;

    // CONSTRUCTOR
    public Symbol(String identifier,String type){
        this.identifier = identifier;
        this.identifierType = type;
        dValue = 0.0;
        iValue = 0;
    }
    public Symbol(String identifier,String type,double dValue){
        this.identifier = identifier;
        this.identifierType = type;
        this.dValue = dValue;
        iValue = 0;
    }
    public Symbol(String identifier,String type,int iValue){
        this.identifier = identifier;
        this.identifierType = type;
        dValue = 0.0;
        this.iValue = iValue;
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