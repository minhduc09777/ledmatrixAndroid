package com.nmd.minhduc09777.datn_complete;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Handicapper extends BaseAdapter {
    private Context context;
    private int layout;
    private List<HinhAnh> hinhanhList;

    public Handicapper(Context context, int layout, List<HinhAnh> hinhanhList) {
        this.context = context;
        this.layout = layout;
        this.hinhanhList = hinhanhList;
    }

    @Override
    public int getCount() {
        return hinhanhList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class viewholder{
        ImageView imghinh;
        TextView _text_ds;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewholder holder;
        if(convertView == null){
            holder = new viewholder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,null);
            holder.imghinh=(ImageView) convertView.findViewById(R.id.anh_ds);
            holder._text_ds=(TextView) convertView.findViewById(R.id.text_ds);
            convertView.setTag(holder);
        }
        else{
            holder=(viewholder) convertView.getTag();
        }
        HinhAnh _hinhanh=hinhanhList.get(position);
        holder.imghinh.setImageResource(_hinhanh.getHinh());
        holder._text_ds.setText(_hinhanh.getTenhinh());
        return convertView;
    }
}
