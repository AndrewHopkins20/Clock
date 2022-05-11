package clock;

import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import queuemanager.QueueUnderflowException;

/**
 *
 * @author Andrew Hopkins
 */
public class Controller {
    
    ActionListener listener;
    Timer timer;
    
    Model model;
    View view;
    
    /**
     *
     * @param m
     * @param v
     */
    public Controller(Model m, View v) {
        model = m;
        view = v;
        
        listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //checks if the alarm needs to run every 10th of a second
                    view.runAlarm();
                } catch (QueueUnderflowException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                model.update();
            }
        };
        
        timer = new Timer(100, listener);
        timer.start();
    }
}
