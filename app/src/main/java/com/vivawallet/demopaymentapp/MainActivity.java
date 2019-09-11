package com.vivawallet.demopaymentapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText amountTxt;
    EditText tipAmountTxt;
    EditText refTxt;
    EditText stanTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountTxt = findViewById(R.id.amountTxt);
        tipAmountTxt = findViewById(R.id.tipAmountTxt);
        refTxt = findViewById(R.id.refTxt);
        stanTxt = findViewById(R.id.stanTxt);

    }

    @Override
    protected void onResume() {
        super.onResume();
        deepLinkingLogic();

    }

    public void deepLinkingLogic(){

        Intent intent = getIntent();
        Uri data = intent.getData();
    }


    public void saleAction(View v){
        long amountL = 1200;
        long tipL = 0;
        String callback = "mycallbackscheme://result";
        if (v.getTag().toString().equalsIgnoreCase("0.5")){
            amountL = 50;
            tipL = 15;
        }else if (v.getTag().toString().equalsIgnoreCase("55")){
            amountL=5500;
        }else if(v.getTag().toString().equalsIgnoreCase("6")){
            amountL = 600;
            callback =  "mymissingcallbackscheme://result_missing";
        }
        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                "vivapayclient://pay/v1"
                        + "?merchantKey=12345678909"
                        + "&appId=com.example.myapp"
                        + "&action=sale"
                        + "&clientTransactionId=1234567801234"
                        + "&amount="+amountL
                        + "&tipAmount="+tipL
                        + "&callback=" +callback));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

        try{
            startActivity(payIntent);
        }catch (Exception e){

        }


    }

    public void cancelTransactionAction(View v){

        String reqStr =  "vivapayclient://pay/v1"
                + "?merchantKey=12345678909"
                + "&appId=com.example.myapp"
                + "&action=cancel"
                + "&callback=mycallbackscheme://result";

        if (stanTxt.getText().toString().length()>0){
            reqStr = reqStr + "&stan="+ stanTxt.getText().toString();
        }

        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse( reqStr));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

        startActivity(payIntent);
    }

}
