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
public class SLinkedList {
    
    public LinkedList<Symbol> list;
    
    public SLinkedList(){
        list = new LinkedList();
    }
    
    public boolean addSymbol(String str){
        
        return list.add(new Symbol(str));
    }
    
    public boolean findSymbol(String str){
        Symbol s = new Symbol(str);
        for(int i = 0; i < list.size(); i++){
            if( s.getIdentifier().equals( list.get(i).getIdentifier() ) )
                return true;
        }
        return false;
    }
}
