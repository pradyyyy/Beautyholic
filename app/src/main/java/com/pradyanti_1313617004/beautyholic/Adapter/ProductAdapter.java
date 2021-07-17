package com.pradyanti_1313617004.beautyholic.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pradyanti_1313617004.beautyholic.Model.Product;
import com.pradyanti_1313617004.beautyholic.R;

import org.apache.commons.lang3.text.WordUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ListViewHolder> {

    List<Product> mProductList;
    private static final String TAG = "ProductAdapter";

    private OnItemClickCallBack onItemClickCallBack;

    public ProductAdapter(List<Product> productList) {
        this.mProductList = productList;
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

    public String convertStringName(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        return WordUtils.capitalize(name);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ListViewHolder holder, int position) {
        String name_product = mProductList.get(position).getName().toLowerCase();
        String update_name = convertStringName(name_product).trim();
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

        String currency = mProductList.get(position).getCurrency();
        String price = "";
        double price_fromApi = mProductList.get(position).getPrice();

        Locale myIndonesianLocale = new Locale("in", "ID");
        NumberFormat kursIndonesia = NumberFormat.getCurrencyInstance(myIndonesianLocale);

        int pembulatan = 0;

        if (price_fromApi == 0) {
            price = "Estimated price is not available";
        } else if (currency == null) {
            price_fromApi = price_fromApi * 14491;
            pembulatan = (int)Math.ceil(price_fromApi);
            price = kursIndonesia.format(pembulatan);
        } else if (currency.equals("USD")) {
            price_fromApi = price_fromApi * 14491;
            pembulatan = (int)Math.ceil(price_fromApi);
            price = kursIndonesia.format(pembulatan);
        } else if (currency.equals("GBP")) {
            price_fromApi = price_fromApi * 20019;
            pembulatan = (int)Math.ceil(price_fromApi);
            price = kursIndonesia.format(pembulatan);
        } else if (currency.equals("CAD")) {
            price_fromApi = price_fromApi * 11518;
            pembulatan = (int)Math.ceil(price_fromApi);
            price = kursIndonesia.format(pembulatan);
        }
        holder.tvPrice.setText(price);

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
        }
    }

    public interface OnItemClickCallBack {
        void onItemClicked(Product productData);
    }
}
