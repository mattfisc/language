/*
 * By:     Matthew Fischer
 * Date:   
 */
package assignment3;

import java.io.File;
import java.util.Scanner;

//00	r1
//01	r2
//02	add		(addressA + addressB and saved in second address)
//03	sub		(addressA - addressB and saved in second address)
//04	multiply	(addressA * addressB and saved in second address)
//05	divide		(addressA / addressB and saved in second address)
//06	load instance (load value to address)
//07	load from memory (load memory to address)
//08	save from register to memory
//09	quit program
//10	square number	(addressA * addressA and saved in addressA)

public class Main {

    
    public static void main(String[] args) {
        Scanner x;
        // READ FILE
        File file = new File("opcode.txt");
        
        // File try catch
        try{
            
            x = new Scanner(file);
            
            while(x.hasNext()){
                // OPCODE
                String op = x.next();
                char sign = ' ';
                
                // REGISTER 1
                String r1String = x.next();
                int rA = Integer.parseInt(r1String);
                
                // REGISTER 2
                String r2String = x.next();
                int rB = Integer.parseInt(r2String);
                
                int temp = 0;
                
                // OPCODE
                switch(op){
                    case "02":
                        sign = '+';
                        break;
                    case "03":
                        sign = '-';
                        break;
                    case "04":
                        sign = '*';
                        break;
                    case "05":
                        sign = '/';
                        break;
                    default:
                        System.out.println("sign error");
                }       
                
                switch(op){
                    case "06":
                        rB = arithmetic(sign,rA,rB);
                        break;
                    case "07":
                        break;
                    case "08":
                        break;
                    case "09":
                        break;
                    case "10":
                        break;
                }
            }
            
        }
        catch(Exception e){
            System.out.println("error in reading" + e);
        }
        
    }
   
    public int arithmetic(sign,rA,rB){
        
        return rB;
    }
}
