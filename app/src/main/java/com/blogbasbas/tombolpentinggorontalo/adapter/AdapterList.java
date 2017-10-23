package com.blogbasbas.tombolpentinggorontalo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.blogbasbas.tombolpentinggorontalo.R;

/**
 * Created by Server on 04/10/2017.
 */

public class AdapterList extends BaseAdapter {

    Context context;
    String []namaTempat;

    public AdapterList(Context context, String[] namaTempat) {
        this.context = context;
        this.namaTempat = namaTempat;

}



    @Override
    public int getCount() {
        return namaTempat.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.custombutton , null);

        TextView textView= (TextView)view.findViewById(R.id.tempat);
        textView.setText(namaTempat[i]);

        return view;
    }
}
