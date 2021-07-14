package com.pradyanti_1313617004.beautyholic.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Product implements Parcelable {

    @SerializedName("brand")
    private String brand;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private double price;

    @SerializedName("price_sign")
    private String price_sign;

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

    public String getPrice_sign() {
        return price_sign;
    }

    public void setPrice_sign(String price_sign) {
        this.price_sign = price_sign;
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

    protected Product(Parcel in) {
        brand = in.readString();
        name = in.readString();
        price = in.readDouble();
        image_link = in.readString();
        description = in.readString();
        rating = in.readFloat();
        category = in.readString();
        product_type = in.readString();
        tagList = in.createStringArrayList();
        product_colors = in.createTypedArrayList(ProductColors.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brand);
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeString(image_link);
        dest.writeString(description);
        dest.writeFloat(rating);
        dest.writeString(category);
        dest.writeString(product_type);
        dest.writeStringList(tagList);
        dest.writeTypedList(product_colors);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
