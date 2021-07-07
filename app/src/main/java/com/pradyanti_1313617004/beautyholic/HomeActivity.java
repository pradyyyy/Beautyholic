package com.pradyanti_1313617004.beautyholic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.pradyanti_1313617004.beautyholic.Adapter.ProductTypeAdapter;
import com.pradyanti_1313617004.beautyholic.Model.ProductType;
import com.pradyanti_1313617004.beautyholic.Model.ProductTypeData;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ProductType> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.rv_product_type);
        recyclerView.setHasFixedSize(true);

        list.addAll(ProductTypeData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        ProductTypeAdapter productTypeAdapter = new ProductTypeAdapter(list);
        recyclerView.setAdapter(productTypeAdapter);

        //OnClick
        productTypeAdapter.setOnItemClickCallBack(new ProductTypeAdapter.OnItemClickCallBack() {
            @Override
            public void onItemClicked(ProductType productType) {
                showSelectedItem(productType);
            }
        });
    }


    //OnClick method
    private void showSelectedItem(ProductType productType) {
        Intent kirimData = new Intent(HomeActivity.this, MainActivity.class);
        kirimData.putExtra("product_type", productType.getName());
        kirimData.putExtra("description_product_type", productType.getDescription());
        startActivity(kirimData);
    }
}