package com.guy.class22a_and_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Fragment;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Fragment_List fragmentList;
    private Fragment_Map fragmentMap;

    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        info = findViewById(R.id.info);

        fragmentList = new Fragment_List();
        fragmentList.setActivity(this);
        fragmentList.setCallBackList(callBackList);
        getSupportFragmentManager().beginTransaction().add(R.id.frame1, fragmentList).commit();


        fragmentMap = new Fragment_Map();
        fragmentMap.setActivity(this);
        fragmentMap.setCallBack_map(callBack_map);
        getSupportFragmentManager().beginTransaction().add(R.id.frame2, fragmentMap).commit();

    }

    private String locateCity(double lat, double lon) {
        return "Tel-Aviv";
    }

    CallBack_Map callBack_map = new CallBack_Map() {
        @Override
        public void mapClicked(double lat, double lon) {
            String city = locateCity(lat, lon);
            fragmentList.setTitle(city);
        }
    };

    CallBack_List callBackList = new CallBack_List() {
        @Override
        public void setMainTitle(String str) {
            info.setText(str);
        }

        @Override
        public void setTitleColor(int color) {
            info.setTextColor(color);
        }

        @Override
        public void rowSelected(String name) {
            fragmentMap.zoomOnMap(2.0, 6.2);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        fragmentList.setTitle("iOS");
    }

}