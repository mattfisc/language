/*
 * By:     Matthew Fischer
 * Date:   
 */
package SwingTutorial;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.*;

/**
 *
 * @author Matthew Fischer
 */
public class ToolBar extends JPanel implements ActionListener{
    
    private JButton helloBtn;
    private JButton goodbyeBtn;
    private StringListener textListener;
    
    public ToolBar(){
        helloBtn = new JButton("Hello Button");
        goodbyeBtn = new JButton("GoodBye Button");
        
        setLayout(new FlowLayout());
        add(helloBtn);
        add(goodbyeBtn);
        
        helloBtn.addActionListener(this);
        goodbyeBtn.addActionListener(this);
        
        setBorder(BorderFactory.createEtchedBorder());
        
    }
    public void setStringListener(StringListener listener){
        this.textListener = listener;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();
        if(clicked == helloBtn){
            if(textListener != null)
                textListener.textEmitted("Hello\n");
        }
        else
            if(textListener != null)
                textListener.textEmitted("Goodbye\n");
        
    }
    
    
}
