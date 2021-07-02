package com.pradyanti_1313617004.beautyholic.Model;

import com.google.gson.annotations.SerializedName;

public class ProductColors {

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
}
