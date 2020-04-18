/*
 * By:     Matthew Fischer
 * Date:   
 */
package SwingTutorial;

import java.util.EventObject;

/**
 *
 * @author Matthew Fischer
 */
public class FormEvent extends EventObject{
    
    private String name;
    private String occupation;
    private int ageCategory;
    
    public FormEvent(Object source, String name, String occupation, int ageCat) {
        super(source);
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCat;
    }

    // GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public int getAgeCategory() {
        return ageCategory;
    }

   
    
}
