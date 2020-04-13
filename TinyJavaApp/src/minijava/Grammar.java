/*
 * By:     Matthew Fischer
 * Date:   
 */
package minijava;

/**
 *
 * @author Matthew Fischer
 */
public class Grammar {
    
    int index = 0;
    int state = 0;
    
    
    public void validate_grammar(){
        while(index < Token.list.size() && state >= 0 && state < 1000){
            
            if(Token.list.get(index).type == TType.line){
                index++;
                continue;
            }
            
            switch(state){
                case 0:
                    state = process_program_statement();
                    break;
                default:
                    state = -100;
            }// END SWITCH
            
            
            
            
        }// END OF WHILE
        
        // ERROR PROCESSING
        switch(state){
            case -100:
                System.out.println("Error in process program statement method");
                break;
            case -101:
                System.out.println("Error no program found");
                break;
            case -102:
                System.out.println("Error no program name found");
                break;
            case -103:
                System.out.println("Error reserved word");
                break;
            case -104:
                System.out.println("Error semi colon");
                break;
            case -105:
                System.out.println("Error assignment token");
                break;
            case -106:
                System.out.println("Error is number");
                break;
            case -107:
                System.out.println("Error ");
                break;
            case -108:
                System.out.println("Error ");
                break;
            default:
                
        }// END OF SWITCH
        
        
        
    }

    // FIRST LINE OF PROGRAM
    private int process_program_statement() {
        // NOT FOUND STATEMENTS
        if(!is_program(0))
            return -101;// TOKEN PROGRAM
        if(!is_identifier(1))
            return -102;// TOKEN IDENTIFIER
        if(!is_reserved_words(0))
            return -103;// TOKEN RESERVED WORD
        if(!is_semicolon(0))
            return -104;// TOKEN SEMI COLON
        if(!is_assignment_token(0))
            return -105;// TOKEN ASSIGNMENT SYMBOL
        if(!is_number(0))
            return -106;// TOKEN NUMBER
        
        return 1000;
    }

    private boolean is_program(int min_whitespace) {
        if(!is_whitespace(min_whitespace))// MISSING WHITESPACE
            return false;
        return is_program_found();
        
    }

    
    // IS WHITESPACE
    private boolean is_whitespace(int min_whitespace) {
        if(index >= Token.list.size())
            return false;
        // IF THERE IS A WHITESPACE
        if(min_whitespace > 0){
            if(Token.list.get(index).type != TType.white_space){
                return false;
            }
        }
        // EATS ALL WHITESPACE
        while(index < Token.list.size() && Token.list.get(index).type == TType.white_space){
            index++;
        }
        return true;
    }

    private boolean is_program_found() {
        if(index >= Token.list.size())
            return false;
        // ERROR IN CREATING RESERVED WORD IN PARSER LINE 130 ABOUT END OF LINES
        if(Token.list.get(index).text.equals("PROGRAM") && Token.list.get(index).type == TType.reservedWords){
            index++;
            return true;
        }
        return false;
    }
    private boolean is_identifier(int min_whitespace) {
            if(!is_whitespace(min_whitespace))// MISSING WHITESPACE
                return false;
            return is_identifier_found();
        }

    private boolean is_identifier_found() {
        if(index >= Token.list.size())
            return false;
        if(Token.list.get(index).type == TType.identifier){
            // ADD SYMBOL
            // IF EXISTS DONT ADD
            index++;
            return true;
        }
        return false;
    }

    private boolean is_reserved_words(int min_whitespace) {
        if(!is_whitespace(min_whitespace))// MISSING WHITESPACE
            return false;
        return is_reserved_words_found();
    }

    private boolean is_reserved_words_found() {
        // IF LIST SIZE IS SMALLER THAN INDEX
        if(index >= Token.list.size())
            return false;
        if(Token.list.get(index).type == TType.reservedWords){
            index++;
            return true;
        }
        return false;
    }

    private boolean is_assignment_token(int min_whitespace) {
        if(!is_whitespace(min_whitespace))// MISSING WHITESPACE
                return false;
            return is_assignment_token_found();
    }

    private boolean is_assignment_token_found() {
        // IF LIST SIZE IS SMALLER THAN INDEX
        if(index >= Token.list.size())
            return false;
        // COMPARE TYPE AND TEXT
        if(Token.list.get(index).type == TType.assignment_token){
            index++;
            return true;
        }
        return false;
    }

    private boolean is_number(int min_whitespace) {
        if(!is_whitespace(min_whitespace))// MISSING WHITESPACE
                return false;
            return is_number_found();    
    }

    private boolean is_number_found() {
        // IF LIST SIZE IS SMALLER THAN INDEX
        if(index >= Token.list.size())
            return false;
        // COMPARE TYPE AND TEXT
        if(Token.list.get(index).type == TType.number ){
            index++;
            return true;
        }
        return false;
    }

    private boolean is_semicolon(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}

   