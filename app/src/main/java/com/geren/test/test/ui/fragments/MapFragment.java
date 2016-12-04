package com.geren.test.test.ui.fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.RotateAnimation;
import com.geren.test.test.R;

/**
 * Created by Kevin on 2016/12/3.
 */
public class MapFragment extends BaseFragment implements View.OnClickListener, LocationSource, AMap.OnMarkerClickListener, AMap.OnMarkerDragListener, AMap.OnInfoWindowClickListener {

    public static final String TAG = MapFragment.class.getSimpleName();

    private MapView mMapView = null;
    private AMap aMap;
    private Button btn_normalmap;
    private Button btn_satelitemap;
    private View btn_nightmap;
    //初始化uisetting
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

//默认点标记
        LatLng latLng = new LatLng(39.906901,116.397972);
        final Marker marker = aMap.addMarker(new MarkerOptions().
                position(latLng).
                title("北京").
                snippet("地球的中心点--大天安门"));

//开发者可根据自己实际的业务需求，在地图指定的位置上添加自定义的点标记信息
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(new LatLng(40.104848,116.377966));
        markerOption.title("老潘的窝").snippet("平西王府西：116.377966,40.104848");
        //是否可拖动
        markerOption.draggable(true);
        markerOption.icon(
                BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(),
                                R.mipmap.location_marker)));
        // 将Marker设置为贴地显示，可以双指下拉看效果
        markerOption.setFlat(true);
        Marker marker1 = aMap.addMarker(markerOption);

        markerOption = new MarkerOptions();
//        markerOption.position(Constants.XIAN);
        markerOption.title("西安市").snippet("西安市：34.341568, 108.940174");
        markerOption.draggable(true);
        // 将Marker设置为贴地显示，可以双指下拉看效果
        markerOption.setFlat(true);

//自v4.0.0版本起，SDK提供了给Marker设置动画的方法
        Animation animation = new RotateAnimation(marker.getRotateAngle(), marker.getRotateAngle() + 360, 0, 0, 0);
        long duration = 10000L;
        animation.setDuration(duration);
        animation.setInterpolator(new LinearInterpolator());
        //设置动画
        marker.setAnimation(animation);
        marker.startAnimation();
        marker1.setAnimation(animation);
        marker1.startAnimation();

        //设置监听
        btn_normalmap = (Button) layout.findViewById(R.id.btn_normalmap);
        btn_normalmap.setOnClickListener(this);
        btn_satelitemap = (Button) layout.findViewById(R.id.btn_satellitemap);
        btn_satelitemap.setOnClickListener(this);
        btn_nightmap = layout.findViewById(R.id.btn_nightmap);
        btn_nightmap.setOnClickListener(this);
        //绑定标注点点击事件
        aMap.setOnMarkerClickListener(this);
        //绑定标记拖拽事件
        aMap.setOnMarkerDragListener(this);
        //绑定窗口信息点击事件
        aMap.setOnInfoWindowClickListener(this);
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
        Log.e(TAG, "定位监听: "+onLocationChangedListener );
    }

    /**
     * 定位的监听
     */
    @Override
    public void deactivate() {

    }

    /**
     * 生命周期
     */
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
     * 标记点击回调AMap.OnMarkerClickListener，监听标记点击事件
     * @param marker
     * @return
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(getContext(), "你勇敢的的点击了"+marker.getTitle(), Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * 标记拖拽回调AMap.OnMarkerDragListener,监听标记拖拽事件
     * @param marker
     */
    @Override
    public void onMarkerDragStart(Marker marker) {
        Toast.makeText(getContext(), "标记拖拽回调AMap.OnMarkerDragListener,监听标记拖拽事件1", Toast.LENGTH_SHORT).show();
    }

    /**
     * 标记拖拽回调AMap.OnMarkerDragListener,监听标记拖拽事件
     * @param marker
     */
    @Override
    public void onMarkerDrag(Marker marker) {
        Toast.makeText(getContext(), "标记拖拽回调AMap.OnMarkerDragListener,监听标记拖拽事件2", Toast.LENGTH_SHORT).show();

    }

    /**
     * 标记拖拽回调AMap.OnMarkerDragListener,监听标记拖拽事件
     * @param marker
     */
    @Override
    public void onMarkerDragEnd(Marker marker) {
        Toast.makeText(getContext(), "标记拖拽回调AMap.OnMarkerDragListener,监听标记拖拽事件3", Toast.LENGTH_SHORT).show();

    }

    /**
     * 信息窗点击事件AMap.OnInfoWindowClickListener
     * @param marker
     */
    @Override
    public void onInfoWindowClick(Marker marker) {
        Snackbar.make(getView(),marker.getTitle(),Snackbar.LENGTH_SHORT).show();
    }
}
