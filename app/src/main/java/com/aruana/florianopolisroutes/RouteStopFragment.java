package com.aruana.florianopolisroutes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.aruana.florianopolisroutes.api.RoutesAPI;
import com.aruana.florianopolisroutes.api.StopCallback;
import com.aruana.florianopolisroutes.beans.Stop;

import java.util.List;


public class RouteStopFragment extends ListFragment implements StopCallback {

    private Integer routeId;
    private RoutesAPI routesAPI;
    private View progressView;

    public static RouteStopFragment newInstance(Integer routeId) {
        RouteStopFragment fragment = new RouteStopFragment();
        Bundle args = new Bundle();
        args.putInt("routeId", routeId);
        fragment.setArguments(args);
        return fragment;
    }

    public RouteStopFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            routeId = getArguments().getInt("routeId");
        }

        routesAPI = new RoutesAPI(getActivity());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        progressView = getView().findViewById(R.id.loadingPanel);
        getListView().setVisibility(View.GONE);
        progressView.setVisibility(View.VISIBLE);

        if (routeId != null) {
            routesAPI.findStops(routeId,this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_route_stop, container, false);
    }

    @Override
    public void stopCallback(List<Stop> stops) {
        if (stops != null) {
            String[] values = new String[stops.size()];

            int i = 0;
            for (Stop stop : stops) {
                values[i++] = stop.getName();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, values);
            setListAdapter(adapter);

            getListView().setVisibility(View.VISIBLE);
            progressView.setVisibility(View.GONE);
        }
    }
}
