package com.example.hibsa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Car> cars;

    public MyAdapter(Context context, int layout, List<Car> cars) {
        this.context = context;
        this.layout = layout;
        this.cars = cars;
    }

    @Override
    public int getCount() {
        return this.cars.size();
    }

    @Override
    public Object getItem(int i) {
        return this.cars.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            view = layoutInflater.inflate(this.layout, null);

            holder = new ViewHolder();
            holder.nameTextView = view.findViewById(R.id.textView);
            holder.imageView = view.findViewById(R.id.imageView);
            view.setTag(holder);
        } else  {
            holder = (ViewHolder) view.getTag();
        }

        String currentName = cars.get(i).getBrand() + " " + cars.get(i).getModel() + " " + cars.get(i).getYear();
        holder.nameTextView.setText(currentName);

        String image = "drawable/" + cars.get(i).getBrand().toLowerCase() + cars.get(i).getModel().toLowerCase() + cars.get(i).getYear();
        // int imageId = getResources().getIdentifier(image, "drawable", getPackageName());

        return view;
    }

    static class ViewHolder {
        private TextView nameTextView;
        private ImageView imageView;
    }
}
