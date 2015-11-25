package com.remedialguns.smartourist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<Place> {

    MyAdapter(Context context, Place[] places){
        super(context, R.layout.visual_place, places);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater MyInflater = LayoutInflater.from(getContext());
        View placeView = MyInflater.inflate(R.layout.visual_place, parent, false);

        Place singlePlaceItem=getItem(position);
        TextView name=(TextView) placeView.findViewById(R.id.name);
        ImageView icon =(ImageView) placeView.findViewById(R.id.icon);
        TextView description = (TextView) placeView.findViewById(R.id.description);

        name.setText(singlePlaceItem.getName().toString());
        icon.setImageResource(R.mipmap.ic_launcher);
        description.setText("Cost "+singlePlaceItem.getCost()+", distance "+singlePlaceItem.getDistance()+", rate "+singlePlaceItem.getRate()+" *");
        return placeView;

    }
}
