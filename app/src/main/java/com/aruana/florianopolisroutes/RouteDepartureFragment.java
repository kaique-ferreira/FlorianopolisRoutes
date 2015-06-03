package com.aruana.florianopolisroutes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.aruana.florianopolisroutes.api.DepartureCallback;
import com.aruana.florianopolisroutes.api.RoutesAPI;
import com.aruana.florianopolisroutes.beans.Departure;
import com.aruana.florianopolisroutes.enums.CalendarEnum;

import java.util.List;


public class RouteDepartureFragment extends ListFragment implements DepartureCallback {

    private Integer routeId;
    private RoutesAPI routesAPI;
    private CalendarEnum calendar;
    private View progressView;

    public static RouteDepartureFragment newInstance(Integer routeId, CalendarEnum calendar) {
        RouteDepartureFragment fragment = new RouteDepartureFragment();
        Bundle args = new Bundle();
        args.putInt("routeId", routeId);
        args.putSerializable("calendar",calendar);
        fragment.setArguments(args);
        return fragment;
    }

    public RouteDepartureFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            routeId = getArguments().getInt("routeId");
            calendar = (CalendarEnum)getArguments().get("calendar");
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
            routesAPI.findDepartures(routeId, this, calendar);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_route_departure, container, false);
    }


    @Override
    public void routeDepartureCallback(List<Departure> departures) {
        if (departures != null) {
            String[] values = new String[departures.size()];

            int i = 0;
            for (Departure departure : departures) {
                values[i++] = departure.getTime();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, values);
            setListAdapter(adapter);

            getListView().setVisibility(View.VISIBLE);
            progressView.setVisibility(View.GONE);
        }
    }
}
