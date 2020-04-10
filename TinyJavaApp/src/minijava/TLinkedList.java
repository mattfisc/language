/*
 * By:     Matthew Fischer
 * Date:   TLinkedList
 */
package minijava;

import java.util.LinkedList;

/**
 *
 * @author Matthew Fischer
 */
public class TLinkedList {
    
    public LinkedList<Token> list;
    
    public TLinkedList(){
        list = new LinkedList();
    }
    
    public boolean addToken(TType type, String text, int row, int col){
        
        return list.add(new Token(type, text, row, col));
    }
    
    // PRINT ALL ATTRIBUTES
    public void printToken(Token t){
        System.out.println("Type: " + t.getType() + " int:" 
                +t.getText() + "\nat row: " + t.getRow()+ " col: " 
                + t.getCol());
    }
}
