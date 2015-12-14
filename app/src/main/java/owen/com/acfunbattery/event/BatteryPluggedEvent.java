package owen.com.acfunbattery.event;

/**
 * Created by Owen on 2015/12/14.
 */
public class BatteryPluggedEvent {

    private int state;

    public BatteryPluggedEvent(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
