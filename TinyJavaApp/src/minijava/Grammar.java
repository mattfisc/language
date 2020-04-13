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
                    state = program_begins();
                    break;
                case 1:
                    state = declarations();
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
                System.out.println("Error reserved word begin");
                break;
            case -104:
                System.out.println("Error reserved word end");
                break;
            case -300:
                System.out.println("Error expression");
                break;  
            case -302:
                System.out.println("Error assignment token");
                break;  
            case -303:
                System.out.println("Error semicolon error");
                break;  
            case -304:
                System.out.println("Error identifier");
                break;
            case -305:
                System.out.println("Error int or double reserved word");
                break;
            case -306:
                System.out.println("Error illegal start declaration statement");
                break;  
            case -307:
                System.out.println("Error no END reserved word");
                break; 
            
            
            default: 
                
        }// END OF SWITCH

    }

    

    // FIRST LINE OF PROGRAM
    private int program_begins() {
        
            //HAS TO BE IN A ROW
            
        if(!is_program(0))
            return -101;// TOKEN PROGRAM
        if(!is_identifier(1))
            return -102;// TOKEN IDENTIFIER
        if(!is_reserved_words_begin(0))
            return -103;// TOKEN PROGRAM
        if(!is_reserved_words_end(0))
            return -307;
        // all possible options next
        // if, int, double, identifier, end
        
        return 1000;
    }
    
    public int declarations(){
        
        // TYPE DECLARATION AND TYPE DECLARATION ASSIGNMENTS
        if(!is_int(0) && !is_double(0)){
            if(!is_identifier(0)){
                if(!is_assignment_token(0)){
                    if(!is_expression(0))
                        return -300; // EXPRESSION ERROR
                    return -302; // ASSIGNMENT ERROR
                }
                else if(!is_semicolon(0))
                    return -303; // SEMICOLON ERROR
                return -304; // IDENTIFIER ERROR    
            }
            return -305;
        }
        // ASSIGNMENT STATEMENTS
        else if(!is_identifier(0)){
            if(!is_assignment_token(0)){
                    if(!is_expression(0))
                        return -300;
                    return -302;
                }
                else if(!is_semicolon(0))
                    return -303;
                return -304;       
        }
        // END PROGRAM
        if(!is_reserved_words_end(0))
            return -307;
        return -306; // DECLARATION GENERAL ERROR STATEMENT
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

    private boolean is_program(int min_whitespace) {
        if(!is_whitespace(min_whitespace))// MISSING WHITESPACE
            return false;
        return is_program_found();
        
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

    
    private boolean is_reserved_words_begin(int min_whitespace) {
        if(!is_whitespace(min_whitespace))// MISSING WHITESPACE
            return false;
        return is_reserved_words_begin_found();
    }
    private boolean is_reserved_words_begin_found() {
        // IF LIST SIZE IS SMALLER THAN INDEX
        if(index >= Token.list.size())
            return false;
        if(Token.list.get(index).text.equals("BEGIN")){
            index++;
            return true;
        }
        return false;
    }

    private boolean is_reserved_words_int(int min_whitespace) {
        if(!is_whitespace(min_whitespace))// MISSING WHITESPACE
            return false;
        return is_reserved_words_int_found();
    }
    private boolean is_reserved_words_int_found() {
        // IF LIST SIZE IS SMALLER THAN INDEX
        if(index >= Token.list.size())
            return false;
        System.out.println("are you here");
        if(Token.list.get(index).text.equals("int") && Token.list.get(index).type == TType.reservedWords){
            index++;
            return true;
        }
        return false;
    }

    private boolean is_reserved_words_if(int min_whitespace) {
        if(!is_whitespace(min_whitespace))// MISSING WHITESPACE
            return false;
        return is_reserved_words_if_found();
    }
    private boolean is_reserved_words_if_found() {
        // IF LIST SIZE IS SMALLER THAN INDEX
        if(index >= Token.list.size())
            return false;
  
        if(Token.list.get(index).text.equals("if") && Token.list.get(index).type == TType.reservedWords){
            index++;
            return true;
        }
        return false;
    }

    
    private boolean is_semicolon(int min_whitespace) {
        if(!is_whitespace(min_whitespace))// MISSING WHITESPACE
            return false;
        return is_reserved_words_semicolon_found();
    }
    private boolean is_reserved_words_semicolon_found() {
        // IF LIST SIZE IS SMALLER THAN INDEX
        if(index >= Token.list.size())
            return false;
        System.out.println(Token.list.get(index).text);
        if(Token.list.get(index).text.equals(";")&& Token.list.get(index).type == TType.line_terminator){
            index++;
            return true;
        }
        return false;
    }

    private boolean is_reserved_words_end(int min_whitespace) {
        if(!is_whitespace(min_whitespace))// MISSING WHITESPACE
            return false;
        return is_reserved_words_end_found();
    }
    private boolean is_reserved_words_end_found() {
        // IF LIST SIZE IS SMALLER THAN INDEX
        if(index >= Token.list.size())
            return false;
        if(Token.list.get(index).text.equals("END") && Token.list.get(index).type == TType.reservedWords){
            index++;
            return true;
        }
        return false;
    }

    private boolean is_int(int i) {
        return true;
    }
    private boolean is_double(int i) {
        return true;    
    }
    private boolean add_symbol(int i) {
        return true;    
    }
    private boolean is_expression(int i) {
        return true;
    }

    
    

    

}

   