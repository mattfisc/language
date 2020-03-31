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
public class Window extends JPanel implements ActionListener{
    
    ArrayList<Circle> balls = new ArrayList();

    JButton addBall = new JButton("Add Ball");
    
    public Window(){
        
        
        // ADD BUTTON
        this.add(addBall, BorderLayout.NORTH);
        
        // COLOR BACKGROUND
        this.setBackground(Color.black);
        
        // MOVE BALLS
        for(Circle c : balls){
            c.move();
        }
    }
    
    
    
    
   
//    public void paintComponent(Graphics g){
//        paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
//        g2.fill(new Ellipse2D.Double(c.x,c.y,40,40) );
//    }
    
    
    public void paint(Graphics g){
        super.paint(g);
        
        for(Circle c : balls){
            g.setColor(c.color);
            g.fillOval(c.x,c.y,c.diameter,c.diameter);
        }
        

    }
    
    public static void main(String[] args) throws InterruptedException {
        Window app = new Window();
        JFrame frame = new JFrame("Moving Ball");
        
        frame.add(app);
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        while(true){
            
            app.repaint();
            Thread.sleep(10);
            
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