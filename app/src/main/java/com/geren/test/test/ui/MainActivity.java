package com.geren.test.test.ui;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.SupportMapFragment;
import com.geren.test.test.R;
import com.geren.test.test.ui.fragments.ActionFragment;
import com.geren.test.test.ui.fragments.BaseFragment;
import com.geren.test.test.ui.fragments.MapFragment;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg_main;
    private Fragment showFragment;
    private FragmentManager fmManager;
    private AMap aMap;

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
            case R.id.rb_action:
                choseFragment(ActionFragment.TAG, ActionFragment.class);
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
}
