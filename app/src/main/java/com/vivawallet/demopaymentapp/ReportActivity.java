package com.vivawallet.demopaymentapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
    TextView businessDescriptionType;
    TextView printLogoOnMerchantReceipt;
    TextView printVATOnMerchantReceipt;
    TextView isBarcodeEnabled;
    TextView businessDescriptionEnabled;
    TextView printAddressOnReceipt;
    TextView isMerchantReceiptEnabled;
    TextView isCustomerReceiptEnabled;
    TextView ISV_amount;
    TextView ISV_clientId;
    TextView ISV_clientSecret;
    TextView ISV_sourceCode;
    TextView commandTxt;
    TextView batchIdTxt;
    TextView batchNameTxt;

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

        businessDescriptionType = findViewById(R.id.businessDescriptionType);
        printLogoOnMerchantReceipt = findViewById(R.id.printLogoOnMerchantReceipt);
        printVATOnMerchantReceipt = findViewById(R.id.printVATOnMerchantReceipt);
        isBarcodeEnabled = findViewById(R.id.isBarcodeEnabled);
        businessDescriptionEnabled = findViewById(R.id.businessDescriptionEnabled);
        printAddressOnReceipt = findViewById(R.id.printAddressOnReceipt);
        isMerchantReceiptEnabled = findViewById(R.id.isMerchantReceiptEnabled);
        isCustomerReceiptEnabled = findViewById(R.id.isCustomerReceiptEnabled);
        ISV_amount = findViewById(R.id.ISV_amount);
        ISV_clientId = findViewById(R.id.ISV_clientId);
        ISV_clientSecret = findViewById(R.id.ISV_clientSecret);
        ISV_sourceCode = findViewById(R.id.ISV_sourceCode);

        commandTxt = findViewById(R.id.commandTxt);
        batchIdTxt = findViewById(R.id.batchIdTxt);
        batchNameTxt = findViewById(R.id.batchNameTxt);

        Intent i = getIntent();

        Uri data = i.getData();
        if (data != null){
            Log.d("deeplinkResponsePath:", data.toString());
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

            businessDescriptionType.setText(data.getQueryParameter("businessDescriptionType") + "");
            printLogoOnMerchantReceipt.setText(data.getQueryParameter("printLogoOnMerchantReceipt") + "");
            printVATOnMerchantReceipt.setText(data.getQueryParameter("printVATOnMerchantReceipt") + "");
            isBarcodeEnabled.setText(data.getQueryParameter("isBarcodeEnabled") + "");
            businessDescriptionEnabled.setText(data.getQueryParameter("businessDescriptionEnabled") + "");
            printAddressOnReceipt.setText(data.getQueryParameter("printAddressOnReceipt") + "");
            isMerchantReceiptEnabled.setText(data.getQueryParameter("isMerchantReceiptEnabled") + "");
            isCustomerReceiptEnabled.setText(data.getQueryParameter("isCustomerReceiptEnabled") + "");
            ISV_amount.setText(data.getQueryParameter("ISV_amount") + "");
            ISV_clientId.setText(data.getQueryParameter("ISV_clientId") + "");
            ISV_clientSecret.setText(data.getQueryParameter("ISV_clientSecret") + "");
            ISV_sourceCode.setText(data.getQueryParameter("ISV_sourceCode") + "");

            commandTxt.setText(data.getQueryParameter("command") + "");
            batchIdTxt.setText(data.getQueryParameter("batchId") + "");
            batchNameTxt.setText(data.getQueryParameter("batchName") + "");
        }else{

        }


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
