/*
 * By:     Matthew Fischer
 * Date:   
 */
package SwingTutorial;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private FormListener formListener;
    private JList ageList;
    private JComboBox empCombo;
            
    public FormPanel(){
        
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        
        // intialize
        nameLabel = new JLabel("Name");
        occupationLabel = new JLabel("Occupation");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);
        
        empCombo = new JComboBox();

        // Set up combo box
        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        empModel.addElement("employed");
        empModel.addElement("self-employed");
        empModel.addElement("un-employed");
        empCombo.setModel(empModel);
        
        ageList = new JList();
        ageList.setPreferredSize(new Dimension(110,66));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        
        // Set up list box
        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeCategory(0,"Under 18"));
        ageModel.addElement(new AgeCategory(1,"18 to 65"));
        ageModel.addElement(new AgeCategory(2,"65 or over"));
        ageList.setModel(ageModel);
        ageList.setSelectedIndex(1);
        
        
        okBtn = new JButton("OK");
        okBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String occupation = occupationField.getText();
                AgeCategory ageCat = (AgeCategory)ageList.getSelectedValue();
                
                System.out.println(ageCat.getId());
                
                FormEvent ev = new FormEvent(this,name,occupation,ageCat.getId());
                
                if(formListener != null){
                    formListener.formEventOccurred(ev);
                }
            }
            
        });
        
        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outterBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outterBorder,innerBorder)); 
        
        layoutComponents();
    }
    
    public void setFormListener(FormListener listener){
        this.formListener = listener;
    }
    
    
    public void layoutComponents(){
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
        /////////// third ROW //////////
        gc.weightx = 1;
        gc.weighty = 0.1;
        
        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(ageList,gc);
        
        /////// FOURTH ROW ///////////
        gc.weightx = 1;
        gc.weighty = 2.0;
        
        gc.gridx = 1;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(okBtn,gc);
    }
}

class AgeCategory {
    private int id;
    private String text;
    
    public AgeCategory(int id,String text) {
        this.id = id;
        this.text = text;
    }
    
    public String toString(){
        return text;
    }
    
    public int getId(){
        return id;
    }
}