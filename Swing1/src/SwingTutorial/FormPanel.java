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
                
       // intialize
        nameLabel = new JLabel("Name");
        occupationLabel = new JLabel("Occupation");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);
        okBtn = new JButton("OK");
        
        // GridLayout
        this.setLayout(new GridLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;// space compared to other cells
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

                
        add(nameLabel,gc);
        gc.gridx = 1;
        gc.gridy = 0;
        add(nameField,gc);
        
        add(occupationLabel);
        
        add(occupationField);
        add(okBtn);
        
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        
        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outterBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outterBorder,innerBorder));
        
    }
}
