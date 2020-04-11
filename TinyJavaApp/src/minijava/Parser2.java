/*
 * By:     Matthew Fischer
 * Date:   
 */
package minijava;

import java.util.StringTokenizer;

/**
 *
 * @author Matthew Fischer
 */
public class Parser2 {
    
    StringTokenizer doubleTokenizer;
    
    
    public boolean parseLine(int row, String line){
        //parse one line, until error, or end
        StringTokenizer st = new StringTokenizer(line," ");
        
        Token token = null;
        int col = 0;
        boolean last_is_semicolon = false;
        boolean valid_line_code = false;
        
        // WORD
        while(st.hasMoreTokens()){
            // GET TOKEN
            String check = st.nextToken();
            
            // LAST CHARACTER IS SEMI COLON
            if(';' == check.charAt(check.length()-1)){
                last_is_semicolon = true;
                // DELETE SEMICOLON
                check = check.substring(0,check.length()-1);
            }
            // CHECK FOR NUMBER
            if(token == null){
                token = numbersCheck(check,row,col);
                
            }
            // CHECK FOR RESERVED WORD
            if(token == null){
                token = isReservedWord(check,row,col);//if else int double program begin end
                // IS A RESERVED WORD

            }
            // CHECK IF IDENTIFIER
            if(token == null){
                token = isIdentifier(check,row, col);// VARIABLE NAME
                // IS AN IDENTIFIER

            }
            // CHECK IF ASSIGNMENT OPERATOR
            if(token == null){
                token = is_assignment_operator(check,row, col);// VARIABLE NAME
                // IS AN IDENTIFIER

            }
            // CHECK IF LOGICAL OPERATOR
            if(token == null){
                token = logical_operator(check,row, col);// VARIABLE NAME
                // IS AN IDENTIFIER
                
            }
            // CHECK IF ARITHMETIC OPERATOR
            if(token == null){
                token = arithmetic_operators(check,row, col);// VARIABLE NAME
                // IS AN IDENTIFIER
             
            }
            
            // ADD TOKEN
            if(token != null){
                Token.list.add(token);
                // ADD SEMI COLON TOKEN IF FOUND TRUE
                if(last_is_semicolon)
                    Token.list.add(new Token(TType.line_terminator,";",row,col));
            }
            
            // RESET
            col = check.length();
            token = null;
        }// END OF WHILE LOOP // END OF WORD
        
        
    
        // CHECK IF LINE STATEMENT IS VALID
        for(int i = 0; i < Token.list.size();i++){//LINE
            System.out.println(Token.list.get(i).type + " " + Token.list.get(i).text);
            // check valid line
            
            // reset arraylist
           
        }
        return true;
        // return false;

    }
    // IS A NUMBER
    public Token numbersCheck(String str, int row, int col){
        int digit = 0;
        String numbers = "";
        Token t = null;
        
        // IS CHAR A NUMBER
        // FIRST CHAR IS NUMBER
        if(str.charAt(0) >= '0' && str.charAt(0) <= '9'){
            numbers = str.charAt(0) + numbers;
            // MORE THAN ONE DIGIT NUMBER
            if(str.length()>1){
                digit++;
                while(digit < str.length() && str.charAt(digit) >= '0' && str.charAt(digit) <= '9'){
                    numbers = str.charAt(digit) + numbers;
                    digit++;
                }
            }
            
            // END IS NOT VALID
            if( digit == str.length()-1 ){
                
                // DOUBLE CHECK
                if(str.contains(".")){
                    if(isDouble(str))
                        t = new Token(TType.doubleN,numbers,row,col);  
                }
                else
                    t = new Token(TType.integerN,numbers,row,col);   
            }  
        }// END OF NUMBER   
        return t;
    }
    
    // IS A RESERVED WORD
    public Token isReservedWord(String str, int row, int col){
        
        if(str.equals("PROGRAM"))
            return new Token(TType.reservedWords,str,row,col);
        if( str.equals("BEGIN") )
            return new Token(TType.reservedWords,str,row,col);
        if( str.equals("END"))
            return new Token(TType.reservedWords,str,row,col);
        if(str.equals("if"))
            return new Token(TType.reservedWords,str,row,col);
        if(str.equals("else"))
            return new Token(TType.reservedWords,str,row,col);
        if (str.equals("int") )
            return new Token(TType.reservedWords,str,row,col);
        if( str.equals("double"))
            return new Token(TType.reservedWords,str,row,col);
        return null;
        
    }
    
    
    // IS AN IDENTIFIER
    public Token isIdentifier(String str,int row, int col){
        int digit = 0;
        Token t = null;
        
        // FIRST CHAR OF IDENTIFIER
        if(str.charAt(digit) == '_' || str.charAt(digit) >= 'A' && str.charAt(digit) <= 'z'){
            digit++;
         
            // OTHER CHAR OF IDENTIFIER
            while( digit < str.length() && ( (str.charAt(digit) >= 'A' && str.charAt(digit) <= 'z') || str.charAt(digit) == '_' || (str.charAt(digit) >= '0' && str.charAt(digit) <= '9') ) ) {
                digit++;
            }
            
            // END IS NOT VALID
            if( digit != str.length()-1 )
                t = new Token(TType.identifier,str,row,col);
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
    public Token arithmetic_operators(String value,int row,int col){
        Token t = null;
        if( value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/") )
            t = new Token(TType.arithmetic_operator,value,row,col);
            
       return t;
    }
    
    // RELATIONAL OPERATORS
    public Token relational_operators(String value,int row,int col){
        Token t = null;
        if(value.equals("==") || value.equals("!=") || value.equals(">") || value.equals("<") || value.equals("<=") || value.equals(">="))
            t = new Token(TType.relational_operator,value,row,col);
            
       return t;
    }
    
    // LOGICAL OPERATORS
    public Token logical_operator(String value,int row,int col){
        Token t = null;
        if(value.equals("&&") || value.equals("||"))
            t = new Token(TType.logical_operator,value,row,col);
            
       return t;
    }
    // IS ASSIGNMENT OPERATOR
    public Token is_assignment_operator(String value,int row,int col){
        Token t = null;
        if(value.charAt(0) == '=')
            t = new Token(TType.assignment_token,value,row,col);
            
       return t;
    }
    //Expression
    public boolean is_expression(Type first,Type second,Type third){
        
        if(first == TType.identifier || first == TType.doubleN || first == TType.integerN)
            return true;
        return false;
    }
    
    // STATEMENTS
    public boolean check_statements(Type first,Type second,Type third,Type fourth){
        // TYPE DECLARATION 
        boolean type_declaration = false;
        if(first == TType.integerN || first == TType.doubleN){
            if(second == TType.identifier)
                type_declaration = true;
            
        }
        // TYPE DECLARATION INTIALIZED
        if(type_declaration && second == TType.assignment_token && third == TType.expression){
            if(second == TType.identifier)
                return true;
            
        }
        return false;
    }
    
    // grammer rules
}

