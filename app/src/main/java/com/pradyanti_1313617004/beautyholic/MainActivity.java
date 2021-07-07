package com.pradyanti_1313617004.beautyholic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.pradyanti_1313617004.beautyholic.Adapter.ProductAdapter;
import com.pradyanti_1313617004.beautyholic.Model.Product;
import com.pradyanti_1313617004.beautyholic.Retrofit.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public static MainActivity mainActivity;
    private static final String TAG = "MainActivity";
    TextView tvDescriptionProduct;

    List<Product> productArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDescriptionProduct = findViewById(R.id.detail_product_type);

        recyclerView = findViewById(R.id.rv_product);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        mainActivity = this;

//        String product_type_name = getIntent().getStringExtra(EXTRA_NAMA);
//
//        product_type.setText(product_type_name);
//
//        getProductFromApi();

        if (getIntent().getExtras() != null) {
            String product_type = getIntent().getExtras().getString("product_type");
            tvDescriptionProduct.setText(getIntent().getExtras().getString("description_product_type"));
            this.getSupportActionBar().setTitle(product_type);
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

                        ProductAdapter productAdapter = new ProductAdapter(productArrayList);
                        productAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(productAdapter);
                        recyclerView.smoothScrollToPosition(productAdapter.getItemCount() - 1);

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
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }

    private void showSelectedProduct(Product productData) {
        Intent kirimData = new Intent(MainActivity.this, DetailProductActivity.class);
        kirimData.putExtra("ProductDataList", productData);
        startActivity(kirimData);
    }
}