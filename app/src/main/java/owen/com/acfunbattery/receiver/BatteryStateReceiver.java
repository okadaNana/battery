package owen.com.acfunbattery.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

import de.greenrobot.event.EventBus;
import owen.com.acfunbattery.event.BatteryInfoEvent;
import owen.com.acfunbattery.event.BatteryLevelEvent;

/**
 * Created by Owen on 2015/12/14.
 */
public class BatteryStateReceiver extends BroadcastReceiver {

    private static volatile BatteryStateReceiver sInstance;

    @Override
    public void onReceive(Context context, Intent intent) {
        EventBus.getDefault().post(new BatteryInfoEvent(
                intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0),
                intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0)));

        EventBus.getDefault().post(new BatteryLevelEvent(
                intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1),
                intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)));
    }


}
