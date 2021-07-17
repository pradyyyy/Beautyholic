package com.pradyanti_1313617004.beautyholic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pradyanti_1313617004.beautyholic.Adapter.ProductColorsAdapter;
import com.pradyanti_1313617004.beautyholic.Model.Product;
import com.pradyanti_1313617004.beautyholic.Model.ProductColors;

import org.apache.commons.lang3.text.WordUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DetailProductActivity extends AppCompatActivity {

    TextView tv_detail_name, tv_detail_price, tv_detail_rating, tv_detail_description, tv_detail_tags, tv_detail_category;
    ImageView image_detail_product;
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

        tv_detail_category = findViewById(R.id.tv_detail_category);
        tv_detail_name = findViewById(R.id.tv_detail_name);
        tv_detail_price = findViewById(R.id.tv_detail_price);
        tv_detail_rating = findViewById(R.id.tv_detail_rating);
        ratingBarProduct = findViewById(R.id.ratingBar_detail_product);
        tv_detail_description = findViewById(R.id.tv_detail_description);
        tv_detail_tags = findViewById(R.id.tv_detail_taglist);
        image_detail_product = findViewById(R.id.image_detail_product);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //name product
        String name_product = product.getName().toLowerCase();
        String update_name = convertStringName(name_product).trim();
        tv_detail_name.setText(update_name);

        //brand
        String brand = product.getBrand();
        if (brand != null ) {
            brand = brand.toUpperCase();
        } else {
            brand = "N/A";
        }
        actionBar.setTitle(brand);

        //category
        String category = product.getCategory();
        if (category == null) {
            category = "-";
        } else if (category.equals("")) {
            category = "-";
        } else {
            category = category.replaceAll("_", " ");
            category = category.substring(0,1).toUpperCase() + category.substring(1);
        }
        tv_detail_category.setText(category);

        //price
        String currency = product.getCurrency();
        String price = "";
        double price_fromApi = product.getPrice();

        //convert currency
        Locale myIndonesianLocale = new Locale("in", "ID");
        NumberFormat kursIndonesia = NumberFormat.getCurrencyInstance(myIndonesianLocale);

        int pembulatan = 0;

        if (price_fromApi == 0) {
            price = "Estimated price is not available";
        } else if (currency == null) {
            price_fromApi = price_fromApi * 14491;
            pembulatan = (int)Math.ceil(price_fromApi);
            price = kursIndonesia.format(pembulatan) + " (Just an estimate price)";
        } else if (currency.equals("USD")) {
            price_fromApi = price_fromApi * 14491;
            pembulatan = (int)Math.ceil(price_fromApi);
            price = kursIndonesia.format(pembulatan) + " (Just an estimate price)";
        } else if (currency.equals("GBP")) {
            price_fromApi = price_fromApi * 20019;
            pembulatan = (int)Math.ceil(price_fromApi);
            price = kursIndonesia.format(pembulatan) + " (Just an estimate price)";
        } else if (currency.equals("CAD")) {
            price_fromApi = price_fromApi * 11518;
            pembulatan = (int)Math.ceil(price_fromApi);
            price = kursIndonesia.format(pembulatan) + " (Just an estimate price)";
        }
        tv_detail_price.setText(price);

        //rating
        float rating = product.getRating();
        DecimalFormat df = new DecimalFormat("#.##");
        String update_rating = df.format(rating);
        tv_detail_rating.setText("(" + update_rating + ")");

        //rating bar
        ratingBarProduct.setRating(product.getRating());

        //description
        String description = product.getDescription();
        if (description == null) {
            description = "-";
        } else if (description.equals("")) {
            description = "-";
        } else {
            description = String.valueOf(Html.fromHtml(description)).replace("\n", "");
        }
        tv_detail_description.setText(description);

        //taglist
        List<String> taglist = product.getTagList();
        String update_taglist = "";
        if (taglist.size() != 0) {
            update_taglist = TextUtils.join(", ", taglist);
        } else {
            update_taglist = "-";
        }
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

    //convert name produk kapital
    public String convertStringName(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        return WordUtils.capitalize(name);
    }

    //back button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}