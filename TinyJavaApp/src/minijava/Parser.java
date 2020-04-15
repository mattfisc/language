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
        Token l = new Token(TType.line,line,row,col);
        
        if(col == 0){
            Token.list.add(l);
            //Tester
            System.out.println("Code\t: " + l.text);
        }

        if(col >= line.length()){
            Token.list.add(new Token(TType.white_space," ",row,col));
            print_token(Token.list.peekLast()); // PRINT LAST TOKEN WHITESPACE
            return true;
        }
        // WHITE SPACE
        if(token == null){
            token = is_whitespace(line,row,col);
        }
        // COMMENT
        if(token == null){
            token = is_comment(line,row,col);
        }
        // CHECK FOR NUMBER
        if(token == null){
            token = is_numbersCheck(line,row,col);
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
            Token.list.add(token);
            print_token(token);
        }
        
        // END OF LINE
      
            // CHECK VALID STATMENT
        return parseLine(line,row,col);
        

    }// END OF PARSE LINE
    // IS A NUMBER
    public Token is_numbersCheck(String str, int row, int col){
        int digit = 0;
        String numbers = "";
        Token t = null;
        boolean found_dot = false;
        // IS CHAR A NUMBER
        // FIRST CHAR IS NUMBER
        if(str.charAt(col) >= '0' && str.charAt(col) <= '9' || str.charAt(col) == '.'){
            numbers += str.charAt(col);
            
            // MORE THAN ONE DIGIT NUMBER
            digit++;
            
            while(digit+col < str.length() && 
                    ((str.charAt(col+digit) >= '0' && str.charAt(col+digit) <= '9') 
                    || (str.charAt(col+digit) == '.' && !found_dot))){
                numbers = numbers + str.charAt(col+digit);
                digit++;
            }

            t = new Token(TType.number,numbers,row,col);
            
            
        }// END OF NUMBER   
        return t;
    }
    // CHECK DOUBLE OR INTEGER
    public boolean is_double(String str){
        boolean is_double = false;
        for(int i = 0; i < str.length(); i++){
            // CONTAINS ONE DECIMAL POINT
            if(str.charAt(i) == '.' && is_double == false){
                is_double = true;
            }
            // CONTAINS TWO DECIMAL POINT
            if(str.charAt(i) == '.' && is_double == true){
                is_double = false;
                break;
            }
                
        }
        return is_double;
    }

    // IS A RESERVED WORD
    public Token is_reservedWord(String str, int row, int col){
 
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
           
            //IS IDENTIFIER
            String[] reserved = {"PROGRAM","BEGIN","END","if","else","int","double"};
            String temp = str.substring(col, col+digit);
            for(int i = 0; i < reserved.length; i++){
                if( temp.equals(reserved[i]))
                    t = new Token(TType.reservedWords,reserved[i],row,col);



            }
           
        }
        // ALL OTHER CHAR OF IDENTIFIER
        return t;
        
        

        
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
            if( str.charAt(col) == '&' && str.charAt(col+1) == '&')
                return new Token(TType.logical_operator,logical[i],row,col);
            else if( str.charAt(col) == '|' && str.charAt(col+1) == '|')
                return new Token(TType.logical_operator,logical[i],row,col);
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
    // IS ASSIGNMENT OPERATOR
    public Token is_comment(String str,int row,int col){
        Token t = null;
        if(str.length()-col > 1 && str.charAt(col+1) == '/' && str.charAt(col+2) == '/' )
            t = new Token(TType.comment,str.substring(col),row,col);
            
       return t;
    }

    public Token is_whitespace(String str, int row, int col){
        int digit = 0;
        Token t = null;
        
        // FIRST CHAR
        if(str.charAt(col) == ' ' || str.charAt(col) == '\t' 
                || str.charAt(col) == '\n' || str.charAt(col) == '\r'){
            digit++;
            // OTHER CHAR OF IDENTIFIER
            while(digit+col < str.length() && (str.charAt(col) == ' ' || str.charAt(col) =='\t' 
                || str.charAt(col) == '\n' || str.charAt(col) == '\r')){
                digit++;
            }
           
            t = new Token(TType.white_space," ",row,col); 
           
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
    
    
    //PRINT TOKEN
    public void print_token(Token token){
        if(token.type != TType.comment)
            System.out.println(token.text + "\t: " + token.type );
            
        else
            System.out.println(token.text + " : " + token.type);

    }
    

}
