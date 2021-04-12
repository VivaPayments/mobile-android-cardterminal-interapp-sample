package com.vivawallet.demopaymentapp;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 * */
public class BillResponse {
  @SerializedName("PaymentToken")
  @Expose
  private String PaymentToken;
  public void setPaymentToken(String PaymentToken){
   this.PaymentToken=PaymentToken;
  }
  public String getPaymentToken(){
   return PaymentToken;
  }
}