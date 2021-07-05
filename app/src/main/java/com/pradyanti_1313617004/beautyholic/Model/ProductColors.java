package com.pradyanti_1313617004.beautyholic.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProductColors implements Parcelable {

    @SerializedName("hex_value")
    private String hex_value;

    @SerializedName("colour_name")
    private String colour_name;

    public String getHex_value() {
        return hex_value;
    }

    public void setHex_value(String hex_value) {
        this.hex_value = hex_value;
    }

    public String getColour_name() {
        return colour_name;
    }

    public void setColour_name(String colour_name) {
        this.colour_name = colour_name;
    }

    protected ProductColors(Parcel in) {
        hex_value = in.readString();
        colour_name = in.readString();
    }

    public static final Creator<ProductColors> CREATOR = new Creator<ProductColors>() {
        @Override
        public ProductColors createFromParcel(Parcel in) {
            return new ProductColors(in);
        }

        @Override
        public ProductColors[] newArray(int size) {
            return new ProductColors[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hex_value);
        dest.writeString(colour_name);
    }
}
