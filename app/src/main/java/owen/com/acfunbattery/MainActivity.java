package owen.com.acfunbattery;

import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
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
    @Bind(R.id.iv_img) ImageView mIvAvatar;

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

        int batteryLevel = (int) (1.0 * batteryLeve / batteryScale * 100);

        mTvBatteryLevel.setText(String.format(getString(R.string.batteryLevel), batteryLevel));
        updateImageByBatteryLevel(batteryLevel);
    }

    /**
     * 根据电量显示不同的图片
     */
    private void updateImageByBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 90) {
            mIvAvatar.setImageResource(R.drawable.ac20);
        } else if (batteryLevel >= 85) {
            mIvAvatar.setImageResource(R.drawable.ac13);
        } else if (batteryLevel >= 75) {
            mIvAvatar.setImageResource(R.drawable.ac10);
        } else if (batteryLevel >= 60) {
            mIvAvatar.setImageResource(R.drawable.ac30);
        } else if (batteryLevel >= 45) {
            mIvAvatar.setImageResource(R.drawable.ac34);
        } else if (batteryLevel >= 30) {
            mIvAvatar.setImageResource(R.drawable.ac22);
        } else if (batteryLevel >= 15) {
            mIvAvatar.setImageResource(R.drawable.ac35);
        } else if (batteryLevel >= 5) {
            mIvAvatar.setImageResource(R.drawable.ac46);
        } else {
            mIvAvatar.setImageResource(R.drawable.ac53);
        }
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
        switch (event.getState()) {
            case 0:
                mTvPowerInfo.setText(R.string.plugged_by_battery);
                break;

            case BatteryManager.BATTERY_PLUGGED_AC:
                mTvPowerInfo.setText(R.string.plugged_by_ac);
                break;

            case BatteryManager.BATTERY_PLUGGED_USB:
                mTvPowerInfo.setText(R.string.plugged_by_usb);
                break;

            case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                mTvPowerInfo.setText(R.string.plugged_by_wireless);
                break;

            default:
                mTvPowerInfo.setText(R.string.unknow_plugged);
                break;
        }
    }

}
