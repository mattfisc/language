/*
 * By:     Matthew Fischer
 * Date:   
 */
package minijava;

/**
 *WHITE SPACE 213
 * @author Matthew Fischer
 */
public class Grammar {
    int depth = 0;
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
                    System.out.println(" Process program statement method");
                    break;
                case -101:
                    System.out.println("No program found");
                    break;
                case -102:
                    System.out.println("No program name found");
                    break;
                case -103:
                    System.out.println("Reserved word begin");
                    break;
                case -104:
                    System.out.println("Reserved word end");
                    break;
                case -106:
                    System.out.println("End '}' for statment end");
                    break;
                case -200:
                    System.out.println("statement start no if, int, double");
                    break;
                case -301:
                    System.out.println("no int or double found for type declaration");
                    break;  
                case -302:
                    System.out.println("no space between int and identifier for type declaration");
                    break;  
                case -303:
                    System.out.println("missing identifier for type declaration");
                    break;  
                case -304:
                    System.out.println("missing semicolon for type declaration assignment statement");
                    break;
                case -401:
                    System.out.println("missing 'if' word in if statement");
                    break;  
                case -402:
                    System.out.println("missing '(' found  in if statement");
                    break;  
                case -403:
                    System.out.println("no ')' found  in if statement");
                    break;
                case -404:
                    System.out.println("no '}' found  in if statement");
                    break;
                case -405:
                    System.out.println("no 'else' found in if statement");
                    break;  
                case -450:
                    System.out.println("no identifier on assignment statement");
                    break;
                case -451:
                    System.out.println("no '=' found on assignment statement");
                    break;
                case -452:
                    System.out.println("no '; found on assignment statement");
                    break;  


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

    private int validate_statement() {
        is_whitespace(0);// STRIP WHITESPACE
        Token t = Token.list.get(index);

        // RESERVED WORDS INT, DOUBLE, IF, IDENTIFIER

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
        while(index < Token.list.size() && state > 0){// i add this state ------------------------
            is_whitespace(0);// STRIP WHITESPACE
            if(TType.reservedWords == Token.list.get(index).type && Token.list.get(index).text.equals("END"))
                return 3;
            return validate_statement();// ---------------------------------i did this --------------------------------  
        }
        return state;
    }
    public int find_statements_that_end_closing_brace(){
        if(index >= Token.list.size())
            return -106;
        is_whitespace(0);// STRIP WHITESPACE
        while(TType.block != Token.list.get(index).type || !Token.list.get(index).text.equals("}")){
            state = validate_statement();
        }
        return state;
    }

    
    // IS WHITESPACE
    private boolean is_whitespace(int min_whitespace) {
        // END OF TOKEN LIST
        if(index >= Token.list.size() || state < 0)
            return false;
        
 
        
        // IF THERE IS A WHITESPACE OR LINE       
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
//        if(!is_whitespace(min_whitespace))// MISSING WHITESPACE
//            return false;
        is_whitespace(0);
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

    private int validate_if_statement() {
        // REMOVE WHITESPACE
        // IF TOKEN IS NOT "IF"
        // RETURN ERROR
        
        is_whitespace(0);
        
        if(Token.list.get(index).type != TType.reservedWords 
                || !Token.list.get(index).text.equals("if"))
            return -401;
        index++;
        
        //IF TOKEN IS NOT (
        // RETURN ERROR
        is_whitespace(0);
        if(Token.list.get(index).type != TType.parenthesis 
                || !Token.list.get(index).text.equals("("))
            return -402;
        index++;
        
        // RELATIONAL EXPRESSION METHOD
        is_whitespace(0);
        state = relational_expression();
        if(state < 0)
            return state;
        
        
        // IF TOKEN IS NOT )
        // RETURN ERROR
        is_whitespace(0);
        if(Token.list.get(index).type != TType.parenthesis 
                || !Token.list.get(index).text.equals(")"))
            return -403;
        index++;
        
        // IF TOKEN IS {
        // VALIDATE STATEMENTS
        // IF TOKEN IS NOT }
        // RETURN ERROR
        is_whitespace(0);
        if(Token.list.get(index).type == TType.block 
                && Token.list.get(index).text.equals("{")){
            index++;
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
        index++;
        
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

        // IF TOKEN IS NOT INT/DOUBLE
        // RETURN ERROR
        is_whitespace(0);
        if(Token.list.get(index).type != TType.reservedWords 
                || !Token.list.get(index).text.equals(type))
            return -301;
        index++;
        
        // WHITE SPACE REQUIRED AFTER 'INT'
        if(!is_whitespace(1))
            return -302;
        
        //IF TOKEN IS NOT AN IDENTIFIER
        // RETURN ERROR
        
        if(Token.list.get(index).type != TType.identifier)
            return -303;
        index++;
        
        
        //IF TOKEN IS ;
        // INCREMENT INDEX AND RETURN VALID
        is_whitespace(0);
        if(Token.list.get(index).type == TType.line_terminator 
                && Token.list.get(index).text.equals(";")){
            index++;
            return state;
        }
        
        // IF TOKEN IS =
        // STATE = ARTHIMETIC EXPRESSION METHOD
        is_whitespace(0);
        if(Token.list.get(index).type == TType.assignment_token 
                && Token.list.get(index).text.equals("=")){
            index++;
            state = arthimetic_expression();// NEEDS WHITE SPACE
        }
        
        
        
        
        //IF TOKEN IS ;
        // INCREMENT INDEX AND RETURN VALID
        is_whitespace(0);
        if(Token.list.get(index).type == TType.line_terminator 
                && Token.list.get(index).text.equals(";")){
            index++;
            return state;
        }
           
        return -304; // SEMI COLON ERROR
    }

    private int validate_assignment_statement() {
        
        //IF TOKEN IS NOT AN IDENTIFIER
        // RETURN ERROR
        is_whitespace(0);
        if(Token.list.get(index).type != TType.identifier)
            return -450;
        index++;
        
        

        // IF TOKEN IS =
        // STATE = ARTHIMETIC EXPRESSION METHOD
        is_whitespace(0);
        if(Token.list.get(index).type == TType.assignment_token 
                && Token.list.get(index).text.equals("=")){
            index++;
            state = arthimetic_expression();
        }
        else
            return -451;
        
        
        
        
        //IF TOKEN IS ;
        // INCREMENT INDEX AND RETURN VALID
        is_whitespace(0);
        if(Token.list.get(index).type == TType.line_terminator 
                && Token.list.get(index).text.equals(";")){
            index++;
            return find_statements_that_end_with_end();
        }
           
        return -453; // END ASSIGNMENT STATEMENT
    }


    private int relational_expression() {
        //X == U
        // 3 == 3
        // 3 ==3 && X!=Y || 4 == Z || 4 > 3
        // (3==3)
        // (3 ==3 && ((X!=Y) || 3 == Z) || 4 ==F)
        if(!is_relational())
            return -750;
        return state;
        
    }
    public boolean is_num_or_identifier(){
        is_whitespace(0);
        
        return (Token.list.get(index).type == TType.number 
                || Token.list.get(index).type == TType.identifier);
   
    }
    public boolean is_relational(){
        // IS NUMBER OR IDENTIFIER  (x==u)
        is_whitespace(0);
        
        // TERMINATION
        // WHEN RIGHT PAREN, INCREMENT, RETURN TRUE
        if(Token.list.get(index).type == TType.parenthesis && Token.list.get(index).text.equals(")")){
            if(depth <= 0)
                return true;
            
            index++;
            depth--;
            return true;
            
        }
        is_whitespace(0);
        // if LEFT PAREN, increment, return is_relational()
        if(Token.list.get(index).type == TType.parenthesis && Token.list.get(index).text.equals("(")){
            depth++;
            index++;
            return is_relational();
        }
        
        // IF NUMBER, AND RELATIONAL OPERATOR
        if(!is_num_or_identifier())
            return false;
        index++;
        
        is_whitespace(0);
        if(Token.list.get(index).type != TType.relational_operator)
            return false;
        index++;

        // if NUMBER OR IDENTIFIER
        if(!is_num_or_identifier())
            return false;
        index++;
        
        // IF LOGICAL
        // IF LEFT PAREN THEN INCREMENT AND RECALL
        // IF RELATIONAL EXPRESSION
        return is_optional_logic_with_relational_expression();

    }
    
    public boolean is_optional_logic_with_relational_expression(){
        
        // OPTIONAL RECURRSION
        is_whitespace(0);
        if(Token.list.get(index).type == TType.logical_operator)
            index++;
       

        // ALL IS VALID AND DONE
        return is_relational();
    }

    private int arthimetic_expression() {
        // 1+1*7/5*3-9;
        // (x+3)
        // ((3+0))
        //  X+3;
        // (3+6*(x+3)+3)
        // 3*33.6 / X;
        // 3 
        // x
        
        // (,NUMBER,IDENTIFIER
        // )
        // IS NUMBER OR IDENTIFIER
        is_whitespace(0);
       
        // TERMINATION
        // WHEN RIGHT PAREN, INCREMENT, RETURN TRUE
        if(Token.list.get(index).type == TType.line_terminator)
            return state;
        if(Token.list.get(index).type == TType.parenthesis && Token.list.get(index).text.equals(")")){
            index++;
            
            if(depth > 0)
                depth--;
               

            is_whitespace(0);
            if(Token.list.get(index).type == TType.arithmetic_operator ){
                return optional_arithmetic_expression();
            }
            return (depth <=0)?state:arthimetic_expression();
        }
        
        is_whitespace(0);
        // if LEFT PAREN, increment, return is_relational()
        if(Token.list.get(index).type == TType.parenthesis && Token.list.get(index).text.equals("(")){
            depth++;
            index++;
            return arthimetic_expression();
        }
        
        // IF NUMBER 
        if(!is_num_or_identifier())
            return -900;
        index++;
        
        
        return optional_arithmetic_expression();
    }
    
    public int optional_arithmetic_expression(){
        
        is_whitespace(0);
        if(Token.list.get(index).type == TType.arithmetic_operator)
            index++;
        return arthimetic_expression();
    }
    
}

   