package owen.com.acfunbattery.event;

/**
 * Created by Owen on 2015/12/14.
 */
public class BatteryLevelEvent {

    /**
     * 剩余电量
     */
    private int level;

    /**
     * 电量最大值
     */
    private int scale;

    public BatteryLevelEvent(int level, int scale) {
        this.level = level;
        this.scale = scale;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }
}
