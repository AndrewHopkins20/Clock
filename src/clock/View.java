package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;

public class View implements Observer {
    
    ClockPanel panel;
    
    public static JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem setAlarm;
        JMenuItem changeClock;
        final JFrame fr = null;
       
        
        
        menuBar = new JMenuBar();
        
        menu = new JMenu("Menu");
        menuBar.add(menu);
       
       setAlarm = new JMenuItem("Set Alarm");
        menu.add(setAlarm);
        
        setAlarm.addActionListener(
        
        new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int hour = 0;
                int minute = 0;
                String alarmName;
        
               SpinnerModel hourModel= new SpinnerNumberModel(01, 01, 12, 1);
               SpinnerModel minuteModel= new SpinnerNumberModel(0, 00, 59, 1);
               
               JSpinner hourspinner = new JSpinner(hourModel);
               JSpinner minutespinner = new JSpinner(minuteModel);
               
               alarmName = JOptionPane.showInputDialog(null, "Set Alarm Name");
               JOptionPane.showMessageDialog(null, hourspinner, "Set Hours", JOptionPane.PLAIN_MESSAGE);
               JOptionPane.showMessageDialog(null, minutespinner, "Set Minutes", JOptionPane.PLAIN_MESSAGE);
               
               
               Object hourResult = hourspinner.getValue();
               Object minuteResult = minutespinner.getValue();
               
               Number setHour = (Number) hourResult;
               Number setMinute = (Number) minuteResult;
               
               hour = setHour.intValue();
               minute = setMinute.intValue();
               
               JOptionPane.showMessageDialog(null, "your alarm " + alarmName + " is set for: " + hour + ":" + minute);
               
               
                
                
            }
            
        }
        
        
        );
        
        return menuBar;
    }
    
    public View(Model model) {
        JFrame frame = new JFrame();
        panel = new ClockPanel(model);
        //frame.setContentPane(panel);
        frame.setTitle("Java Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       
        
        
       
        
        
        // Start of border layout code
        
        // I've just put a single button in each of the border positions:
        // PAGE_START (i.e. top), PAGE_END (bottom), LINE_START (left) and
        // LINE_END (right). You can omit any of these, or replace the button
        // with something else like a label or a menu bar. Or maybe you can
        // figure out how to pack more than one thing into one of those
        // positions. This is the very simplest border layout possible, just
        // to help you get started.
        
        Container pane = frame.getContentPane();
        
       
        
        panel.setPreferredSize(new Dimension(200, 200));
        frame.setJMenuBar(createMenuBar());
        pane.add(panel, BorderLayout.CENTER);
         
       JButton button = new JButton("Button 3 (LINE_START)");
        pane.add(button, BorderLayout.LINE_START);
         
        button = new JButton("Long-Named Button 4 (PAGE_END)");
        pane.add(button, BorderLayout.PAGE_END);
         
        button = new JButton("5 (LINE_END)");
        pane.add(button, BorderLayout.LINE_END);
        
        // End of borderlayout code
        
     
        frame.pack();
        frame.setVisible(true);
    }
    
    public void update(Observable o, Object arg) {
        panel.repaint();
    }
    
    
    

}
