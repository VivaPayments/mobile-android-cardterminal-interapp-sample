package com.vivawallet.demopaymentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ReportActivity extends AppCompatActivity {

    TextView statusTxt;
    TextView msgTxt;
    TextView actionTxt;
    TextView clientTransactionIdTxt;
    TextView amountTxt;
    TextView tipAmountTxt;
    TextView verificationMethodTxt;
    TextView rrnTxt;
    TextView cardTypeTxt;
    TextView referenceNumberTxt;
    TextView authorisationCodeTxt;
    TextView tidCodeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        statusTxt = findViewById(R.id.statusTxt);
        msgTxt = findViewById(R.id.msgTxt);
        actionTxt = findViewById(R.id.actionTxt);
        clientTransactionIdTxt = findViewById(R.id.clientTransactionIdTxt);
        amountTxt = findViewById(R.id.amountTxt);
        tipAmountTxt = findViewById(R.id.tipAmountTxt);
        verificationMethodTxt = findViewById(R.id.verificationMethodTxt);
        rrnTxt = findViewById(R.id.rrnTxt);
        cardTypeTxt = findViewById(R.id.cardTypeTxt);
        referenceNumberTxt = findViewById(R.id.referenceNumberTxt);
        authorisationCodeTxt = findViewById(R.id.authorisationCodeTxt);
        tidCodeTxt = findViewById(R.id.tidCodeTxt);


        Intent i = getIntent();

        statusTxt.setText(i.getStringExtra("status") + "");
        msgTxt.setText(i.getStringExtra("message") + "");
        actionTxt.setText(i.getStringExtra("action") + "");
        clientTransactionIdTxt.setText(i.getStringExtra("clientTransactionId") + "");
        amountTxt.setText(i.getStringExtra("amount") + "");
        tipAmountTxt.setText(i.getStringExtra("tipAmount") + "");
        verificationMethodTxt.setText(i.getStringExtra("verificationMethod") + "");
        rrnTxt.setText(i.getStringExtra("rrn") + "");
        cardTypeTxt.setText(i.getStringExtra("cardType") + "");
        referenceNumberTxt.setText(i.getStringExtra("referenceNumber") + "");
        authorisationCodeTxt.setText(i.getStringExtra("authorisationCode") + "");
        tidCodeTxt.setText(i.getStringExtra("tid") + "");


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
