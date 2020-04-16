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
public class Token {
    
    public static LinkedList<Token> list = new LinkedList<Token>();
    
    public TType type;
    public String text;
    public int row;// TOKEN ROW
    public int col;//  TOKEN COL
    
    // DEFAULT CONSTRUCTOR
    public Token(){
        text = "";
        row = 0;
        col = 0;
        
    }
    
    // CONSTRUCTOR
    public Token(TType type, String text, int row, int col){
        this.type = type;
        this.text = text;
        this.row = row;
        this.col = col;
    }
    
}
