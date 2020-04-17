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
    
    public int xVel;
    public int yVel;
    
    public Color color;
    
    public Circle(){
        // random size
        this.x = (int) Math.floor((Math.random()  *200)-50);
        this.y = (int) Math.floor((Math.random()  *400)-50);
        
        // DIAMETER
        this.diameter = (int)Math.floor((Math.random()  *25)+10);
        // color random
        int R = (int)(Math.random()*256);
        int G = (int)(Math.random()*256);
        int B= (int)(Math.random()*256);
        
        Color color = new Color(R, G, B); //random color, but can be bright or dull
        this.color = color;
        
        // velocity 1-5
        this.xVel = (int) Math.floor((Math.random() * 1) +3);
        this.yVel = (int) Math.floor((Math.random() * 1) +3);
    }
    public Circle(int x, int y){
        this.x = x;
        this.y = y;
        this.xVel = (int) Math.floor((Math.random() * 10) - 5);
        this.yVel = (int) Math.floor((Math.random() * 10) - 5);
        
    }
    
    
    public void move(){
        if (x > 400 || x < 0) {
                xVel *= -1;
            }
            if (y > 400 || y < 0) {
                yVel *= -1;
            }
            if (x > 400) {
                x = 400;
            }
            if (x < 0) {
                x = 0;
            }
            if (y > 400) {
                y = 400;
            }
            if (y < 0) {
                y = 0;
            }

            this.x += xVel;
            this.y += yVel;
        
    }
    public void draw(Graphics g) {
            g.setColor(color);
            g.fillOval(x, y, diameter, diameter);
        }
    
}
