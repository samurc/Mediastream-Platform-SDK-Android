package com.example.mediastreamplatformsdkandroidsamples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<ItemList> {
    private Context mContext;
    private int mResource;

    public ItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ItemList> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent, false);
        ImageView imageView = convertView.findViewById(R.id.rowImage);
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtDescription = convertView.findViewById(R.id.txtDescription);

        imageView.setImageResource(getItem(position).getImage());
        txtName.setText(getItem(position).getTitle());
        txtDescription.setText(getItem(position).getDescription());

        return convertView;
    }
}
