package com.twentytwoseven.android.bitx.model;

import com.google.gson.annotations.SerializedName;

public class Transaction {
    @SerializedName("Txid")
    public String id;

    @SerializedName("Timestamp")
    public long timestamp;

    @SerializedName("Type")
    public String type;

    @SerializedName("Address")
    public String address;

    @SerializedName("Description")
    public String description;

    @SerializedName("Amount")
    public double amount;

    @SerializedName("Pending")
    public boolean pending;
}
