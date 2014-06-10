package com.twentytwoseven.android.bitx.model;

public class Transaction {
    public String txid;
    public long timestamp;
    public String type;
    public String address;
    public String description;
    public double amount;
    public boolean pending;

    public static class Asset {
        public static final String XBT = "XBT";
        public static final String ZAR = "ZAR";
    }
}
