package com.example.ghtk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ghtk.R;
import com.example.ghtk.models.PackageInfo;

import java.util.List;

public class PackageItemAdapter extends BaseAdapter {
    private List<PackageInfo> packageList;
    private Context context;

    public PackageItemAdapter(List<PackageInfo> packageList, Context context) {
        this.packageList = packageList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return packageList.size();
    }

    @Override
    public Object getItem(int position) {
        return packageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.package_item, null);
        TextView txt_package_name = convertView.findViewById(R.id.txt_package_name);
        TextView txt_package_weight = convertView.findViewById(R.id.txt_package_weigh);
        //
        PackageInfo item = packageList.get(position);
        //
        //
        txt_package_name.setText(item.getTensp());
        txt_package_weight.setText(String.format("%.1fkg x %d", item.getCannang(), item.getSoluong()));
        return convertView;
    }
}
