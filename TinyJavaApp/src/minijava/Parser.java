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

    public boolean parseLine(String line,int row, int col){
        //parse one line, until error, or end
        Token token = null;



        if(col >= line.length()){
            return true;
        }
        // WHITE SPACE
        if(token == null){
            token = is_whitespace(line,row,col);
        }
        // CHECK FOR NUMBER
        if(token == null){
            token = is_numbersCheck(line,row,col);
        }
        // CHECK IF RESTRICTED
        if(token == null){
            token = is_restricted(line,row, col);// VARIABLE NAME
        }
        // CHECK FOR RESERVED WORD
        if(token == null){
            token = is_reservedWord(line,row,col);//if else int double program begin end
            //  is_whitespace( str.charAt(col+ reserved[i].length() 
        }
        // CHECK IF IDENTIFIER
        if(token == null){
            token = is_identifier(line,row, col);// VARIABLE NAME
        }
        
        // CHECK IF LOGICAL OPERATOR
        if(token == null){
            token = is_logical_operator(line,row, col);// VARIABLE NAME
        }
        // CHECK IF ARITHMETIC OPERATOR
        if(token == null){
            token = is_arithmetic_operators(line,row, col);// VARIABLE NAME
        }
        // CHECK IF LINE TERMINATOR
        if(token == null){
            token = line_terminator(line,row, col);// VARIABLE NAME
        }
        if(token == null){
            token = is_relational_operators(line,row, col);// VARIABLE NAME
        }
        // CHECK IF ASSIGNMENT OPERATOR
        if(token == null){
            token = is_assignment_operator(line,row, col);// VARIABLE NAME
        }
        // CHECK IF ASSIGNMENT OPERATOR
        if(token == null){
            token = is_parenthesis(line,row, col);// VARIABLE NAME
        }
        // CHECK IF BLOCK
        if(token == null){
            token = is_block(line,row, col);// VARIABLE NAME
        }
        // CHECK IF PERIOD  
        if(token == null){
            token = is_decimal(line,row, col);// VARIABLE NAME
        }
        

        if(token != null){
            col = col + token.text.length();
            print_token(token);
        }
        
        // END OF LINE
        if( col >= line.length() ){
            // CHECK VALID STATMENT
            // is_statement();
            return true;
        }
        else{
            // ERROR PRINT 

            return parseLine(line,row,col);
        }
        
//        System.out.println("Error on row:" + row + " col: " + col + " invalid token.");
//        return false;
    }// END OF PARSE LINE
    // IS A NUMBER
    public Token is_numbersCheck(String str, int row, int col){
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
            is_double(col);
        }// END OF NUMBER   
        return t;
    }

    // IS A RESERVED WORD
    public Token is_reservedWord(String str, int row, int col){
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
    public Token is_identifier(String str,int row, int col){
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
    public boolean is_double(String value){
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
    public Token is_arithmetic_operators(String str,int row,int col){
        Token t = null;
        if(str.charAt(col) == '+' || str.charAt(col) == '-' || str.charAt(col) == '*' || str.charAt(col) == '/')
            t = new Token(TType.arithmetic_operator,str.substring(col, col+1),row,col);
            
       return t;
    }
    
    // RELATIONAL OPERATORS
    public Token is_relational_operators(String str,int row,int col){
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
    public Token is_logical_operator(String str,int row,int col){
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
        char[] w ={' ','\n','\t','\r'};
        for(int i = 0; i < w.length; i++){
            if( str.charAt(col) == w[i]){
                return new Token(TType.white_space," ",row,col);
            }
        }
            
       return t;
    }
    // PARENTHESIS
    public Token is_parenthesis(String str,int row,int col){
        Token t = null;
        String[] paren ={"(",")"};
        for(int i = 0; i < paren.length; i++){
            if( str.startsWith(paren[i], col) ){
                return new Token(TType.parenthesis,paren[i],row,col);
            }
        }
       return t;
    }
    
    // BLOCK
    public Token is_block(String str,int row,int col){
        Token t = null;
        String[] block ={"{","}"};
        for(int i = 0; i < block.length; i++){
            if( str.startsWith(block[i], col) ){
                return new Token(TType.block,block[i],row,col);
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
    
    public Token is_decimal(String str,int row,int col){
        Token t = null;
        if(str.charAt(col) == '.')
            t = new Token(TType.decimal,".",row,col);

       return t;
    }
    
    public Token is_restricted(String str,int row,int col){
        String temp = str.substring(col);

        String[] r = {"IF","ELSE","INT","DOUBLE"};
        for(int i = 0; i < r.length; i++){
            if( str.startsWith(r[i], col) ){
                return new Token(TType.restricted,r[i],row,col);
            }
        }
        
        return null;
        
    }
    
    //PRINT TOKEN
    public void print_token(Token token){
        if(token.type != TType.white_space)
            System.out.println(token.type + ": " + token.text);
            
        else
            System.out.println(token.type + ": " + " ");

    }
    
    // CHECK ALL NUMBERS IF DOUBLE
    public void is_double(int index){
        if(Token.list.size() > 1){
            if(Token.list.get(index).type == TType.number){
                // IF DOUBLE AND SIZE IS BIGGER THAN 2

                if(Token.list.get(index-1).type == TType.decimal 
                    && Token.list.get(index-2).type == TType.number){
                    // EDIT TOKEN TYPE
                    Token.list.get(index-2).type = TType.doubleN;
                    //EDIT TOKEN TEXT
                    Token.list.get(index-2).text = Token.list.get(index-2).text 
                            + Token.list.get(index-1).text + Token.list.get(index);
                    Token.list.remove(index-1);
                    Token.list.remove(index);     
                }
            }
            
        }
    }
//    public void is_statement(){
//        
//    }
}
