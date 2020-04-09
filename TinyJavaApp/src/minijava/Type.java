/*
 * By:     Matthew Fischer
 * Date:   
 */
package minijava;

/**
 *
 * @author Matthew Fischer
 */
enum TType {
    
//    type,
    number,
//    letter,
//    
//    digit,
//    integerN,
//    doubleN,
//    
//    arithmetic_operator,
//    relational_operator,
//    logical_operator,
//    
//    symbol,
//    underline,
//    line_terminator,
//    assignment_token,
//    
//    first_char,
//    other_char,
//    
    identifier
//    
//    type_declaration,
//    type_declaration_itialized,
//    type_declaration_statement,
//    
//    expression,
//    term,
//    
//    relational_expression,
//   
//    assignment_statement,
//    if_statement,
//    block,
//    if_one_line,
//    
//    program
    
    
}


public class Type {
    
    TType ttype;
    
    public Type(TType ttype){
        this.ttype = ttype;
    }
}