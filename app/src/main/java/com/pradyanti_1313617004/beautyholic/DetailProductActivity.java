package com.pradyanti_1313617004.beautyholic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pradyanti_1313617004.beautyholic.Adapter.ProductColorsAdapter;
import com.pradyanti_1313617004.beautyholic.Model.Product;
import com.pradyanti_1313617004.beautyholic.Model.ProductColors;

import java.util.ArrayList;
import java.util.List;

public class DetailProductActivity extends AppCompatActivity {

    TextView tv_detail_brand, tv_detail_name, tv_detail_price, tv_detail_rating, tv_detail_description, tv_detail_tags;
    ImageView image_detail_product;
    private RecyclerView recyclerView;
    private ProductColorsAdapter productColorsAdapter;
    public static DetailProductActivity detailProductActivity;
    private static final String TAG = "DetailProductActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        Product product = getIntent().getParcelableExtra("ProductDataList");

        tv_detail_brand = findViewById(R.id.tv_detail_brand);
        tv_detail_name = findViewById(R.id.tv_detail_name);
        tv_detail_price = findViewById(R.id.tv_detail_price);
        tv_detail_rating = findViewById(R.id.tv_detail_rating);
        tv_detail_description = findViewById(R.id.tv_detail_description);
        tv_detail_tags = findViewById(R.id.tv_detail_taglist);

        tv_detail_brand.setText(product.getBrand());
        tv_detail_name.setText(product.getName());
        tv_detail_price.setText(Double.toString(product.getPrice()));
        tv_detail_rating.setText(Double.toString(product.getRating()));
        tv_detail_description.setText(product.getDescription());

        List<String> taglist = product.getTagList();
        String tag_list = "";
        for (int i = 0; i < taglist.size(); i++) {

            if ( i == taglist.size() - 1) {
                tag_list += taglist.get(i);
            } else {
                tag_list += taglist.get(i) + ", ";
            }
        }

        tv_detail_tags.setText(tag_list);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.error);

//        Glide.with(DetailProductActivity.this)
//                .load(product.getImage_link())
//                .apply(options)
//                .into(image_detail_product);

        recyclerView = findViewById(R.id.rv_detail_product);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        detailProductActivity = this;

        ArrayList<ProductColors> productColorsArrayList = product.getProduct_colors();
        productColorsAdapter = new ProductColorsAdapter(productColorsArrayList);
        recyclerView.setAdapter(productColorsAdapter);




    }
}