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

    // Global variables
    Scanner x;
    
    
    public static void main(String[] args) {
        
        // READ FILE
        File rf = new File("opcode.txt");
        
        // File try catch
        if(rf.exists()){
            System.out.println("file exits");
        }
        else{
            System.out.println("not exist file");
        }
        
        
//        if(rf.exists()){
//            in = new Scanner(rf);
//        }
//        else{
//            
//        }
        
        
        // PARSE STRING
        
        
        // 
        
    }
    public void readFile(){
        
        while(x.hasNext()){
            String opcode = x.next();
            String r1 = x.next();
            String r2 = x.next();
            
            
            System.out.printf("%s %s %s\n",opcode,r1,r2);
            
        }
    }
    
}
