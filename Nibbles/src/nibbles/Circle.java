/*
 * By:     Matthew Fischer
 * Date:   
 */
package nibbles;

import java.awt.Graphics;

/**
 *
 * @author Matthew Fischer
 */
public class Circle {
    public int x;
    public int y;
    
    public double xVel = 0;
    public double yVel = 0;
    
    
    public Circle(){
        this.x = 20;
        this.y = 20;
        
    }
    public Circle(int x, int y){
        this.x = x;
        this.y = y;
        
    }
    
}
