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
    public String identifierType;
    public String identifier;
    public String valueStr;
    public double dValue;
    public int iValue;

    // CONSTRUCTOR
    public Symbol(Token identifierType,Token identifier){
        
        this.identifierType = identifierType.text;
        
        this.identifier = identifier.text;
        
        this.valueStr = "";
        dValue = 0.0;
        iValue = 0;
    }
    
    
    // FIND SYMBOL
    public static boolean findSymbol(String identifier){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).identifier.equals(identifier))
                return true;
        }
        return false;
    }
    
    // PRINT SYMBOLS LIST
    public static void printSymbols(){
        System.out.println("\n------------------\n"
                + "TYPE:\tIDENTIFIER:\n------------------");
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).identifierType +"\t"+ list.get(i).identifier);
        }
    }
    
    
}