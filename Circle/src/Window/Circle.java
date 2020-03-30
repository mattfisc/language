/*
 * By:     Matthew Fischer
 * Date:   
 */
package Window;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Matthew Fischer
 */
public class Circle {
    public int x;
    public int y;
    public int diameter = 20;
    
    public int xVel = 1;
    public int yVel = 1;
    
    public Color color;
    
    public Circle(){
        this.x = 100;
        this.y = 100;
        
        int R = (int)(Math.random()*256);
        int G = (int)(Math.random()*256);
        int B= (int)(Math.random()*256);
        
        Color color = new Color(R, G, B); //random color, but can be bright or dull
        this.color = color;
        
    }
    public Circle(int x, int y){
        this.x = x;
        this.y = y;
        
    }
    
    
    public void move(){
        if(this.x + this.xVel < 0){
            this.xVel = 1;
        }
        else if(this.x + this.xVel > 500){
            this.xVel = -1;
        }
        else if(this.y + this.yVel < 0){
            this.yVel = 1;
        }
        else if(this.y + this.yVel >  500){
            this.yVel = -1;
        }
        
        this.x = this.x + this.xVel;
        this.y = this.y + this.yVel;
        
        
    }
    
}
