package cn.da0ke.androidkit.map;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.amap.api.maps2d.CoordinateConverter;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviParaOption;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.utils.OpenClientUtil;

import cn.da0ke.androidkit.R;

/**
 * Created by da0ke on 2017/11/30
 */

public class MapActivity extends Activity {

    private double lng;
    private double lat;
    private String com;
    private double myLat;
    private double myLng;

    private com.baidu.mapapi.map.MapView mMapView = null;
    private BaiduMap mBaiduMap = null;
    private RoutePlanSearch mSearch = null;
    private BDLocationListener myListener = new MyLocationListenner();
    private boolean isFirstLoc = true;
    private LocationClient mLocClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //百度地图：在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
//        SDKInitializer.initialize(getApplicationContext());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.androidkit_map_activity_map);

        initView();
    }

    private void initView() {
        //返回
        ImageButton nav_back = findViewById(R.id.nav_back);
        nav_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //nav
        TextView nav_title = findViewById(R.id.nav_title);
        nav_title.setText("地图");

        lng = getIntent().getDoubleExtra("lng", 116.403694);
        lat = getIntent().getDoubleExtra("lat", 39.916263);
        com = getIntent().getStringExtra("com");
        String address = getIntent().getStringExtra("address");

        mMapView = findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        //设置中心点
        LatLng point = new LatLng(lat, lng);
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(point)
                .zoom(18)
                .build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);

        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

        //设置标注信息
        View overlay = View.inflate(getApplicationContext(),R.layout.androidkit_map_view_overlay,null);
//定义用于显示该InfoWindow的坐标点
        LatLng pt = new LatLng(lat, lng);
//创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
        InfoWindow mInfoWindow = new InfoWindow(overlay, pt, -97);

//显示InfoWindow
        mBaiduMap.showInfoWindow(mInfoWindow);
        overlay.findViewById(R.id._navi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initNavi();
            }
        });
        ((TextView)overlay.findViewById(R.id._com)).setText(com);
        ((TextView)overlay.findViewById(R.id._address)).setText(address);
    }

    /**
     * 定位SDK监听函数
     */
    private class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null)
                return;
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                myLat = location.getLatitude();
                myLng = location.getLongitude();

                //驾车线路规划
                mSearch = RoutePlanSearch.newInstance();
                OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {

                    @Override
                    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

                    }

                    @Override
                    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

                    }

                    @Override
                    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

                    }

                    @Override
                    public void onGetDrivingRouteResult(DrivingRouteResult result) { //获取驾车线路规划结果
                        if(result.error == SearchResult.ERRORNO.NO_ERROR) {
                            DrivingRouteOverlay overlay = new DrivingRouteOverlay(mBaiduMap);
                            mBaiduMap.setOnMarkerClickListener(overlay);
                            overlay.setData(result.getRouteLines().get(0));
                            overlay.addToMap();
//                        overlay.zoomToSpan();
                        }
                    }

                    @Override
                    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

                    }

                    @Override
                    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

                    }
                };
                mSearch.setOnGetRoutePlanResultListener(listener);
                PlanNode stNode = PlanNode.withLocation(new LatLng(myLat, myLng));
                PlanNode enNode = PlanNode.withLocation(new LatLng(lat, lng));
                mSearch.drivingSearch((new DrivingRoutePlanOption())
                        .from(stNode)
                        .to(enNode));

            }
        }

    }

    /**
     * 导航
     */
    private void initNavi() {
        try {
            if(OpenClientUtil.getBaiduMapVersion(this)!=0) { //百度地图
                Intent i1 = new Intent();
                i1.setData(Uri.parse("baidumap://map/marker?location="+lat+","+lng+"&title="+com));
                startActivity(i1);
            } else { //高德地图
                CoordinateConverter converter  = new CoordinateConverter();
                converter.from(CoordinateConverter.CoordType.BAIDU);

                converter.coord(new com.amap.api.maps2d.model.LatLng(lat,lng));
                com.amap.api.maps2d.model.LatLng endPoint = converter.convert();
                Intent i1 = new Intent(Intent.ACTION_VIEW,Uri.parse("http://uri.amap.com/navigation?to="+endPoint.longitude+","+endPoint.latitude+","+com+"&src=锦绣网&callnative=1&mode=car"));
                startActivity(i1);
            }
        } catch (Exception e) {
            Log.e("saint","地图app都未安装");
            NaviParaOption para = new NaviParaOption();
            para.startPoint(new LatLng(myLat, myLng));
            para.endPoint(new LatLng(lat, lng));
            BaiduMapNavigation.openBaiduMapNavi(para,this);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        //释放检索实例
        mSearch.destroy();
        //关闭定位
        mLocClient.stop();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
