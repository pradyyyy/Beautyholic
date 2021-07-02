package com.pradyanti_1313617004.beautyholic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.pradyanti_1313617004.beautyholic.Adapter.ProductAdapter;
import com.pradyanti_1313617004.beautyholic.Model.Product;
import com.pradyanti_1313617004.beautyholic.Model.ProductModel;
import com.pradyanti_1313617004.beautyholic.Retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter mAdapter;
    public static MainActivity mainActivity;
    private static final String TAG = "MainActivity";

    List<Product> productArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_product);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mainActivity = this;

        getProductFromApi();

    }

    private void getProductFromApi() {
        ApiClient.apiInterface().getProductData()
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

                        mAdapter = new ProductAdapter(productArrayList);
                        recyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());

                    }
                });
    }
}