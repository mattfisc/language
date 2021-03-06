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
    Token identifierType = null;
    Token identifier = null;
    
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
        
        
        // ERROR CHECK
        error_output();
        
        // CODE IS VALID
        if(state == 1000){
            System.out.println("------------\nCODE IS VALID!!!!\n------------");
            // OUTPUT ALL SYMBOLS
            Symbol.printSymbols();
        }
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
            System.out.println("Error "+ state+ " : line " + (t.row+1) + " at col " + (t.col+1) );
 
            switch(state){
                case -100:
                    System.out.println(" Process program statement method");
                    break;
                case -101:
                    System.out.println("No program found");
                    break;
                case -102:
                    System.out.println("Missing program name");
                    break;
                case -103:
                    System.out.println("Missing 'BEGIN'");
                    break;
                case -104:
                    System.out.println("Missing 'end'");
                    break;
                case -106:
                    System.out.println("Missing '}' end of block");
                    break;
                case -200:
                    System.out.println("Missing 'if', 'int', 'double', or 'identifier'");
                    break;
                case -301:
                    System.out.println("Missing int or double found for type declaration");
                    break;  
                case -302:
                    System.out.println("Missing space between int and identifier for type declaration");
                    break;  
                case -303:
                    System.out.println("Missing identifier for type declaration");
                    break;  
                case -304:
                    System.out.println("Missing semicolon for type declaration assignment statement");
                    break;
                case -401:
                    System.out.println("Missing 'if' word in if statement");
                    break;  
                case -402:
                    System.out.println("Missing '(' on if statement");
                    break;  
                case -403:
                    System.out.println("Missing ')' on if statement");
                    break;
                case -404:
                    System.out.println("Missing '}' on if statement");
                    break;
                case -405:
                    System.out.println("Missing 'else' on if statement");
                    break;  
                case -450:
                    System.out.println("Missing identifier on assignment statement");
                    break;
                case -451:
                    System.out.println("Missing '=' on assignment statement");
                    break;
                case -452:
                    System.out.println("Missing '; on assignment statement");
                    break;  
                case -998:
                    System.out.println("Symbol add at Type declaration");
                    break;
                case -750:
                    System.out.println("Missing relational expression");
                    break;
                case -900:
                    System.out.println("Missing number or identifier for arithmetic expression");
                    break;  
                case -989:
                    System.out.println("Symbol not initialized ");
                    break;  
                case -990:
                    System.out.println("Symbol already exists");
                    break;
                case -999:
                    System.out.println("Variable already exists");
                    break; 
                case -995:
                    System.out.println("Symbol");
                    break; 
                case -997:
                    System.out.println("Symbol");
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
    
        while(true){
            int check = validate_statement();
            is_whitespace(0);// STRIP WHITESPACE

            if(check == -200 
                    && (TType.block == Token.list.get(index).type && Token.list.get(index).text.equals("}"))){
                return state;
                
            }
            else if(check < 0)
                return check;
            else
                return state;
        }
        
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
                return state;// IF ERROR
            
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
            index++;
            is_whitespace(0);
            if(Token.list.get(index).type == TType.block 
                    && Token.list.get(index).text.equals("{")){
                index++;
                state = find_statements_that_end_closing_brace();
                if(state < 0)
                    return state;
                
                is_whitespace(0);
                if(Token.list.get(index).type != TType.block 
                    || !Token.list.get(index).text.equals("}")){
         
                    return -404;
                }
                index++;
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
        identifierType = null;
        identifier = null;
        
        // IF TOKEN IS NOT INT/DOUBLE
        // RETURN ERROR
        is_whitespace(0);
    
        
        if(Token.list.get(index).type != TType.reservedWords 
                || !Token.list.get(index).text.equals(type))
            return -301;
        identifierType = Token.list.get(index);
        index++;
        
        // WHITE SPACE REQUIRED AFTER 'INT'
        if(!is_whitespace(1))
            return -302;
        
        //IF TOKEN IS NOT AN IDENTIFIER
        // RETURN ERROR
        if(Token.list.get(index).type != TType.identifier)
            return -303;
        
        // Symbol add if not exist
        identifier = Token.list.get(index);
        if(!Symbol.findSymbol(identifier.text))
            Symbol.list.add(new Symbol(identifierType,identifier));
        else
            return -990;
        // GO TO NEXT TOKEN
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
        //  IF SYMBOL ALREADY EXISTS
        if(!Symbol.findSymbol(Token.list.get(index).text))
            return -989;
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
           
        return -452; // END ASSIGNMENT STATEMENT
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
        // IS NUMBER OR IDENTIFIER  
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
        
        // OPTIONAL RELATIONAL EXPRESSION-----------------------------------------------
     
        
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

   