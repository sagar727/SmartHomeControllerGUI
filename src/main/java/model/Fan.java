package model;

public class Fan implements Appliance {
    private int speed = 0;

    @Override
    public void turnOn() {
       speed = 1;
    }

    @Override
    public void turnOff() {
        speed = 0;
    }

    public String getSpeed() {
        return speed == 0 ? "Off"  : "On";
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        if(speed == 0){
            turnOff();
        }
    }
}
