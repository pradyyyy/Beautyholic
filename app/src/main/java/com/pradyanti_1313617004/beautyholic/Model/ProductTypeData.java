package com.pradyanti_1313617004.beautyholic.Model;

import com.pradyanti_1313617004.beautyholic.R;

import java.util.ArrayList;

public class ProductTypeData {

    private static String[] productTypeNames = {
            "Blush",
            "Bronzer",
            "Eyebrow",
            "Eyeliner",
            "Eyeshadow",
            "Foundation",
            "Lip Liner",
            "Lipstick",
            "Mascara",
            "Nail Polish"
    };

    private static int[] productTypeImage = {
            R.drawable.blush,
            R.drawable.bronzer,
            R.drawable.eyebrow,
            R.drawable.eyeliner,
            R.drawable.eyeshadow,
            R.drawable.foundation,
            R.drawable.lips_liner,
            R.drawable.lipstick,
            R.drawable.mascara,
            R.drawable.nail_polish
    };

    public static ArrayList<ProductType> getListData() {
        ArrayList<ProductType> list = new ArrayList<>();
        for (int position = 0; position < productTypeNames.length; position++ ) {
            ProductType productType = new ProductType();
            productType.setName(productTypeNames[position]);
            productType.setPhotoPoster(productTypeImage[position]);
            list.add(productType);
        }

        return list;
    }
}
