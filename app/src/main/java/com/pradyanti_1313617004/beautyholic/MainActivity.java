package com.pradyanti_1313617004.beautyholic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private ProductAdapter mAdapter;
    public static MainActivity mainActivity;
    private static final String TAG = "MainActivity";

    public static final String EXTRA_NAMA = "extra_nama";

    List<Product> productArrayList;
    TextView product_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        product_type = findViewById(R.id.tv_product_type);

        recyclerView = findViewById(R.id.rv_product);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        mainActivity = this;

        String product_type_name = getIntent().getStringExtra(EXTRA_NAMA);

        product_type.setText(product_type_name);

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
                        mAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(mAdapter);
                        recyclerView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());

                    }
                });
    }
}