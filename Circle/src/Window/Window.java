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
/**
 *
 * @author Matthew Fischer
 */
public class Window extends JPanel implements ActionListener {
    int index = 0;
    Circle[] c;

    JButton addBall = new JButton("Add Ball");
    
    public Window(){
        
        c = new Circle[10];
        
        this.add(addBall, BorderLayout.NORTH);
        addBall.addActionListener(this);
        this.setBackground(Color.black);
        
    }
    
    
    
    public void move(){
        if(c.x + c.xVel < 0){
            c.xVel = 1;
        }
        else if(c.x + c.xVel > getWidth() - 50){
            c.xVel = -1;
        }
        else if(c.y + c.yVel < 0){
            c.yVel = 1;
        }
        else if(c.y + c.yVel > getHeight() - 50){
            c.yVel = -1;
        }
        
        c.x = c.x + c.xVel;
        c.y = c.y + c.yVel;
        
        
    }
    
   
//    public void paintComponent(Graphics g){
//        paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
//        g2.fill(new Ellipse2D.Double(c.x,c.y,40,40) );
//    }
    
    
    public void paint(Graphics g){
        super.paint(g);
        
        
        g.setColor(c.color);
        g.fillOval(c.x,c.y,c.diameter,c.diameter);
        

    }
    
    public static void main(String[] args) throws InterruptedException {
        Window app = new Window();
        JFrame frame = new JFrame("Moving Ball");
        frame.add(app);
        
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        while(true){
            app.move();
            app.repaint();
            Thread.sleep(10);
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        e.getSource() = new Circle();
    }

    
}