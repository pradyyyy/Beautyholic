package com.pradyanti_1313617004.beautyholic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pradyanti_1313617004.beautyholic.Adapter.ProductAdapter;
import com.pradyanti_1313617004.beautyholic.Model.Product;
import com.pradyanti_1313617004.beautyholic.Retrofit.ApiClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    public static MainActivity mainActivity;
    private static final String TAG = "MainActivity";
    TextView tvDescriptionProduct;
    LinearLayout error_layout, detail_layout;
    private ProductAdapter productAdapter;

    List<Product> productArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshLayout = findViewById(R.id.swipe_refresh);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);

        tvDescriptionProduct = findViewById(R.id.detail_product_type);
        error_layout = findViewById(R.id.eror_layout);
        detail_layout = findViewById(R.id.detail_layout);

        recyclerView = findViewById(R.id.rv_product);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        productAdapter = new ProductAdapter(productArrayList);
        recyclerView.setAdapter(productAdapter);

        mainActivity = this;
        receivedData();
    }

    private void receivedData() {
        detail_layout.setVisibility(View.GONE);

        if (getIntent().getExtras() != null) {
            String product_type = getIntent().getExtras().getString("product_type");
            tvDescriptionProduct.setText(getIntent().getExtras().getString("description_product_type"));
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(product_type);
            actionBar.setDisplayHomeAsUpEnabled(true);

            error_layout.setVisibility(View.GONE);
            getProductFromApi(product_type);
        }
    }

    private void getProductFromApi(String product_type) {
        ApiClient.apiInterface().getProductData(product_type)
                .enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        if (!response.isSuccessful()) {
                            Log.d(TAG, "onResponse: Gagal respon " + response.code());
                            Log.d(TAG, "onResponse: Message " + response.message());
                            return;
                        }

                        Log.d(TAG, "onResponse: Sukses respon " + response.code());

                        productArrayList = response.body();
                        refreshLayout.setRefreshing(false);
                        detail_layout.setVisibility(View.VISIBLE);
                        error_layout.setVisibility(View.GONE);

                        productAdapter = new ProductAdapter(productArrayList);
                        productAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(productAdapter);
                        //recyclerView.smoothScrollToPosition(productAdapter.getItemCount() - 1);

                        //onClick
                        productAdapter.setOnItemClickCallBack(new ProductAdapter.OnItemClickCallBack() {
                            @Override
                            public void onItemClicked(Product productData) {
                                showSelectedProduct(productData);
                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        refreshLayout.setRefreshing(false);
                        Log.d(TAG, "onFailure: " + t.getMessage());
                        error_layout.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void showSelectedProduct(Product productData) {
        Intent kirimData = new Intent(MainActivity.this, DetailProductActivity.class);
        kirimData.putExtra("ProductDataList", productData);
        startActivity(kirimData);
    }

    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh: Sukses masuk refresh");
        receivedData();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.action_sort_brand:
                Collections.sort(productArrayList, Product.brandComparator);;
                productAdapter.notifyDataSetChanged();
                return true;

            case R.id.action_sort_category:
                Collections.sort(productArrayList, Product.categoryCamparator);
                productAdapter.notifyDataSetChanged();
                return true;

            case R.id.action_sort_price_low:
                Collections.sort(productArrayList, Product.priceLowtoHighComparator);
                productAdapter.notifyDataSetChanged();
                return true;

            case R.id.action_sort_price_high:
                Collections.sort(productArrayList, Product.priceHightoLowComparator);
                productAdapter.notifyDataSetChanged();
                return true;

            case R.id.action_sort_rating:
                Collections.sort(productArrayList, Product.ratingComparator);
                productAdapter.notifyDataSetChanged();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
}