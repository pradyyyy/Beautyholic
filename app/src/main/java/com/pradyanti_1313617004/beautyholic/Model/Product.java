package com.pradyanti_1313617004.beautyholic.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Product implements Parcelable {

    @SerializedName("brand")
    private String brand;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private double price;

    @SerializedName("currency")
    private String currency;

    @SerializedName("image_link")
    private String image_link;

    @SerializedName("description")
    private String description;

    @SerializedName("rating")
    private float rating;

    @SerializedName("category")
    private String category;

    @SerializedName("product_type")
    private String product_type;

    @SerializedName("tag_list")
    @Expose
    private List<String> tagList = null;

    @SerializedName("product_colors")
    private ArrayList<ProductColors> product_colors;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public ArrayList<ProductColors> getProduct_colors() {
        return product_colors;
    }

    public void setProduct_colors(ArrayList<ProductColors> product_colors) {
        this.product_colors = product_colors;
    }

    public Product(String brand, String name, double price, String currency, String image_link, String description, float rating, String category, String product_type, List<String> tagList, ArrayList<ProductColors> product_colors) {
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.image_link = image_link;
        this.description = description;
        this.rating = rating;
        this.category = category;
        this.product_type = product_type;
        this.tagList = tagList;
        this.product_colors = product_colors;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brand);
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeString(currency);
        dest.writeString(image_link);
        dest.writeString(description);
        dest.writeFloat(rating);
        dest.writeString(category);
        dest.writeString(product_type);
        dest.writeStringList(tagList);
        dest.writeTypedList(product_colors);
    }

    protected Product(Parcel in) {
        brand = in.readString();
        name = in.readString();
        price = in.readDouble();
        currency = in.readString();
        image_link = in.readString();
        description = in.readString();
        rating = in.readFloat();
        category = in.readString();
        product_type = in.readString();
        tagList = in.createStringArrayList();
        product_colors = in.createTypedArrayList(ProductColors.CREATOR);
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public static Comparator<Product> brandComparator = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return String.valueOf(o1.getBrand()).compareTo(String.valueOf(o2.getBrand()));
        }
    };

    public static Comparator<Product> categoryCamparator = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return String.valueOf(o1.getCategory()).compareTo(String.valueOf(o2.getCategory()));
        }
    };

    public static Comparator<Product> priceLowtoHighComparator = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return Double.compare(o1.getPrice(), o2.getPrice());
        }
    };

    public static Comparator<Product> priceHightoLowComparator = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return Double.compare(o2.getPrice(), o1.getPrice());
        }
    };

    public static Comparator<Product> ratingComparator = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return Double.compare(o2.getRating(), o1.getRating());
        }
    };
}
