package owen.com.acfunbattery.interfaces;

/**
 * Created by Owen on 2015/12/14.
 */
public interface BatteryStateChangeListener {

    /**
     * 供电方式变化时调用
     */
    void onChargingStateChange(int state);

    /**
     * 电量水平变化时调用
     */
    void onBatteryLevelChange();

    /**
     * 当USB插入拔出时调用
     */
    void onPluggedStateChange(int state);
}
