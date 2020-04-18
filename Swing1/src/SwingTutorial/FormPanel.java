/*
 * By:     Matthew Fischer
 * Date:   
 */
package SwingTutorial;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author Matthew Fischer
 */
public class FormPanel extends JPanel{
    
    private JLabel nameLabel;
    private JLabel occupationLabel;
    
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;
    
    public FormPanel(){
        
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        
        // intialize
        nameLabel = new JLabel("Name");
        occupationLabel = new JLabel("Occupation");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);
        
        okBtn = new JButton("OK");
        
        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outterBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outterBorder,innerBorder)); 
        
        // GridLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        
        
        
        /////////// FIRST ROW ////////////
        gc.weightx = 1;
        gc.weighty = .1;
        
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(nameLabel,gc);
        
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameField,gc);
        
        /////////// SECOND ROW //////////
        gc.weightx = 1;
        gc.weighty = 0.1;
        
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_END;
        add(occupationLabel,gc);
        
        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(occupationField,gc);
        
        /////// THIRD ROW ///////////
        gc.weightx = 1;
        gc.weighty = 2.0;
        
        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(okBtn,gc);
    }
}
