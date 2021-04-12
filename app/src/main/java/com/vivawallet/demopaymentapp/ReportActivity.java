package com.vivawallet.demopaymentapp;

import android.content.Intent;
import android.net.Uri;
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
    TextView accountNumbertTxt;
    TextView orderCodeTxt;
    TextView shortOrderCodeTxt;
    TextView dateTxt;
    TextView installmentsTxt;
    TextView transactionIdTxt;
    TextView billPaymentTokenTxt;
    TextView customerTrnsTxt;
    TextView fullNameTxt;

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
        accountNumbertTxt = findViewById(R.id.accountNumbertTxt);
        orderCodeTxt = findViewById(R.id.orderCodeTxt);
        shortOrderCodeTxt = findViewById(R.id.shortOrderCodeTxt);
        dateTxt = findViewById(R.id.dateTxt);
        installmentsTxt = findViewById(R.id.installmentsTxt);
        transactionIdTxt = findViewById(R.id.transactionIdTxt);
        billPaymentTokenTxt = findViewById(R.id.billPaymentTokenTxt);
        customerTrnsTxt = findViewById(R.id.customerTrnsTxt);
        fullNameTxt = findViewById(R.id.fullNameTxt);

        Intent i = getIntent();

        Uri data = i.getData();
        if (data != null){
            statusTxt.setText(data.getQueryParameter("status") + "");
            msgTxt.setText(data.getQueryParameter("message") + "");
            actionTxt.setText(data.getQueryParameter("action") + "");
            clientTransactionIdTxt.setText(data.getQueryParameter("clientTransactionId") + "");
            amountTxt.setText(data.getQueryParameter("amount") + "");
            tipAmountTxt.setText(data.getQueryParameter("tipAmount") + "");
            verificationMethodTxt.setText(data.getQueryParameter("verificationMethod") + "");
            rrnTxt.setText(data.getQueryParameter("rrn") + "");
            cardTypeTxt.setText(data.getQueryParameter("cardType") + "");
            referenceNumberTxt.setText(data.getQueryParameter("referenceNumber") + "");
            authorisationCodeTxt.setText(data.getQueryParameter("authorisationCode") + "");
            tidCodeTxt.setText(data.getQueryParameter("tid") + "");
            accountNumbertTxt.setText(data.getQueryParameter("accountNumber") + "");
            installmentsTxt.setText(data.getQueryParameter("installments") + "");

            orderCodeTxt.setText(data.getQueryParameter("orderCode") + "");
            shortOrderCodeTxt.setText(data.getQueryParameter("shortOrderCode") + "");
            dateTxt.setText(data.getQueryParameter("transactionDate") + "");
            transactionIdTxt.setText(data.getQueryParameter("transactionId") + "");
            billPaymentTokenTxt.setText(data.getQueryParameter("billPaymentToken") + "");
            customerTrnsTxt.setText(data.getQueryParameter("customerTrns") + "");
            fullNameTxt.setText(data.getQueryParameter("fullName") + "");
        }else{

        }


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
