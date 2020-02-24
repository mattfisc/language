/*
 * By:     Matthew Fischer
 * Date:   
 */
package nibbles;

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
public class Window extends JFrame implements ActionListener,KeyListener {

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
        addKeyListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        c.x += c.xVel;
        c.y += c.yVel;
    }
    
    public void up(){
        c.yVel = -1;
        c.xVel = 0;
    }
    
    public void down(){
        c.yVel = 1;
        c.xVel = 0;
    }
    
    public void left(){
        c.yVel = 0;
        c.xVel = -1;
    }
    
    public void right(){
        c.yVel = 0;
        c.xVel = 1;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_UP){
            up();
        }
        else if(code == KeyEvent.VK_DOWN){
            down();
        }
        else if(code == KeyEvent.VK_LEFT){
            left();
        }
        else if(code == KeyEvent.VK_RIGHT){
            right();
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
