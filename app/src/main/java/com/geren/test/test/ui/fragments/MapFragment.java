package com.geren.test.test.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.geren.test.test.R;

/**
 * Created by Kevin on 2016/12/3.
 */
public class MapFragment extends BaseFragment implements View.OnClickListener, LocationSource {

    public static final String TAG = MapFragment.class.getSimpleName();

    private MapView mMapView = null;
    private AMap aMap;
    private Button btn_normalmap;
    private Button btn_satelitemap;
    private View btn_nightmap;
    private View btn_big;
    private View btn_small;
    private UiSettings mUiSettings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layout = inflater.inflate(R.layout.fragment_map,container,false);
        //获取地图控件引用
        mMapView = ((MapView) layout.findViewById(R.id.map));
        //初始化
        init();
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mMapView.onCreate(savedInstanceState);

        return layout;
    }

    /**
     * 初始化
     */
    private void init() {
        //初始化amap
        aMap = mMapView.getMap();
        //初始化uisetting
        mUiSettings = aMap.getUiSettings();
        //设置放大和缩小按钮
        mUiSettings.setZoomControlsEnabled(true);
        //
//        mUiSettings.setZoomPosition(300);
        //设置指南针
        mUiSettings.setCompassEnabled(true);
        //设置定位点
        aMap.setLocationSource(this);
        //显示默认的定位按钮
        mUiSettings.setMyLocationButtonEnabled(true);
        //可触发定位并显示定位层
        aMap.setMyLocationEnabled(true);
        //设置比例尺控件
        mUiSettings.setScaleControlsEnabled(true);
        //设置监听
        btn_normalmap = (Button) layout.findViewById(R.id.btn_normalmap);
        btn_normalmap.setOnClickListener(this);
        btn_satelitemap = (Button) layout.findViewById(R.id.btn_satellitemap);
        btn_satelitemap.setOnClickListener(this);
        btn_nightmap = layout.findViewById(R.id.btn_nightmap);
        btn_nightmap.setOnClickListener(this);

//        btn_big = layout.findViewById(R.id.btn_big);
//        btn_big.setOnClickListener(this);
//        btn_small = layout.findViewById(R.id.btn_small);
//        btn_small.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 点击监听，转换地图模式
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_normalmap:
                aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                break;

            case R.id.btn_satellitemap:
                aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
                break;

            case R.id.btn_nightmap:
                aMap.setMapType(AMap.MAP_TYPE_NIGHT);
                break;

        }
    }

    /**
     * 定位的监听
     * @param onLocationChangedListener
     */
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    /**
     * 定位的监听
     */
    @Override
    public void deactivate() {

    }
}
