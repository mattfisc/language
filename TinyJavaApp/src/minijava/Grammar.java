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
        System.out.println("---------------\nPARSING GRAMMAR\n---------------");
        while(index < Token.list.size() && state >= 0 && state < 1000){
            
            switch(state){
                case 0:
                    state = valid_program_statement();
                    break;
                // VALIDATE BEGIN STATEMENT
                case 1:
                    state = validate_begin_statement();
                    break;
                // VALIDATE STATEMENTS, END
                case 2:
                    state = find_statements_that_end_with_end();
                    break;
                // VALIDATE END STATEMENT
                case 3:
                    state = validate_end_statement();
                    break;
                default:
                    state = -100;
            }// END SWITCH
            
           
            
        }// END OF WHILE
        
        error_output();

    }

    public void error_output(){
        // ERROR PROCESSING
        Token t = Token.list.get(index);
        if(state < 0){
            String str = "";
            for(int i = 0; i < (t.col); i++){
                str += " ";
            }
            
            System.out.println(str + "^");
            System.out.print("Error : line " + (t.row+1) + " at col " + (t.col+1) + ", " + state+ " ");
 
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
    //            case -300:
    //                System.out.println("Error expression");
    //                break;  
    //            case -302:
    //                System.out.println("Error assignment token");
    //                break;  
    //            case -303:
    //                System.out.println("Error semicolon error");
    //                break;  
    //            case -304:
    //                System.out.println("Error identifier");
    //                break;
    //            case -305:
    //                System.out.println("Error int or double reserved word");
    //                break;
    //            case -306:
    //                System.out.println("Error illegal start declaration statement");
    //                break;  
    //            case -307:
    //                System.out.println("Error no END reserved word");
    //                break; 


                default: 

            }// END OF SWITCH
        }
    }

    // FIRST LINE OF PROGRAM
    private int valid_program_statement() {
        
            //HAS TO BE IN A ROW
            
        if(!is_program(0))
            return -101;// TOKEN PROGRAM
        if(!is_identifier(1))
            return -102;// TOKEN IDENTIFIER
        return 1;
    }
    
    
    private int validate_begin_statement() {
        
        if(!is_reserved_words_begin(1))
            return -103;
        return 2;
    }

    private int validate_end_statement() {
        if(!is_reserved_words_end(0))
            return -104;
        return 1000;
    }
//    private int validate_statements(){
//        Token t = Token.list.get(index);
//        
//        while((!t.text.equals("END") || t.type != TType.reservedWords) 
//                && (!t.text.equals("}") || t.type != TType.block)){
//            state = validate_statement();
//            if(state < 0)
//                break;
//            t = Token.list.get(++index);
//        }
//
//        return state;
//    }
    private int validate_statement() {
        is_whitespace(0);// STRIP WHITESPACE
        
        Token t = Token.list.get(index);
        
        // RESERVED WORDS INT DOUBLE IF IDENTIFIER
        
        // IF TOKEN IS IF
        // RETURN validate_if_statement();
        if(t.type == TType.reservedWords && t.text.equals("if"))
            return validate_if_statement();
        // IF TOKEN IS INT 
        // RETURN VALIDATE INT STATEMENT
        if(t.type == TType.reservedWords && t.text.equals("int"))
            return validate_int_statement();
        // IF TOKEN IS DOUBLE 
        // RETURN VALIDATE DOUBLE STATEMENT
        if(t.type == TType.reservedWords && t.text.equals("double"))
            return validate_double_statement();
        // IF TOKEN IS IDENTIFIER
        // RETURN VALIDATE ASSIGNMENT STATEMENT
        if(t.type == TType.identifier )
            return validate_assignment_statement();
                
        is_whitespace(0);// STRIP WHITESPACE
        
        return -200;
    }
    public int find_statements_that_end_with_end(){
        while(index < Token.list.size()){
            is_whitespace(0);// STRIP WHITESPACE
            if(TType.reservedWords == Token.list.get(index).type && Token.list.get(index).text.equals("END"))
                return 3;
            state = validate_statement();
        }
        return state;
    }
    public int find_statements_that_end_closing_brace(){
        if(index >= Token.list.size())
            return -106;
        while(TType.block != Token.list.get(index).type || !Token.list.get(index).text.equals("}")){
            state = validate_statement();
        }
        return state;
    }

    
    // IS WHITESPACE
    private boolean is_whitespace(int min_whitespace) {
        if(index >= Token.list.size())
            return false;
        
        
        // IF THERE IS A WHITESPACE        
        if(min_whitespace > 0){
            if(Token.list.get(index).type == TType.line){
                    System.out.println(Token.list.get(index).text);
                    index++;
            }
            if(Token.list.get(index).type != TType.white_space){
                return false;
            }
        }
        
        
        // EATS ALL WHITESPACE
        while(index < Token.list.size()){
            if(Token.list.get(index).type == TType.line){
                    System.out.println(Token.list.get(index).text);
                    index++;
            }
            if(Token.list.get(index).type == TType.white_space){
                index++;
            }
            else
                break;
            
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
        if(Token.list.get(index).text.equals("BEGIN") 
                && Token.list.get(index).type  == TType.reservedWords){
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
        if(Token.list.get(index).text.equals("int")){
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
  
        if(Token.list.get(index).text.equals("if")){
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
        if(Token.list.get(index).text.equals("END")){
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

    private int validate_if_statement() {
        // REMOVE WHITESPACE
        is_whitespace(0);
        
        // IF TOKEN IS NOT "IF"
        // RETURN ERROR
        if(Token.list.get(index).type != TType.reservedWords 
                || !Token.list.get(index).text.equals("if"))
            return -401;
        is_whitespace(0);
        //IF TOKEN IS NOT (
        // RETURN ERROR
        if(Token.list.get(index).type != TType.parenthesis 
                || !Token.list.get(index).text.equals("("))
            return -402;
        is_whitespace(0);
        
        // RELATIONAL EXPRESSION METHOD
        state = relational_expression();
        if(state < 0)
            return state;
        is_whitespace(0);
        
        // IF TOKEN IS NOT )
        // RETURN ERROR
        if(Token.list.get(index).type != TType.parenthesis 
                || !Token.list.get(index).text.equals(")"))
            return -403;
        
        
        // IF TOKEN IS {
        // VALIDATE STATEMENTS
        // IF TOKEN IS NOT }
        // RETURN ERROR
        is_whitespace(0);
        if(Token.list.get(index).type == TType.block 
                && Token.list.get(index).text.equals("{")){
            state = find_statements_that_end_closing_brace();
            if(state < 0)
                return state;
            
            is_whitespace(0);
            if(Token.list.get(index).type != TType.block 
                || !Token.list.get(index).text.equals("}"))
                return -404;
        }
        // MUST BE SINGLE STATEMENT
        else{
            is_whitespace(0);
            state = validate_statement();
                if(state < 0)
                    return state;
        }
        
        
        // OPTION ELSE TOKEN
        // IF TOKEN IS {
        // VALIDATE STATEMENTS
        // IF TOKEN IS NOT }
        // RETURN ERROR
        is_whitespace(0);
        if(Token.list.get(index).type == TType.reservedWords 
                && Token.list.get(index).text.equals("else")){
            if(Token.list.get(index).type == TType.block 
                    && Token.list.get(index).text.equals("{")){
                state = find_statements_that_end_closing_brace();
                if(state < 0)
                    return state;
                
                is_whitespace(0);
                if(Token.list.get(index).type != TType.block 
                    || !Token.list.get(index).text.equals("}"))
                    return -404;
            }
            // MUST BE SINGLE STATEMENT
            else{
                is_whitespace(0);
                state = validate_statement();
                    if(state < 0)
                        return state;
            }
        }

        return state;
    }
    
    public int validate_int_statement(){
        return validate_type_statement("int"); 
    }
    public int validate_double_statement(){
        return validate_type_statement("double"); 
    }
    private int validate_type_statement(String type) {
        
        is_whitespace(0);
        // IF TOKEN IS NOT INT/DOUBLE
        // RETURN ERROR
        if(Token.list.get(index).type != TType.reservedWords 
                || !Token.list.get(index).text.equals(type))
            return -301;
        index++;
        if(!is_whitespace(1))
            return -305;
        
        //IF TOKEN IS NOT AN IDENTIFIER
        // RETURN ERROR
        if(Token.list.get(index).type != TType.identifier)
            return -302;
        index++;
        is_whitespace(0);
        
        //IF TOKEN IS ;
        // INCREMENT INDEX AND RETURN VALID
        if(Token.list.get(index).type == TType.line_terminator 
                && Token.list.get(index).text.equals(";")){
            index++;
            return state;
        }
            
        is_whitespace(0);
        // IF TOKEN IS =
        // STATE = ARTHIMETIC EXPRESSION METHOD
        if(Token.list.get(index).type == TType.assignment_token 
                && Token.list.get(index).text.equals("=")){
            index++;
            state = arthimetic_expression();// NEEDS WHITE SPACE
        }
        
        
        is_whitespace(0);
        
        //IF TOKEN IS ;
        // INCREMENT INDEX AND RETURN VALID
        if(Token.list.get(index).type == TType.line_terminator 
                && Token.list.get(index).text.equals(";")){
            index++;
            return state;
        }
           
        return -300; // DEFAULT ERROR INT STATEMENT
    }

    private int validate_assignment_statement() {
        is_whitespace(0);
        //IF TOKEN IS NOT AN IDENTIFIER
        // RETURN ERROR
        if(Token.list.get(index).type != TType.identifier)
            return -301;
        index++;
        is_whitespace(0);
        

        // IF TOKEN IS =
        // STATE = ARTHIMETIC EXPRESSION METHOD
        if(Token.list.get(index).type == TType.assignment_token 
                && Token.list.get(index).text.equals("=")){
            index++;
            state = arthimetic_expression();
        }
        
        
        is_whitespace(0);
        
        //IF TOKEN IS ;
        // INCREMENT INDEX AND RETURN VALID
        if(Token.list.get(index).type == TType.line_terminator 
                && Token.list.get(index).text.equals(";")){
            index++;
            return state;
        }
           
        return -400; // DEFAULT ERROR INT STATEMENT
    }

    private int arthimetic_expression() {
        if(Token.list.get(index).type != TType.number)
            state = -600;
        index++;
        return state;
    }

    private int relational_expression() {
        if(Token.list.get(index).type != TType.number)
            state = -600;
        index++;
        return state;
    }

    
    

    

}

   