/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

/**
 *
 * @author Andrew Hopkins
 */
public class Alarm  {
    String alarmName;
    int hours;
    int minutes;
    int seconds;

    
    //constructor for the alarm

    /**
     *
     * @param alarmName
     * @param hours
     * @param minutes
     * @param seconds
     */
    public Alarm(String alarmName, int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.alarmName = alarmName;
    }
    
    /**
     *
     * @return alarmName the name the alarm is set in the dialog box such as "Bedtime" for Example
     */
    public String getAlarmName() {
        return alarmName;
    }

    /**
     *
     * @param alarmName
     */
    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    /**
     *
     * @return hours set for the alarm in the hourspinner 
     */
    public int getHours() {
        return hours;
    }

    /**
     *
     * @param hours
     */
    public void setHours(int hours) {
        this.hours = hours;
    }

    /**
     *
     * @return minutes set in the minute spinner
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     *
     * @param minutes
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    /**
     *
     * @return seconds that are set in the second spinner
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     *
     * @param seconds
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    
    
      
    
    
    
}