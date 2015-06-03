package com.aruana.florianopolisroutes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.aruana.florianopolisroutes.adapter.RoutesListAdapter;
import com.aruana.florianopolisroutes.api.RouteByNameCallback;
import com.aruana.florianopolisroutes.api.RoutesAPI;
import com.aruana.florianopolisroutes.beans.Route;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by aruana on 31/05/15.
 */
public class RoutesListActivity extends Activity implements RouteByNameCallback {

    private ListView listViewRoutes;
    private Button searchButton;
    private Button buttonMap;
    private EditText edtStopName;
    private View progressView;


    private RoutesListAdapter listRoutesAdapter;
    private List<Route> routes;

    private RoutesListActivity thisActivity;

    private RoutesAPI routesAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_routes);

        this.thisActivity = this;
        routesAPI = new RoutesAPI(this);

        progressView = findViewById(R.id.loadingPanel);

        routes = new ArrayList<Route>();

        listRoutesAdapter = new RoutesListAdapter(this,routes);

        listViewRoutes = (ListView)findViewById(R.id.listViewRoutes);
        if (listViewRoutes != null) {
           listViewRoutes.setAdapter(listRoutesAdapter);
        }

        edtStopName = (EditText)findViewById(R.id.editTextStopName);

        searchButton = (Button)findViewById(R.id.buttonOk);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryStopName();
            }
        });

        buttonMap = (Button)findViewById(R.id.buttonMap);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thisActivity, MapsActivity.class);
                thisActivity.startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String address = intent.getStringExtra("address");
        if (address != null && !address.isEmpty()) {
            edtStopName.setText(address);
            searchButton.callOnClick();
        }
    }

    private void queryStopName() {
        hideKeyboard();

        if (edtStopName != null) {
            String stopNameQuery = edtStopName.getText().toString();

            if (stopNameQuery != null && !stopNameQuery.isEmpty()) {
                progressView.setVisibility(View.VISIBLE);
                listViewRoutes.setVisibility(View.GONE);
                routesAPI.findRoutes(stopNameQuery,thisActivity);
            }
        }
    }


    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edtStopName.getWindowToken(), 0);
    }

    @Override
    public void routeStopNameCallback(List<Route> routesResult) {
        if (routesResult != null) {
            routes.clear();
            routes.addAll(routesResult);
            listRoutesAdapter.notifyDataSetChanged();
            progressView.setVisibility(View.GONE);
            listViewRoutes.setVisibility(View.VISIBLE);
            listViewRoutes.setVisibility(View.VISIBLE);
        }
    }

}
