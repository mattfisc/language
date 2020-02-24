/*
 * By:     Matthew Fischer
 * Date:   
 */
package Window;

import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Matthew Fischer
 */
public class Window extends JFrame{

    Container content = this.getContentPane();
    
    JPanel panel = new JPanel();
    
    public Window(){
        this.setVisible(true);
        this.setSize(500, 500);
        this.setTitle("Circle");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel.setBackground(Color.black);
        content.add(panel,BorderLayout.CENTER);
        
        
    }
    public static void main(String[] args) {
        Window c = new Window();
    }
    
}
