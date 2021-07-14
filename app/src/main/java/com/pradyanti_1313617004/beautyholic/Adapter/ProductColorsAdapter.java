package com.pradyanti_1313617004.beautyholic.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pradyanti_1313617004.beautyholic.Model.ProductColors;
import com.pradyanti_1313617004.beautyholic.R;

import java.util.ArrayList;

public class ProductColorsAdapter extends RecyclerView.Adapter<ProductColorsAdapter.ListViewHolder> {

    ArrayList<ProductColors> mProductColorsArrayList;

    public ProductColorsAdapter(ArrayList<ProductColors> productColorsArrayList) {
        mProductColorsArrayList = productColorsArrayList;
    }

    @Override
    public ProductColorsAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_color, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductColorsAdapter.ListViewHolder holder, int position) {
        String circle_color = mProductColorsArrayList.get(position).getHex_value();
        String[] colors = circle_color.split(",");
        String color = colors[0];
        int circleColor = Color.parseColor(color);
        holder.circleColors.setBackgroundColor(circleColor);

        holder.tvColorName.setText(mProductColorsArrayList.get(position).getColour_name());

    }

    @Override
    public int getItemCount() {
        return mProductColorsArrayList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tvColorName, circleColors;;

        public ListViewHolder(View itemView) {
            super(itemView);

            circleColors = itemView.findViewById(R.id.circle_colors);
            tvColorName = itemView.findViewById(R.id.tv_color_name);
        }
    }
}
