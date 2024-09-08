package view;

import model.Fan;
import model.Light;
import model.Thermostat;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class SmartHome extends JFrame{
    private JPanel smartPanel;
    private JSlider speedSlider;
    private JLabel fanStatus;
    private JLabel acMode;
    private JComboBox acModes;
    private JButton offButton;
    private JLabel updateText;

    private final Fan fan = new Fan();
    private final Thermostat thermostat = new Thermostat();
    private final Light light = new Light();

    public SmartHome() {
        setContentPane(smartPanel);
        setTitle("Smart Home Controller");
        setSize(600,300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        updateText.setVisible(false);

        fanStatus.setForeground(Color.RED);
        acMode.setForeground(Color.RED);
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int fanSpeed = speedSlider.getValue();
                fan.setSpeed(fanSpeed);
                String status = fan.getSpeed();
                fanStatus.setText(status);
                fanStatus.setForeground(status.equals("Off") ? Color.RED : Color.CYAN);
            }
        });

        acModes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mode = (String) acModes.getSelectedItem();
                thermostat.setMode(mode);
                String ac = thermostat.getMode();
                acMode.setText(ac);
                acMode.setForeground(ac.equals("Cool") ? Color.CYAN : Color.RED);
            }
        });

        offButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                light.toggle();
                if(light.isOn()){
                    offButton.setText("On");
                    offButton.setForeground(Color.CYAN);
                }else{
                    offButton.setText("Off");
                    offButton.setForeground(Color.BLACK);
                }
            }
        });

        offButton.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(light.isOn()){
                    offButton.setText("On");
                    offButton.setForeground(Color.CYAN);
                }else{
                    offButton.setText("Off");
                    offButton.setForeground(Color.BLACK);
                }
            }
        });
    }

    public void turnOffAll(){
        light.turnOff();
        fan.turnOff();
        thermostat.turnOff();
        speedSlider.setValue(0);
        acModes.setSelectedIndex(0);
        offButton.setText("Off");
        updateText.setVisible(true);
    }

    public void scheduleUpdate(){
        java.util.Timer timer = new Timer();
        Calendar updateTime = Calendar.getInstance();

        //for testing purpose, please comment below code

        updateTime.set(Calendar.MONTH,Calendar.JANUARY);
        updateTime.set(Calendar.DAY_OF_MONTH,1);
        updateTime.set(Calendar.HOUR_OF_DAY,1);
        updateTime.set(Calendar.MINUTE,0);
        updateTime.set(Calendar.SECOND,0);

        Calendar now = Calendar.getInstance();
        if(now.after(updateTime)){
            updateTime.add(Calendar.YEAR,1);
        }

        //up to above line

        //for testing purpose, please uncomment below two lines. and when you run app, it will be triggered after 15 seconds.

//        long millis = System.currentTimeMillis();
//        updateTime.setTimeInMillis(millis + 15000);

        long duration = 365 * 24 * 60 * 60 * 1000L;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                turnOffAll();
            }
        },updateTime.getTime(), duration);
    }
}