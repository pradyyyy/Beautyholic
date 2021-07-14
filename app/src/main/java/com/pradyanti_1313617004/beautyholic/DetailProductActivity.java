package com.pradyanti_1313617004.beautyholic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pradyanti_1313617004.beautyholic.Adapter.ProductColorsAdapter;
import com.pradyanti_1313617004.beautyholic.Model.Product;
import com.pradyanti_1313617004.beautyholic.Model.ProductColors;
import com.pradyanti_1313617004.beautyholic.Model.ProductType;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DetailProductActivity extends AppCompatActivity {

    TextView tv_detail_name, tv_detail_price, tv_detail_rating, tv_detail_description, tv_detail_tags, tv_detail_category, title;
    ImageView image_detail_product, back_button;;
    RatingBar ratingBarProduct;
    private RecyclerView recyclerView;
    private ProductColorsAdapter productColorsAdapter;
    public static DetailProductActivity detailProductActivity;
    private static final String TAG = "DetailProductActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        Product product = getIntent().getParcelableExtra("ProductDataList");

        title = findViewById(R.id.title);
        back_button = findViewById(R.id.back_button);

        tv_detail_category = findViewById(R.id.tv_detail_category);
        tv_detail_name = findViewById(R.id.tv_detail_name);
        tv_detail_price = findViewById(R.id.tv_detail_price);
        tv_detail_rating = findViewById(R.id.tv_detail_rating);
        ratingBarProduct = findViewById(R.id.ratingBar_detail_product);
        tv_detail_description = findViewById(R.id.tv_detail_description);
        tv_detail_tags = findViewById(R.id.tv_detail_taglist);
        image_detail_product = findViewById(R.id.image_detail_product);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String name_product = product.getName();
        String update_name = name_product.trim();
        tv_detail_name.setText(update_name);

        String brand = product.getBrand();
        if (brand != null ) {
            brand = brand.toUpperCase();
        } else {
            brand = "N/A";
        }
        title.setText(brand);

        String category = product.getCategory();
        Log.d(TAG, "onCreate: Category " + category);
        if (category == null) {
            category = "-";
        } else if (category.equals("")) {
            category = "-";
        } else {
            category = category.replaceAll("_", " ");
            category = category.substring(0,1).toUpperCase() + category.substring(1);
        }
        tv_detail_category.setText(category);

        String price_sign = product.getPrice_sign();
        if (price_sign == null) {
            price_sign = "$";
        }
        tv_detail_price.setText(price_sign + Double.toString(product.getPrice()));

        float rating = product.getRating();
        DecimalFormat df = new DecimalFormat("#.##");
        String update_rating = df.format(rating);
        tv_detail_rating.setText("(" + update_rating + ")");

        ratingBarProduct.setRating(product.getRating());

        String description = product.getDescription();
        if (description == null) {
            description = "-";
        } else if (description.equals("")) {
            description = "-";
        } else {
            description = description.trim().replaceAll("\\s{2,}", " ");
        }
        tv_detail_description.setText(description);

        List<String> taglist = product.getTagList();
        String update_taglist = "";
        if (taglist.size() != 0) {
            update_taglist = TextUtils.join(", ", taglist);
        } else {
            update_taglist = "-";
        }
//        String update_taglist = "";
//        for (int i = 0; i < taglist.size(); i++) {
//            if ( i == taglist.size() - 1) {
//                update_taglist += taglist.get(i);
//            }
//            else {
//                update_taglist += taglist.get(i) + ", ";
//            }
//        }

        tv_detail_tags.setText(update_taglist);

        Glide.with(DetailProductActivity.this)
                .load(product.getImage_link())
                .timeout(30000)
                .placeholder(R.drawable.load)
                .error(R.drawable.ic_baseline_image_not_supported_24)
                .into(image_detail_product);

        recyclerView = findViewById(R.id.rv_detail_product_color);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        detailProductActivity = this;

        ArrayList<ProductColors> productColorsArrayList = product.getProduct_colors();
        productColorsAdapter = new ProductColorsAdapter(productColorsArrayList);
        productColorsAdapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(productColorsAdapter.getItemCount());
        recyclerView.setAdapter(productColorsAdapter);
    }
}