/*
 * By:     Matthew Fischer
 * Date:   
 */
package ass3lauren;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Matthew Fischer
 */

public class Ass3Lauren {
  
    
  public static void main(String[] args) {
      Ass3Lauren m = new Ass3Lauren();
//read separate files of the opcode used in assignment two
      //output of this machine code should be 10
      File file1 = new File("opcode1.txt");
      m.opcode(file1);
      //output of this machine code should be -1
      File file2 = new File("opcode2.txt");
      m.opcode(file2);
      //output of this machine code should be 1
      File file3 = new File("opcode3.txt");
      m.opcode(file3);
  }
 
  public void opcode(File file){
      Scanner in;
 
//Variables for the memory locations/operands
 //String values
      String locationOne = "";
      String locationTwo = "";
 //int values
      int valueL1 = 0;
      int valueL2 = 0;
      int placeHolder = 0;
//Array to hold variables that doesnt take up too much memory
      HashMap<String, Integer> valueHolder = new HashMap<String, Integer>();
 
//Try-Catch
      try{
         
          in = new Scanner(file);
         
          while(in.hasNext()){
//First variable will be the OPCODE
              String op = in.next();
             
             
//indicate the program has stopped running
              if(op.equals("00")){
                  System.out.println("\t" + "\t" +"Program Finished." + "\n");
                  break;
              }
//values in each memory location
              locationOne = in.next();          
              locationTwo = in.next();
             
             
//use OPCODE to facilitate a switch statement
              switch(op){
//02 add
                  case "02":
                	  valueL1 = valueHolder.get(locationOne);
                	  valueL2 = valueHolder.get(locationTwo);
                	  valueL1 += valueL2;
                	  break;
//03 sub
                  case "03":
                	  valueL1 = valueHolder.get(locationOne);
                	  valueL2 = valueHolder.get(locationTwo);
                	  valueL1 -= valueL2;
                      break;
//04 multiply
                  case "04":
                	  valueL1 = valueHolder.get(locationOne);
                	  valueL2 = valueHolder.get(locationTwo);
                	  valueL1 *= valueL2;
                      break;
//05 divide
                  case "05":
                	  valueL1 = valueHolder.get(locationOne);
                	  valueL2 = valueHolder.get(locationTwo);
                	  valueL1 /= valueL2;
                      break;
//06 exponent
                  case "06":
                	  valueL1 = valueHolder.get(locationOne);
                	  valueL2 = valueHolder.get(locationTwo);
                	  valueL1 = (int) Math.pow(valueL1, valueL2);
                      break;
//07 squareroot
                  case "07":
                	  valueL1 = valueHolder.get(locationOne);
                	  valueL2 = valueHolder.get(locationTwo);
                	  valueL1 = (int) Math.round(Math.pow(valueL1, 1.0 / valueL2));
                      break;
//08 load value data into a string variable
                  case "08":
                      valueL1 = Integer.parseInt(locationTwo);
                      valueHolder.put(locationOne, valueL1);
                      break;
//09 Store new value from computations into a new string variable
                  case "09":
                	  valueL2 = valueL1;
                	  placeHolder = valueL2;
                	  valueL1 = placeHolder;
                	  
                	  valueHolder.put(locationOne, valueL1);
                      break;
//display answer
                  case "10":
                   System.out.println("This is the answer to the machine code" + " " + valueL1);
                      break;

//if OPCODE isn't reading the correct assigned values
                  default:
                      System.out.println("Read Opcode Wrong :( ");
              }
//Additional output to see how each line of the machine code performs
              if(op.equals("10")) {
            	  System.out.println( "\t" + "Yay you got to the end!");
              }else {
              System.out.println("\t" +"opcode\tString then Value in 1\tString then value in 2");
              System.out.println("\t" +op + "\t" + locationOne + "\t" + "\t" + "\t" + locationTwo);        
              System.out.println("\t" +op + "\t" + valueL1+ "\t" + "\t" + "\t" +valueL2 + "\n");  
              }
          }
         
      }
      catch(Exception e){
          System.out.println("Couldn't read the FILE :( " + e);
      }
  }
 
}