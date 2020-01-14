/*
 * By:     Matthew Fischer
 * Date:   
 */
package assignment1;

/**
 *
 * @author Matthew Fischer
 */
public class Assignment1 {
//It's New Year's Day and everyone's in line for the Wonderland rollercoaster 
//ride! There are a number of people queued up, and each person wears a sticker 
//indicating their initial position in the queue. Initial positions increment by  
//from  at the front of the line to  at the back.
//Any person in the queue can bribe the person directly in front of them to swap 
//positions. If two people swap positions, they still wear the same sticker 
//denoting their original places in line. One person can bribe at most two 
// others. For example, if  and  bribes , the queue will look like this: .
//Fascinated by this chaotic queue, you decide you must know the minimum number 
//of bribes that took place to get the queue into its current state!
    // PROBLEM 1
    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {

        int n = 0;

        boolean chaos = false;
        int t = q.length;
        
        // ARRAY SIZE OF 1 TO 10
        if(t >= 1 && t <= 10){
            for(int i = 0; i < q.length; i++){
                if(q[i] -2 <= i){
                    n += q[i]-i;
                }
                else{
                    chaos = true;
                    break;
                }
            }
        }

        // SIZE OF N COUNT RESTRICTION
        if( n < 1 && n > Math.pow(10,5) ){
            chaos = true;
        }

        // OUTPUT
        if(chaos){
            System.out.println("Too chaotic");
        }
        else{
            System.out.println(n);
        }

    }
    public static void main(String[] args) {

    
    
    }
    
}
