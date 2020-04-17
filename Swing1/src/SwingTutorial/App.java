package SwingTutorial;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/*
 * By:     Matthew Fischer
 * Date:   
 */

/**
 *
 * @author Matthew Fischer
 */
public class App {
    public static void main(String[] args){
        
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                new MainFrame();
            } 
        });
        
        
    }
}
