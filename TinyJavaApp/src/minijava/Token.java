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
public class Token {
    
    
    private TType type;
    private String text;
    
    private int row;// TOKEN ROW
    private int col;//  TOKEN COL
    
    // DEFAULT CONSTRUCTOR
    public Token(){
        row = 0;
        col = 0;
        text = "";
    }
    
    // CONSTRUCTOR
    public Token(TType type, String text, int row, int col){
        this.type = type;
        this.text = text;
        this.row = row;
        this.col = col;
    }
    
    

    // GETTERS AND SETTERS
    public TType getType() {
        return type;
    }

    public void setType(TType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int line) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
    
    
    
    
    
}
