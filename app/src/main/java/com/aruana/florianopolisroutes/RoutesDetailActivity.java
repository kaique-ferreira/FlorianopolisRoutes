package com.aruana.florianopolisroutes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aruana.florianopolisroutes.adapter.RoutesDetailFragmentAdapter;
import com.aruana.florianopolisroutes.beans.Route;
import com.aruana.florianopolisroutes.enums.CalendarEnum;

import java.util.ArrayList;
import java.util.List;


public class RoutesDetailActivity extends FragmentActivity {

    private RoutesDetailFragmentAdapter pageAdapter;
    private Route route;
    private TextView routeName;
    private Button buttonBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_detail);

        route = (Route)getIntent().getSerializableExtra("route");

        if (route != null) {
            routeName = (TextView) findViewById(R.id.routeName);
            routeName.setText(route.getShortName()+" - "+route.getLongName());
        }

        List<Fragment> fragments = getFragments();

        pageAdapter = new RoutesDetailFragmentAdapter(getSupportFragmentManager(),fragments,this);

        ViewPager pager = (ViewPager)findViewById(R.id.pager);

        pager.setAdapter(pageAdapter);

        buttonBack = (Button)findViewById(R.id.buttonBackDetail);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onBackPressed();
            }
        });

    }

    private List<Fragment> getFragments() {
        if (route != null) {
            List<Fragment> fList = new ArrayList<Fragment>();


            fList.add(RouteStopFragment.newInstance(route.getId()));
            fList.add(RouteDepartureFragment.newInstance(route.getId(), CalendarEnum.WEEKDAY));
            fList.add(RouteDepartureFragment.newInstance(route.getId(), CalendarEnum.SATURDAY));
            fList.add(RouteDepartureFragment.newInstance(route.getId(), CalendarEnum.SUNDAY));

            return fList;
        }

        return null;
    }

}
