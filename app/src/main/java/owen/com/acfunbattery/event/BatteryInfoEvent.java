package owen.com.acfunbattery.event;

/**
 * 电池信息
 *
 * Created by Owen on 2015/12/14.
 */
public class BatteryInfoEvent {

    /**
     * 电压
     */
    private int voltage;

    /**
     * 电池温度
     */
    private int temperature;

    public BatteryInfoEvent(int voltage, int temperature) {
        this.voltage = voltage;
        this.temperature = temperature;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

}
