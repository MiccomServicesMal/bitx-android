package com.twentytwoseven.android.bitx.model;

import com.google.gson.annotations.SerializedName;

public class Transaction {
    @SerializedName("Txid")
    public String id;
    public long timestamp;
    public String type;
    public String address;
    public String description;
    public double amount;
    public boolean pending;
}
