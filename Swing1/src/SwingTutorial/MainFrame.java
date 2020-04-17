package SwingTutorial;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/*
 * By:     Matthew Fischer
 * Date:   
 */

/**
 *
 * @author Matthew Fischer
 */
public class MainFrame extends JFrame{
    
    private TextPanel textPanel;
    private ToolBar toolBar;
    private FormPanel formPanel;
    
    
        public MainFrame(){
            super("hello world");
            
            setVisible(true);
            setSize(600, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocation(300, 150);
            
            textPanel = new TextPanel();
            toolBar = new ToolBar();
            formPanel = new FormPanel();
            
            setLayout(new BorderLayout());
            add(textPanel,BorderLayout.CENTER);
            add(toolBar,BorderLayout.NORTH);
            add(formPanel,BorderLayout.WEST);
            
            toolBar.setStringListener(new StringListener(){
                @Override
                public void textEmitted(String text) {
                    textPanel.append(text);
                }
            });
            
        
        }
}
