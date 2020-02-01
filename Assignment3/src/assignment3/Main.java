/*
 * By:     Matthew Fischer
 * Date:   
 */
package assignment3;

import java.io.File;
import java.util.Scanner;

//00	r1
//01	r2
public class Main {

    
    public static void main(String[] args) {
        Scanner x;
        
        // READ FILE
        File file = new File("opcode1.txt");
        
        // REGISTER 1
        String register1 = "";

        // REGISTER 2
        String  register2 = "";

        int rA = 0;
        int rB = 0;

        int memory1 = 0;
        int memory2 = 0;
        
        
        // File try catch
        try{
            
            x = new Scanner(file);
            
            while(x.hasNext()){
                // OPCODE
                String op = x.next();
               
                
                // END PROGRAM
                if(op.equals("09")){
                    System.out.println("terminate");
                    break;
                }
                // REGISTER 1
                register1 = x.next();
                
                
                // REGISTER 2
                register2 = x.next();
                
               
                // OPCODE
                switch(op){
                    //02 add (addressA + addressB and saved in second address)
                    case "02":
                        // REGISTER IS 000
                        if(register2.equals("000")){
                            rA = rA + rB;
                        }
                        else{
                            rB = rB + rA;
                        }
                        break;
                    //03 sub (addressA - addressB and saved in second address)
                    case "03":
                        // REGISTER IS 000
                        if(register2.equals("000")){
                            rA = rB - rA;
                        }
                        else{
                            rB = rA - rB;
                        }
                        break;
                    //04 multiply (addressA * addressB and saved in second address)
                    case "04":
                        // REGISTER IS 000
                        if(register2.equals("000")){
                            rA = rA * rB;
                        }
                        else{
                            rB = rB * rA;
                        }
                        break;
                    //05 divide (addressA / addressB and saved in second address)
                    case "05":
                        // REGISTER IS 000
                        if(register2.equals("000")){
                            rA = rB / rA;
                        }
                        else{
                            rB = rA / rB;
                        }
                        break;
                    //06 load instance (load value to address)
                    case "06":
                        // REGISTER IS 000
                        if(register1.equals("000")){
                            rA = Integer.parseInt(register2);
                        }
                        else{
                            rB = Integer.parseInt(register2);
                        }
                        break;
                    //07 load from memory (load memory to address)
                    case "07":
                        if(register1.equals("000") && register2.equals("400")){
                            rA = memory1;
                        }
                        else if(register1.equals("000") && register2.equals("500")){
                            rA = memory2;
                        }
                        else if(register1.equals("001") && register2.equals("400")){
                            rB = memory1;
                        }
                        else{
                            rB = memory2;
                        }
                        break;
                    //08 save from register to memory
                    case "08":
                        if(register1.equals("000") && register2.equals("400")){
                            memory1 = rA;
                        }
                        else if(register1.equals("001") && register2.equals("400")){
                            memory1 = rB;
                        }
                        else if(register1.equals("000") && register2.equals("500")){
                            memory2 = rA;
                        }
                        else{
                            memory2 = rB;
                        }
                        break;
                    case "09":
                        // ENDS PROGRAM
                        break;
                    //10 square number	(addressA * addressA and saved in addressA)
                    case "10":
                        if(register1.equals("000")){
                            rA = rA * rA;
                        }
                        else{
                            rB = rB * rB;
                        }
                        break;
                    // OPCODE ABOVE 10
                    default:
                        System.out.println("opcode error");
                }
                
                // DISPLAY OUTPUT
                System.out.println("opcode\tr1\tr2");
                System.out.println(op + "\t" + register1 + "\t" + register2);        
                System.out.println(op + "\t" + rA + "\t" + rB + "\n");
                
                
                
            }
            
            // ANSWER IS STORED IN SECOND REGISTER
            if(register2.equals("000")){
                System.out.println("OUTPUT " + rA);
            }
            else if(register2.equals("001")){
                System.out.println("OUTPUT " + rB);
            }
            else if(register2.equals("400")){
                System.out.println("OUTPUT " + memory1);
            }
            else{
                System.out.println("OUTPUT " + memory2);
            }
            
        }
        catch(Exception e){
            System.out.println("error in reading" + e);
        }
        
    }
   
}
