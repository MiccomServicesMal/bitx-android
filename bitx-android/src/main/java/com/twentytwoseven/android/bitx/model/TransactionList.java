package com.twentytwoseven.android.bitx.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionList {
    @SerializedName("TotalCount")
    public int totalCount;

    @SerializedName("Transactions")
    public List<Transaction> transactions;
}
