package com.pradyanti_1313617004.beautyholic.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pradyanti_1313617004.beautyholic.Model.ProductType;
import com.pradyanti_1313617004.beautyholic.R;

import java.util.ArrayList;

public class ProductTypeAdapter extends RecyclerView.Adapter<ProductTypeAdapter.ListViewHolder> {

    private ArrayList<ProductType> productTypeList;

    private OnItemClickCallBack onItemClickCallBack;


    public void setOnItemClickCallBack(OnItemClickCallBack onItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack;
    }

    public ProductTypeAdapter(ArrayList<ProductType> list) {
        this.productTypeList = list;
    }

    @Override
    public ProductTypeAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_type, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductTypeAdapter.ListViewHolder holder, int position) {
        ProductType productType = productTypeList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(productType.getPhotoPoster())
                .into(holder.imageButton);
        holder.tvName.setText(productType.getName());

        //OnClick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallBack.onItemClicked(productTypeList.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return productTypeList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        ImageButton imageButton;
        TextView tvName;

        public ListViewHolder(View itemView) {
            super(itemView);

            imageButton = itemView.findViewById(R.id.imageButton_product_type);
            tvName = itemView.findViewById(R.id.tv_Product_type_name);
        }
    }

    public interface OnItemClickCallBack {
        void onItemClicked(ProductType productType);
    }
}
