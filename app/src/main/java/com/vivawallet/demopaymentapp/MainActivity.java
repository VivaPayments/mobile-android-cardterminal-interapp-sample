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

    }

    public void cancelTransactionAction(View v){

    }

}
