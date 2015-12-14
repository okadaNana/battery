package owen.com.acfunbattery;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import owen.com.acfunbattery.event.BatteryInfoEvent;
import owen.com.acfunbattery.event.BatteryLevelEvent;
import owen.com.acfunbattery.event.BatteryPluggedEvent;
import owen.com.acfunbattery.receiver.BatteryStateReceiver;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolBar;
    @Bind(R.id.tv_batteryLevel) TextView mTvBatteryLevel;
    @Bind(R.id.tv_powerInfo) TextView mTvPowerInfo;

    private int mBatteryVoltage;
    private int mBatteryTemperature;

    private static final String ACTION = "android.intent.action.BATTERY_CHANGED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        registerReceiver(new BatteryStateReceiver(), intentFilter);

        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        setSupportActionBar(mToolBar);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @OnClick(R.id.fab)
    public void onFabClick(View view) {
        Snackbar.make(view, String.format(getString(R.string.powerInfo), (mBatteryVoltage / 1000.0F), (mBatteryTemperature / 10.0F)), Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 电量
     */
    public void onEvent(BatteryLevelEvent event) {
        int batteryLeve = event.getLevel();
        int batteryScale = event.getScale();

        mTvBatteryLevel.setText(String.format(getString(R.string.batteryLevel), (batteryLeve / batteryScale * 100)));
    }

    /**
     * 电压、温度
     */
    public void onEvent(BatteryInfoEvent event) {
        mBatteryVoltage = event.getVoltage();
        mBatteryTemperature = event.getTemperature();
    }

    /**
     * 供电方式
     */
    public void onEvent(BatteryPluggedEvent event) {
        if (event.getState() == 0) {
            mTvPowerInfo.setText(R.string.plugged_by_battery);
        } else if (event.getState() != -1) {
            mTvPowerInfo.setText(R.string.plugged_by_usb);
        } else {
            mTvPowerInfo.setText(R.string.unknow_plugged);
        }
    }

}
