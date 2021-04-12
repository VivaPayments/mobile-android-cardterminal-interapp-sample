package com.vivawallet.demopaymentapp;

import com.google.gson.annotations.SerializedName;

public class FeesResponse {
    @SerializedName("Fee")
    public double Fee;
    @SerializedName("BillFee")
    public double BillFee;
    @SerializedName("ServiceFee")
    public double ServiceFee;
    @SerializedName("ResellerFee")
    public double ResellerFee;
    @SerializedName("CollectionFee")
    public double CollectionFee;
    @SerializedName("TotalConversionFee")
    public double TotalConversionFee;
    @SerializedName("ErrorCode")
    public int ErrorCode;
    @SerializedName("ErrorText")
    public String ErrorText;
    @SerializedName("TimeStamp")
    public String TimeStamp;
    @SerializedName("CorrelationId")
    public String CorrelationId;
    @SerializedName("EventId")
    public int EventId;
    @SerializedName("Success")
    public boolean Success;

}
