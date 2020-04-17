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
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class Window {
    public static void main(String[] args) {
        Program program = new Program();
        //program.run();
    }
}

class Program {
    private JFrame mainFrame;
    private DrawPanel drawPanel;
    private ArrayList<Circle> balls;

    private JButton button = new JButton("Add Ball");
    private int windowWidth = 500;
    private int windowHeight = 500;
    private String windowLabel = "Bounce Program";

    public Program() {

        balls = new ArrayList<Circle>();

        //Generate balls in action

        // Initialize program 
        mainFrame = new JFrame();
        drawPanel = new DrawPanel();
        drawPanel.add(button);
        drawPanel.setBackground(Color.black);
        mainFrame.getContentPane().add(drawPanel);
        mainFrame.setTitle(windowLabel);
        mainFrame.setSize(windowWidth, windowHeight);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void putValue(String key, Object value) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setEnabled(boolean b) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isEnabled() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                balls.add(new Circle());
            }
        });
        while (true) {
            for (Circle b: balls) {
                b.move();
            }

            // Give Swing 10 milliseconds to see the update! 
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mainFrame.repaint();
        }
    }

    class DrawPanel extends JPanel {
        @Override
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            for (Circle b: balls) {
                b.draw(graphics);
            }

        }
    }

    
    
}