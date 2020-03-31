/*
 * By:     Matthew Fischer
 * Date:   
 */
package Window;

import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
/**
 *
 * @author Matthew Fischer
 */
public class Window extends JFrame implements ActionListener{
    
    ArrayList<Circle> balls = new ArrayList();

    JButton addBall = new JButton("Add Ball");
    JFrame frame;
    JPanel drawPanel;
    
    public Window(){
        
        frame = new JFrame("Moving Ball");
        
        drawPanel = new JPanel();
        frame.add(drawPanel);
        frame.setVisible(true);
        frame.setSize(500, 500);
        // ADD BUTTON
        frame.add(addBall, BorderLayout.NORTH);
        
        // COLOR BACKGROUND
        frame.setBackground(Color.black);
        
        addBall.addActionListener(this);
        
        // MOVE BALLS
        for(Circle c : balls){
            
            c.move();
        }
    }

    public void paintComponent(Graphics graphics) {
        paintComponent(graphics);

        for (Circle b: balls) {
            paint(graphics);
        }

    }
    
    
    public void paint(Graphics g){
        paint(g);
        
        for(Circle c : balls){
            g.setColor(c.color);
            g.fillOval(c.x,c.y,c.diameter,c.diameter);
        }
        

    }
    
    public static void main(String[] args) {
        Window app = new Window();
     
        app.run();
    }
    
    public void run(){
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        while(true){
            
            try{
                
                Thread.sleep(10);
            
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            frame.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // NEW CIRCLE
        Circle c = new Circle();
        // ADD CIRCLE
        balls.add(c);
    }

   
    
}