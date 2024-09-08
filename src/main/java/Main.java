import view.SmartHome;

public class Main {

    public static void main(String[] args) {
        SmartHome sh = new SmartHome();
        sh.scheduleUpdate();
        sh.setVisible(true);
    }
}