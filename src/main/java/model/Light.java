package model;

public class Light implements Appliance {
    private boolean isOn;

    @Override
    public void turnOn() {
        isOn = true;
    }

    @Override
    public void turnOff() {
        isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }

   public void toggle(){
        if(isOn){
            turnOff();
        }else {
            turnOn();
        }
   }
}
