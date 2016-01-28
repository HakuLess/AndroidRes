package less.haku.androidres.common;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HaKu on 16/1/28.
 * 定位服务
 */

public class LocationManager {

    private static LocationManager _instance;
    private Context context;

    public static LocationManager instance(Application application) {
        if (_instance == null) {
            _instance = new LocationManager(application);
        }
        return _instance;
    }

    public interface ILocationListener {
        void receiveLocation(BDLocation loc);
    }

    private BDLocation mLocation;
    public LocationClient mLocationClient;
    public MyLocationListener mMyLocationListener;
    private ArrayList<ILocationListener> listListener = new ArrayList<ILocationListener>();
    private LocationManager(Context context) {
        this.context = context;
        mLocationClient = new LocationClient(context);
        mMyLocationListener = new MyLocationListener();
        InitLocation();
        start();
    }


    private void start() {
        mLocationClient.unRegisterLocationListener(mMyLocationListener);
        mLocationClient.registerLocationListener(mMyLocationListener);
        mLocationClient.start();
        mLocationClient.requestLocation();
    }

    private void InitLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        mLocationClient.setLocOption(option);
    }

    /**
     * 获取当前定位点(为应用启动时或上次定位的地点，不能保证为最精确)
     */
    public BDLocation getLocation() {
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();
        }
        mLocationClient.requestLocation();
        return mLocation;
    }


    /**
     *获取原来的定位信息
     */
    public BDLocation getOldLocation() {
        return mLocation;
    }

    /**
     *触发一次请求定位操作
     */
    public void requestLocation() {
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();
        }
        mLocationClient.requestLocation();
    }

    /**
     *
     * 请求一次定位操作，并监听定位结果
     */
    public void requestLocation(ILocationListener listener) {
        listListener.add(listener);
        requestLocation();
    }


    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation loc) {
            logLocation(loc);
            Log.v("location", "LocType : " + loc.getLocType() + "\n");
            mLocation = loc;

            if (mLocationClient.isStarted()) {
                mLocationClient.stop();
            }

            /** 只保留最近的事件监听 **/
            while (listListener.size() > 0) {
                ILocationListener l = listListener.remove(0);
                l.receiveLocation(mLocation);
            }
        }

    }

    private boolean isValid(BDLocation loc) {
        if (loc == null || Math.abs(loc.getLatitude()) < Double.MIN_VALUE
                || Math.abs(loc.getLongitude()) < Double.MIN_VALUE) {
            return false;
        }
        return true;
    }

    public void logLocation(BDLocation location) {
        StringBuffer sb = new StringBuffer(256);
        sb.append("time : ");
        sb.append(location.getTime());
        sb.append("\nerror code : ");
        sb.append(location.getLocType());
        sb.append("\nlatitude : ");
        sb.append(location.getLatitude());
        sb.append("\nlontitude : ");
        sb.append(location.getLongitude());
        sb.append("\nradius : ");
        sb.append(location.getRadius());
        if (location.getLocType() == BDLocation.TypeGpsLocation){// GPS定位结果
            sb.append("\nspeed : ");
            sb.append(location.getSpeed());// 单位：公里每小时
            sb.append("\nsatellite : ");
            sb.append(location.getSatelliteNumber());
            sb.append("\nheight : ");
            sb.append(location.getAltitude());// 单位：米
            sb.append("\ndirection : ");
            sb.append(location.getDirection());
            sb.append("\naddr : ");
            sb.append(location.getAddrStr());
            sb.append("\ndescribe : ");
            sb.append("gps定位成功");

        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){// 网络定位结果
            sb.append("\naddr : ");
            sb.append(location.getAddrStr());
            //运营商信息
            sb.append("\noperationers : ");
            sb.append(location.getOperators());
            sb.append("\ndescribe : ");
            sb.append("网络定位成功");
        } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
            sb.append("\ndescribe : ");
            sb.append("离线定位成功，离线定位结果也是有效的");
        } else if (location.getLocType() == BDLocation.TypeServerError) {
            sb.append("\ndescribe : ");
            sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
        } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
            sb.append("\ndescribe : ");
            sb.append("网络不同导致定位失败，请检查网络是否通畅");
        } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
            sb.append("\ndescribe : ");
            sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
        }
        sb.append("\nlocationdescribe : ");// 位置语义化信息
        sb.append(location.getLocationDescribe());
        List<Poi> list = location.getPoiList();// POI信息
        if (list != null) {
            sb.append("\npoilist size = : ");
            sb.append(list.size());
            for (Poi p : list) {
                sb.append("\npoi= : ");
                sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
            }
        }
        Log.v("location", sb.toString());
        android.util.Log.i("BaiduLocationApiDem", sb.toString());
    }
}

