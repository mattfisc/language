/*
 * By:     Matthew Fischer
 * Date:   4/10/2020
 */
package minijava;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Matthew Fischer
 */
public class Parser {
    StringTokenizer doubleTokenizer;
    
    
    public boolean parseLine(String line,int row, int col){
        //parse one line, until error, or end
       
        
        Token token = null;

        boolean last_is_semicolon = false;
        boolean valid_line_code = false;

        if(col >= line.length()){
            return true;
        }
        // WHITE SPACE
        if(token == null){
            token = is_whitespace(line,row,col);
        }
        // CHECK FOR NUMBER
        if(token == null){
            token = numbersCheck(line,row,col);

        }
        // CHECK FOR RESERVED WORD
        if(token == null){
            token = isReservedWord(line,row,col);//if else int double program begin end
            //  is_whitespace( str.charAt(col+ reserved[i].length() 
        }
        // CHECK IF IDENTIFIER
        if(token == null){
            token = isIdentifier(line,row, col);// VARIABLE NAME
        }
        
        // CHECK IF LOGICAL OPERATOR
        if(token == null){
            token = logical_operator(line,row, col);// VARIABLE NAME
        }
        // CHECK IF ARITHMETIC OPERATOR
        if(token == null){
            token = arithmetic_operators(line,row, col);// VARIABLE NAME
        }
        // CHECK IF LINE TERMINATOR
        if(token == null){
            token = line_terminator(line,row, col);// VARIABLE NAME
        }
        if(token == null){
            token = relational_operators(line,row, col);// VARIABLE NAME
        }
        // CHECK IF ASSIGNMENT OPERATOR
        if(token == null){
            token = is_assignment_operator(line,row, col);// VARIABLE NAME
        }
        // CHECK IF ASSIGNMENT OPERATOR
        if(token == null){
            token = parenthesis(line,row, col);// VARIABLE NAME
        }

        if(token != null){
            col = col + token.text.length();
            print_token(token);
        }
        
        // END OF LINE
        if( col >= line.length() ){
            return true;
        }
        else{
            return parseLine(line,row,col);
        }
        
        // return false;

    }
    // IS A NUMBER
    public Token numbersCheck(String str, int row, int col){
        int digit = 0;
        String numbers = "";
        Token t = null;
        
        // IS CHAR A NUMBER
        // FIRST CHAR IS NUMBER
        if(str.charAt(col) >= '0' && str.charAt(col) <= '9'){
            numbers = numbers + str.charAt(col);
            // MORE THAN ONE DIGIT NUMBER
            
            digit++;
            while(digit+col < str.length() && str.charAt(col+digit) >= '0' && str.charAt(col+digit) <= '9'){
                numbers = numbers + str.charAt(col+digit);
                digit++;
            }

            t = new Token(TType.number,numbers,row,col);   
              
        }// END OF NUMBER   
        return t;
    }

    // IS A RESERVED WORD
    public Token isReservedWord(String str, int row, int col){
        String temp = str.substring(col);
        
        
        String[] reserved = {"PROGRAM","BEGIN","END","if","else","int","double"};
        for(int i = 0; i < reserved.length; i++){
            if( str.startsWith(reserved[i], col) ){
                return new Token(TType.reservedWords,reserved[i],row,col);
            }
        }
        
        return null;
        
    }
    
    
    // IS AN IDENTIFIER
    public Token isIdentifier(String str,int row, int col){
        int digit = 0;
        Token t = null;
        
        // FIRST CHAR OF IDENTIFIER
        if(str.charAt(col+digit) == '_' || str.charAt(col+digit) >= 'A' && str.charAt(col+digit) <= 'Z'
                || (str.charAt(col+digit) >= 'a' && str.charAt(col+digit) <= 'z')){
            digit++;
         
            // OTHER CHAR OF IDENTIFIER
            while( digit+col < str.length() && ((str.charAt(col+digit) >= 'A' && str.charAt(col+digit) <= 'Z') 
                    || (str.charAt(col+digit) >= 'a' && str.charAt(col+digit) <= 'z')
                    || str.charAt(col+digit) == '_' 
                    || (str.charAt(col+digit) >= '0' && str.charAt(col+digit) <= '9') ) ){
                digit++;
            }
           
            t = new Token(TType.identifier,str.substring(col,col+digit),row,col); 
           
        }
        // ALL OTHER CHAR OF IDENTIFIER
        return t;
    }
    
    // CHECK DOUBLE OR INTEGER
    public boolean isDouble(String value){
        boolean is_double = false;
        
        for(int i = 0; i < value.length(); i++){
            // CONTAINS ONE DECIMAL POINT
            if(value.charAt(i) == '.'){
                // DOES NOT CONATAIN TWO DECIMAL POINTS
                if(!value.substring(i+1).contains("."))
                    return is_double;
            }
                
        }
        return is_double;
    }
    
    // ARITHMETIC OPERATORS
    public Token arithmetic_operators(String str,int row,int col){
        Token t = null;
        if(str.charAt(col) == '+' || str.charAt(col) == '-' || str.charAt(col) == '*' || str.charAt(col) == '/')
            t = new Token(TType.arithmetic_operator,str.substring(col, col+1),row,col);
            
       return t;
    }
    
    // RELATIONAL OPERATORS
    public Token relational_operators(String str,int row,int col){
        Token t = null;
        String[] relational ={"!=","==",">=","<=","<",">"};
        for(int i = 0; i < relational.length; i++){
            if( str.startsWith(relational[i], col) ){
                return new Token(TType.relational_operator,relational[i],row,col);
            }
        }
            
       return t;
    }
    
    // LOGICAL OPERATORS
    public Token logical_operator(String str,int row,int col){
        Token t = null;
        String[] logical ={"&&","||"};
        for(int i = 0; i < logical.length; i++){
            if( str.startsWith(logical[i], col) ){
                return new Token(TType.logical_operator,logical[i],row,col);
            }
        }
       return t;
    }
    // IS ASSIGNMENT OPERATOR
    public Token is_assignment_operator(String str,int row,int col){
        Token t = null;
        if(str.charAt(col) == '=')
            t = new Token(TType.assignment_token,"=",row,col);
            
       return t;
    }

    public Token is_whitespace(String str, int row, int col){
        Token t = null;
        int digit = 0;
        
       
        if(str.charAt(col+digit) == ' ' || str.charAt(col+digit) == '\n' && str.charAt(col+digit) == '\t'){
            digit++;
         
            // OTHER CHAR OF IDENTIFIER
            while(digit+col < str.length() &&  (str.charAt(col+digit) == ' ' 
                    || str.charAt(col+digit) == '\n' && str.charAt(col+digit) == '\t')) {
                digit++;
            }
           
            t = new Token(TType.white_space,str.substring(col,col+digit),row,col); 
           
        }
       return t;
    }
    // LOGICAL OPERATORS
    public Token parenthesis(String str,int row,int col){
        Token t = null;
        String[] paren ={"(",")"};
        for(int i = 0; i < paren.length; i++){
            if( str.startsWith(paren[i], col) ){
                return new Token(TType.parenthesis,paren[i],row,col);
            }
        }
       return t;
    }
    
    public Token line_terminator(String str,int row,int col){
        Token t = null;
        if(str.charAt(col) == ';')
            t = new Token(TType.line_terminator,";",row,col);
            
       return t;
    }
    
    //PRINT TOKEN
    public void print_token(Token token){
        if(token.type == TType.white_space)
            System.out.println(token.type + ": " + " ");
        else
            System.out.println(token.type + ": " + token.text);
        
       
    }
    
    
}
