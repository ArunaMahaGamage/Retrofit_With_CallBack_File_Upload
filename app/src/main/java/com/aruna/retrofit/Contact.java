package com.aruna.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aruna on 1/1/18.
 */

public class Contact {

    @SerializedName("name")
    private String Name;

    @SerializedName("email")
    private String Email;

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }
}
