package com.geren.test.test.ui.Activitys;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.geren.test.test.R;
import com.geren.test.test.ui.Activitys.BaseActivity;
import com.geren.test.test.ui.fragments.CompassFragment;
import com.geren.test.test.ui.fragments.MapFragment;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg_main;
    private Fragment showFragment;
    private FragmentManager fmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化
        initView();
        //开启事物
        fmManager = getSupportFragmentManager();
        FragmentTransaction fmTransaction = fmManager.beginTransaction();

        //加载第一个fragment
        showFragment = new MapFragment();
        //添加到事物中
        fmTransaction.add(R.id.contain_main, showFragment, MapFragment.TAG);
        //提交事物
        fmTransaction.commit();

    }

    private void initView() {
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        //设置监听
        rg_main.setOnCheckedChangeListener(this);
    }

    /**
     * 设置点击监听
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(final RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_map:
                choseFragment(MapFragment.TAG, MapFragment.class);
                break;
            case R.id.rb_compass:
                choseFragment(CompassFragment.TAG, CompassFragment.class);
                break;
        }
    }

    /**
     * 加载选择的fragment
     *
     * @param tag
     * @param cla
     */
    private void choseFragment(String tag, Class<? extends Fragment> cla) {

        FragmentTransaction fmTransaction = fmManager.beginTransaction();
        //隐藏当前fragment
        fmTransaction.hide(showFragment);
        //使用tag找到fragment
        showFragment = fmManager.findFragmentByTag(tag);

        //如果找到就直接显示
        if (showFragment != null) {
            fmTransaction.show(showFragment);

        } else {
            //如果没有找到，实例化一个，添加到碎片管理
            try {
                showFragment = cla.getConstructor().newInstance();
                fmTransaction.add(R.id.contain_main, showFragment, tag);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        fmTransaction.commit();

    }

    private long exitTime = 0;
    /**
     * 按键点击事件
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

            if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
                if((System.currentTimeMillis()-exitTime) > 2000){
                    Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                    System.exit(0);
                }
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }




}
