/*
 * By:     Matthew Fischer
 * Date:   
 */
package drawshapes;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Matthew Fischer
 */
public class Window {
    private JFrame frame;
    private JPanel panel;
    
    private Ball ball;
    private Paddle paddle;
    
    public Window(){
        frame = new JFrame();
        panel = new JPanel();
        // paint panel
//        panel.paintComponents(Graphics g){
//        
        ball = new Ball();
        paddle = new Paddle();

        panel.setBackground(Color.black);
        panel.paintComponents(ball);
        
        
        
        
        frame.getContentPane().add(panel);
        frame.setTitle("padel game");
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    
}
