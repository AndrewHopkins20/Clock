package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import queuemanager.PriorityQueue;
import queuemanager.QueueOverflowException;
import queuemanager.QueueUnderflowException;
import queuemanager.SortedArrayPriorityQueue;

public class View implements Observer {
    
    //Sets up the Priority Queue
    ClockPanel panel;
    PriorityQueue<Alarm> queue = new  SortedArrayPriorityQueue<>(8);
     
    
              
    //Creates the menu bar and its menus and menu items
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem setAlarm;
        JMenuItem viewNextAlarm;
        JMenuItem removeAlarm;
        
        //sets up the menubar
        menuBar = new JMenuBar();
        
        //adds menu to menu from menu bar
        menu = new JMenu("Menu");
        menuBar.add(menu);
        
        //add set alarm menu item to menu
        setAlarm = new JMenuItem("Set Alarm");
        menu.add(setAlarm);
        
        //add view alarm menu item to menu
        viewNextAlarm = new JMenuItem("View Next Alarm");     
        menu.add(viewNextAlarm);
        
        
        //add remove alarm menu item to menu
        removeAlarm = new JMenuItem("Remove Alarm");
        menu.add(removeAlarm);
        
        setAlarm.addActionListener(
        
        new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                //Gets instance of current time
                Calendar date = Calendar.getInstance();
                int  currentHour = date.get(Calendar.HOUR);
                int currentMinute = date.get(Calendar.MINUTE);
                
                int hours = 0;
                int minutes = 0;
                int seconds = 0;
                String alarmName;
        
                //sets spinner models for hour minutes and seconds
                SpinnerModel hourModel= new SpinnerNumberModel(01, 01, 12, 1);
                SpinnerModel minuteModel= new SpinnerNumberModel(0, 00, 59, 1);
                SpinnerModel secondModel= new SpinnerNumberModel(0, 00, 59, 1);
               
                // sets spinners to corresponding model
                JSpinner hourspinner = new JSpinner(hourModel);
                JSpinner minutespinner = new JSpinner(minuteModel);
                JSpinner secondspinner = new JSpinner(secondModel);
               
                //creates the dialog buttons for the set alarms
                alarmName = JOptionPane.showInputDialog(null, "Set Alarm Name");
                JOptionPane.showMessageDialog(null, hourspinner, "Set Hours", JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null, minutespinner, "Set Minutes", JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null, secondspinner, "Set Seconds", JOptionPane.PLAIN_MESSAGE);
               
                //sets values from spinners as objects
                Object hourResult = hourspinner.getValue();
                Object minuteResult = minutespinner.getValue();
                Object secondResult = secondspinner.getValue();
               
                //sets objects to numbers
                Number setHour = (Number) hourResult;
                Number setMinute = (Number) minuteResult;
                Number setSecond = (Number) secondResult;
               
                //sets numbers to ints
                hours = setHour.intValue();
                minutes = setMinute.intValue();
                seconds = setSecond.intValue();
               
                //shows the alarm that was just set
                JOptionPane.showMessageDialog(null, "your alarm " + alarmName + " is set for: " + hours + ":" + minutes + ":" + seconds);
               
                //Creates Doubles to calculate Priority
                double doubleCurrent = currentHour + (currentMinute/100);
                double doubleAlarm = hours + (minutes/100);
               
                //calculates the resulting double
                double doubleResult = (doubleCurrent/doubleAlarm)*100;
               
                //sets double to int
                int intResult = (int) doubleResult;
                System.out.println(intResult);
                
                //sets values to an alarms
                Alarm setAlarm = new Alarm(alarmName, hours, minutes, seconds);
           
                try {
                    //puts alarm and result into the queue
                    queue.add(setAlarm, intResult);
                } catch (QueueOverflowException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
                         
                try {
                    //souts head hours
                    System.out.println(queue.head().getHours());
                } catch (QueueUnderflowException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
        }
        //closes listener
        );
       
        viewNextAlarm.addActionListener( 
                
            new ActionListener(){ 
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if(queue.isEmpty()){
                            //if no alarms set shows this dialog box
                            JOptionPane.showMessageDialog(null, "No alarms set please set an alarm");                        
                        }
                        else {
                            //shows head alarm in queue
                            JOptionPane.showMessageDialog(null, "Your Next Alarm Called: " + queue.head().getAlarmName() + "is set for: " + queue.head().getHours()+ ":" + queue.head().getMinutes());
                        }
                    } catch (QueueUnderflowException ex) {
                        Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
 
            }
                
        );
        
        removeAlarm.addActionListener(
            new ActionListener(){ 
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if(queue.isEmpty()){
                            //if no alarms set shows this dialog box
                            JOptionPane.showMessageDialog(null, "No alarms to be removed");                   
                        }
                        else {
                        //tells you what alarm will be rmeoved and removes alarm
                        JOptionPane.showMessageDialog(null, "Your  Alarm Called: " + queue.head().getAlarmName() + " set for: " + queue.head().getHours()+ ":" + queue.head().getMinutes() + " has been Removed!");
                        queue.remove();
                        }
                    }catch (QueueUnderflowException ex) {
                        Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        );                   
        return menuBar;
    }
    
    /**
     *
     */
    public void runAlarm() throws QueueUnderflowException{
        
        //gets current time
        Calendar date = Calendar.getInstance();
        int  currentHour = date.get(Calendar.HOUR);
        int currentMinute = date.get(Calendar.MINUTE);
        
        //checks if current time is time set on the head alarm and runs the alarm, after running it removes it
        if(currentHour == queue.head().getHours() && currentMinute == queue.head().getMinutes()){
            JOptionPane.showMessageDialog(null, "Your Alarm " + queue.head().getAlarmName() + " Is going off");
            queue.remove();
        }    
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
