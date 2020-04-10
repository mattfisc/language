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
            if(';' == check.charAt(check.length()-1))
                check = check.substring(0,check.length()-1);//SUBTRACT SEMI COLON
            
            // CHECK FOR NUMBER
            if(token == null){
                value = isNumber(row,digit,check);
                // IS A NUMBER
                if(!value.equals("")){
                    token = new Token(TType.number,value,row,col);
                    System.out.println(token.type + token.text);
                }
            }
//            // CHECK FOR RESERVED WORD
//            if(token == null){
//                value = isReservedWord(row,digit,check);//if else int double program begin end
//                // IS A RESERVED WORD
//                if(!value.equals("")){
//                    token = new Token(TType.identifier,value,row,col);
//                    System.out.println(token.type + token.text);
//                }
//            }
            // CHECK IF IDENTIFIER
            if(token == null){
                value = isIdentifier(row,digit,check);// VARIABLE NAME
                // IS AN IDENTIFIER
                if(!value.equals("")){
                    token = new Token(TType.identifier,value,row,col);
                    System.out.println(token.type + token.text);
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
    // Is Number
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
    
    public String isReservedWord(int row, int digit, String str){
        
        if(str.equals("PROGRAM") && str.equals("BEGIN") && str.equals("END") 
                && str.equals("if") && str.equals("else") && str.equals("int") && str.equals("double"))
            return "true";
        return "";
        
    }
    
    
    
    public String isIdentifier(int row,int digit,String str){
        String valid = "";
        
        //FIRST CHAR OF IDENTIFIER
        if(str.charAt(0) >= 'A' && str.charAt(0) <= 'z' && str.charAt(0) == '_'){
            valid = str.charAt(0) + isIdentifier(row,digit++,str);
            System.out.println("is 1valid: " + valid);
            
           
        }
        //ALL OTHER CHAR OF IDENTIFIER
        else if(str.charAt(digit) >= 'A' && str.charAt(digit) <= 'z' && str.charAt(digit) == '_' && str.charAt(digit) >= '0' && str.charAt(digit) <= '9'){
            valid += str.charAt(digit) + isIdentifier(row,digit++,str);
            System.out.println("is 2valid: " + valid);
           
        }

        System.out.println("is valid: " + valid);
        return valid;
    }
    
    
    // grammer rules
}
