package model;

public class Thermostat implements Appliance {
    private String mode = "Off";

    @Override
    public void turnOn() {
        mode = "Cool";
    }

    @Override
    public void turnOff() {
        mode = "Off";
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
