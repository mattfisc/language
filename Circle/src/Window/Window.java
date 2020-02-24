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
public class Window extends JFrame  {

    Container content = this.getContentPane();
    
    JPanel panel = new JPanel();
    
    Timer t = new Timer(5,this);
    
    Circle c = new Circle();
    
    public Window(){
        this.setVisible(true);
        this.setSize(500, 500);
        this.setTitle("Circle");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       
        content.add(panel,BorderLayout.CENTER);
  
        t.start();
        //addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
    }
    
//    public void paintComponent(Graphics g){
//        paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
//        g2.fill(new Ellipse2D.Double(c.x,c.y,40,40) );
//    }
    
    
    public void paint(Graphics g){
        g.setColor(Color.red);
        g.drawOval(c.x,c.y,10,10);
        

    }
    
    public static void main(String[] args) {
        Window w = new Window();
        w.paint(null);
    }

    
}