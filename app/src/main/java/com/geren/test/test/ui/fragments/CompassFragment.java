package com.geren.test.test.ui.fragments;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.geren.test.test.R;
import com.geren.test.test.ui.MyView.CompassView;

/**
 * Created by Kevin on 2016/12/3.
 */
public class CompassFragment extends BaseFragment implements SensorEventListener {

    public static final String TAG = CompassFragment.class.getSimpleName();
    private CompassView imgCompass;
    private float currentDegree = 0f;
    private TextView tv_fx;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layout = inflater.inflate(R.layout.fragment_compass, container, false);
        //初始化
        imgCompass = ((CompassView) layout.findViewById(R.id.img_compass));
        tv_fx = ((TextView) layout.findViewById(R.id.tv_fx));
        //传感器管理者
        SensorManager sm = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        // 注册传感器(Sensor.TYPE_ORIENTATION(方向传感器);SENSOR_DELAY_FASTEST(0毫秒延迟);
        // SENSOR_DELAY_GAME(20,000毫秒延迟)、SENSOR_DELAY_UI(60,000毫秒延迟))
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);


        return layout;
    }

    /**
     * 传感器报告新的值(方向改变)
     *
     * @param event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            float degree = event.values[0];
//            float value1 = event.values[1];
            Log.e(TAG, "参数1--》 " + event.values[0] + ",参数2--》" + event.values[1] + "，参数3--》" + event.values[2]);
            /*
            RotateAnimation类：旋转变化动画类
            参数说明:
            fromDegrees：旋转的开始角度。
            toDegrees：旋转的结束角度。
            pivotXType：X轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
            pivotXValue：X坐标的伸缩值。
            pivotYType：Y轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
            pivotYValue：Y坐标的伸缩值
            */
            RotateAnimation ra = new RotateAnimation(currentDegree, -degree,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            //旋转过程持续时间
            ra.setDuration(200);
            //罗盘图片使用旋转动画
            imgCompass.startAnimation(ra);
            tvShow((int) degree);
            currentDegree = -degree;
        }
    }

    /**
     * 设置中间文字显示
     *
     * @param degree
     */
    private void tvShow(int degree) {

        if (degree > 0 && degree < 45) {
            tv_fx.setTextColor(Color.WHITE);
            tv_fx.setText("北偏东\n" + degree + "度");
        } else if (degree >= 45 && degree < 90) {
            tv_fx.setTextColor(Color.WHITE);
            tv_fx.setText("东偏北\n" + (90 - degree) + "度");
        } else if (degree == 90) {
            tv_fx.setTextColor(Color.RED);
            tv_fx.setText("正东");
        } else if (degree > 90 && degree < 135) {
            tv_fx.setTextColor(Color.WHITE);
            tv_fx.setText("东偏南\n" + (degree - 90) + "度");
        } else if (degree >= 135 && degree < 180) {
            tv_fx.setTextColor(Color.WHITE);
            tv_fx.setText("南偏东\n" + (180 - degree) + "度");
        } else if (degree == 180) {
            tv_fx.setTextColor(Color.RED);
            tv_fx.setText("正南");
        } else if (degree > 180 && degree < 235) {
            tv_fx.setTextColor(Color.WHITE);
            tv_fx.setText("南偏西\n" + (degree - 180) + "度");
        } else if (degree >= 235 && degree < 270) {
            tv_fx.setTextColor(Color.WHITE);
            tv_fx.setText("西偏南\n" + (270 - degree) + "度");
        } else if (degree == 270) {
            tv_fx.setTextColor(Color.RED);
            tv_fx.setText("正西");
        } else if (degree >= 270 && degree < 315) {
            tv_fx.setTextColor(Color.WHITE);
            tv_fx.setText("西偏北\n" + (degree - 270) + "度");
        } else if (degree >= 315 && degree < 360) {
            tv_fx.setTextColor(Color.WHITE);
            tv_fx.setText("北偏西\n" + (360 - degree) + "度");
        } else {
            tv_fx.setTextColor(Color.RED);
            tv_fx.setText("正北");
        }
    }

    /**
     * 传感器精度的改变
     *
     * @param sensor
     * @param accuracy
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
