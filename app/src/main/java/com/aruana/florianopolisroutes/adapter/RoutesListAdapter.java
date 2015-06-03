package com.aruana.florianopolisroutes.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aruana.florianopolisroutes.R;
import com.aruana.florianopolisroutes.RoutesDetailActivity;
import com.aruana.florianopolisroutes.beans.Route;

import java.util.List;

/**
 * Created by aruana on 31/05/15.
 */
public class RoutesListAdapter extends BaseAdapter {

    private Context context;
    private List<Route> routes;

    public RoutesListAdapter(Context context, List<Route> routes) {
        this.context = context;
        this.routes = routes;
    }

    @Override
    public int getCount() {
        if (this.routes != null) {
            return this.routes.size();
        }

        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView =  inflater.inflate(R.layout.list_routes_row,null);

        if (routes != null && !routes.isEmpty()) {
            final Route route = routes.get(position);
            if (route != null) {
                TextView textViewLongName = (TextView) rowView.findViewById(R.id.rowLongName);
                TextView textViewShortName = (TextView) rowView.findViewById(R.id.rowShortName);

                textViewLongName.setText(route.getLongName());
                textViewShortName.setText(""+route.getShortName());

                if (position % 2 == 0) {
                    rowView.setBackgroundColor(Color.LTGRAY);
                }

                rowView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, RoutesDetailActivity.class);
                        intent.putExtra("route",route);
                        context.startActivity(intent);
                    }
                });
            }
        }

        return rowView;
    }

}
