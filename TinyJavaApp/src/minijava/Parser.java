/*
 * By:     Matthew Fischer
 * Date:   4/10/2020
 */
package minijava;

import java.util.StringTokenizer;

/**
 *
 * @author Matthew Fischer
 */
public class Parser {
    
    public Parser(){
        
    }
    
    public void parseLine(int row, String line){
        //parse one line, until error, or end
        StringTokenizer st = new StringTokenizer(line," ");
        
        Token token = null;
        int col = 0;
        int digit = 0;
        String value = "";
        
        while(st.hasMoreTokens()){
            // GET TOKEN
            String check = st.nextToken();
            
            // LAST CHARACTER IS SEMI COLON
            // SUBTRACT SEMI COLON
            if(';' == check.charAt(check.length()-1))
                check = check.substring(0,check.length()-1);
            
            // CHECK FOR NUMBER
            if(token == null){
                value = isNumber(row,digit,check);
                // IS A NUMBER
                if(!value.equals("")){
                    token = new Token(TType.number,value,row,col);
                    System.out.println(token.type +" "+ token.text);
                }
            }
            // CHECK FOR RESERVED WORD
            if(token == null){
                value = isReservedWord(row,digit,check);//if else int double program begin end
                // IS A RESERVED WORD
                if(!value.equals("")){
                    token = new Token(TType.reservedWords,value,row,col);
                    System.out.println(token.type +" "+ token.text);
                }
            }
            // CHECK IF IDENTIFIER
            if(token == null){
                value = isIdentifier(row,check);// VARIABLE NAME
                // IS AN IDENTIFIER
                if(!value.equals("")){
                    token = new Token(TType.identifier,value,row,col);
                    System.out.println(token.type + " "+ token.text);
                }
            }
           //if you have a token, position of next digit, \n \t " "  recursion parseline
        
        //
//        if(token != null){
//            boolean success = parseLine(row,digit,check);// recall
//        }
            
            
            
            
            
            // ADD TOKEN
            Token.list.add(token);
            
            // RESET for next iteration in word
            token = null;
            col++; // next col
            value = "";
        }// END OF WHILE LOOP

    }
    // IS A NUMBER
    public String isNumber(int row,int digit,String str){

        // IS CHAR A NUMBER
        if(str.charAt(digit) >= 48 && str.charAt(digit) <= 57){
            //i = str.charAt(0)- '0';
            
            // SINGLE DIGIT
            if(digit == str.length()-1)
                return str;
            
            str = str.charAt(digit) + isNumber(row,digit++,str.substring(digit++));
            
            
        }
        else
            return "";
        return str;
    }
    
    // IS A RESERVED WORD
    public String isReservedWord(int row, int digit, String str){
        
        if(str.equals("PROGRAM"))
            return "PROGRAM";
        if( str.equals("BEGIN") )
            return "BEGIN";
        if( str.equals("END"))
            return "END";
        if(str.equals("if"))
            return "if";
        if(str.equals("else"))
            return "else";
        if (str.equals("int") )
            return "int";
        if( str.equals("double"))
            return "double";
        return "";
        
    }
    
    
    // IS AN IDENTIFIER
    public String isIdentifier(int row,String str){
        String valid = "";
        
        // LENGTH EXIT
        if( str.length() < 1 )
            return valid;
        
        // FIRST CHAR OF IDENTIFIER
        if(str.charAt(0) >= 'A' && str.charAt(0) <= 'z'|| str.charAt(0) == '_'){
            valid += str.charAt(0) + isIdentifier(row,str.substring(1));

        }
        // ALL OTHER CHAR OF IDENTIFIER
        else if( (str.charAt(0) >= 'A' && str.charAt(0) <= 'z') || str.charAt(0) == '_' || (str.charAt(0) >= '0' && str.charAt(0) <= '9') ){
            valid += str.charAt(0) + isIdentifier(row,str.substring(1));
        }

        return valid;
    }
    
    
    // grammer rules
}
