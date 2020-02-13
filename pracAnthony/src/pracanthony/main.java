/*
 * By:     Matthew Fischer
 * Date:   
 */
package pracanthony;


import java.io.File;

import java.util.Scanner;

/**
 *
 * @author Matthew Fischer
 */
public class main {

    
    

public static void main (String mach) {
    File t = new File("C:\\\\Users\\\\antho\\\\Desktop\\\\programs.txt"); //access txt file
    // almost the same as the scanner
    //BufferedReader br; //create a reader in which the txt can be read from 

    Scanner input = new Scanner(System.in);// for input from keyboard
    
    
    String opCode = ""; // opcode
    String firstReg =""; //create first string memory register 
    String secondReg =""; //create second string memory register 

    try{ 
        Scanner fileReader = new Scanner(t);// read file through scanner
        
        while(fileReader.next() != null){
            opCode = fileReader.next();
            firstReg = fileReader.next();
            secondReg = fileReader.next();
            
            
            
            
            // logic here
//            String sign = "";
//            switch (mach) {
//                case "00": break; //serves as the halt function 
//                case "02": sign = "+"; break;//take data store in c and multiple it by data of d
//                case "03": sign = "-"; break;  // then take the new data stored d and add to data of a
//                case "04": sign = "*"; break; //now, take the data stored in a and take sqrt
//                case "05": sign = "/"; break;
//                case "06": sign = "load"; break; // load prestored data types like a,b,c,d
//                case "09": 
//                    System.out.println("input a number between 0 and 999");
//                    fileReader.nextInt(); 
//                    break;//take input from keyboard
//                case "10": String temp= "X"; //preassign variable for a function
//
//
//            }
        }
    }

    catch (Exception e) {
        e.printStackTrace(); // catch any potential errors that may prevent the program to run 
    }


    
    
    }

}
