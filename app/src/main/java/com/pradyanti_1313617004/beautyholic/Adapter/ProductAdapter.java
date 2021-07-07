package com.pradyanti_1313617004.beautyholic.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pradyanti_1313617004.beautyholic.Model.Product;
import com.pradyanti_1313617004.beautyholic.Model.ProductType;
import com.pradyanti_1313617004.beautyholic.R;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ListViewHolder> {

    List<Product> mProductList;

    private OnItemClickCallBack onItemClickCallBack;

    public ProductAdapter(List<Product> productList) {
        mProductList = productList;
    }

    public void setOnItemClickCallBack(OnItemClickCallBack onItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack;
    }

    @Override
    public ProductAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ListViewHolder holder, int position) {
        holder.tvBrand.setText(mProductList.get(position).getBrand());
        holder.tvName.setText(mProductList.get(position).getName());
        holder.tvCategory.setText(mProductList.get(position).getCategory());
        holder.tvPrice.setText("$" + Double.toString(mProductList.get(position).getPrice()));

        Glide.with(holder.itemView.getContext())
                .load(mProductList.get(position).getImage_link())
                .placeholder(R.drawable.load)
                .error(R.drawable.ic_baseline_image_not_supported_24)
                .into(holder.imageButton);

        //OnClick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallBack.onItemClicked(mProductList.get(holder.getAdapterPosition()));
            }
        });


//        List<String> taglist = mProductList.get(position).getTagList();
//        String tag_list = "";
//
//        for (int i = 0; i < taglist.size(); i++ ) {
////            tag_list += taglist.get(i) + ", ";
//
//            if (i == taglist.size() - 1) {
//                tag_list += taglist.get(i);
//            } else {
//                tag_list += taglist.get(i) + ", ";
//            }
//        }
//
//        holder.tvTagList.setText(tag_list);

    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        public TextView tvBrand, tvName, tvCategory, tvPrice;
        public ImageButton imageButton;

        public ListViewHolder(View itemView) {
            super(itemView);

            tvBrand = itemView.findViewById(R.id.tvbrand);
            tvName = itemView.findViewById(R.id.tvname);
            tvCategory = itemView.findViewById(R.id.tvCategoryName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imageButton = itemView.findViewById(R.id.imageButton_product);
//            tvTagList = itemView.findViewById(R.id.tag_list);
        }
    }

    public interface OnItemClickCallBack {
        void onItemClicked(Product productData);
    }
}
