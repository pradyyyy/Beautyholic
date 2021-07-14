package com.pradyanti_1313617004.beautyholic.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pradyanti_1313617004.beautyholic.Model.Product;
import com.pradyanti_1313617004.beautyholic.R;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ListViewHolder> {

    List<Product> mProductList;
    private static final String TAG = "ProductAdapter";

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
        String name_product = mProductList.get(position).getName();
        String update_name = name_product.trim();
        holder.tvName.setText(update_name);

        String brand = mProductList.get(position).getBrand();
        if (brand != null ) {
            brand = brand.toUpperCase();
        } else {
            brand = "N/A";
        }
        holder.tvBrand.setText(brand);

        String category = mProductList.get(position).getCategory();
        if (category == null) {
            category = "-";
        } else if (category.equals("")) {
            category = "-";
        } else {
            category = category.replaceAll("_", " ");
            category = category.substring(0,1).toUpperCase() + category.substring(1);
        }
        holder.tvCategory.setText(category);

        float rating = mProductList.get(position).getRating();
        DecimalFormat df = new DecimalFormat("#.##");
        String update_rating = df.format(rating);
        holder.tvRating.setText("(" + update_rating + ")");

        holder.ratingBarproduct.setRating(mProductList.get(position).getRating());

        String price_sign = mProductList.get(position).getPrice_sign();
        if (price_sign == null) {
            price_sign = "$";
        }
        holder.tvPrice.setText(price_sign  + Double.toString(mProductList.get(position).getPrice()));

        Glide.with(holder.itemView.getContext())
                .load(mProductList.get(position).getImage_link())
                .error(R.drawable.ic_baseline_image_not_supported_24)
                .into(holder.imageView);

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

        public TextView tvBrand, tvName, tvPrice, tvCategory, tvRating;
        public ImageView imageView;
        RatingBar ratingBarproduct;

        public ListViewHolder(View itemView) {
            super(itemView);

            tvBrand = itemView.findViewById(R.id.tvbrand);
            tvName = itemView.findViewById(R.id.tvname);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvCategory = itemView.findViewById(R.id.tvCategoryProduct);
            tvRating = itemView.findViewById(R.id.tv_rating);
            imageView = itemView.findViewById(R.id.imageView_product);
            ratingBarproduct = itemView.findViewById(R.id.ratingBar_product);
//            tvTagList = itemView.findViewById(R.id.tag_list);
        }
    }

    public interface OnItemClickCallBack {
        void onItemClicked(Product productData);
    }
}
