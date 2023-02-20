package com.vivawallet.demopaymentapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.TextView;

public class ReportActivity extends AppCompatActivity {

    TextView virtualIdTxt;
    TextView sourceTerminalIdTxt;
    TextView merchantIDTxt;

    TextView statusTxt;
    TextView msgTxt;
    TextView actionTxt;
    TextView activationCodeTxt;
    TextView transactionTypeTxt;
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
    TextView ISV_merchantId;
    TextView ISV_currencyCode;
    TextView ISV_MerchantSourceCode, ISV_customerTrns, ISV_ClientTransactionId;
    TextView loyaltyMerchant;
    TextView loyaltyTerminal;
    TextView loyaltyPacketNo;
    TextView loyaltyTransactionNo;
    TextView loyaltyRedemptionAmount;
    TextView loyaltyPaymentAmount;
    TextView loyaltyFinalAmount;
    TextView loyaltyPointsCollected;
    TextView loyaltyPointsRedeemed;
    TextView loyaltyPointsPrevBalance;
    TextView loyaltyPointsNewBalance;
    TextView loyaltyExtraMessage;
    TextView loyaltyProgramId;
    TextView loyaltyLogoUrl;
    TextView commandTxt;
    TextView batchIdTxt;
    TextView batchNameTxt;
    TextView aid;
    TextView vatNumber;
    TextView address;
    TextView businessDescription;
    TextView merchantReceiptPAN;
    TextView cardholderReceiptPAN;
    TextView transactionReceiptAcquirerZone;
    TextView cardholderReceiptText;
    TextView merchantReceiptText;
    TextView cardholderName;
    TextView cardExpirationDate;
    TextView cardholderNameExpirationDateFlags;
    TextView needsSignature;
    TextView addQRCode;
    TextView terminalSerialNumber;
    TextView currency;
    TextView errorText;
    TextView applicationVersion;
    TextView oldBalance;
    TextView newBalance;
    TextView entryMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        virtualIdTxt = findViewById(R.id.virtualIdTxt);
        sourceTerminalIdTxt = findViewById(R.id.sourceTerminalIdTxt);
        merchantIDTxt = findViewById(R.id.merchantIDTxt);

        statusTxt = findViewById(R.id.statusTxt);
        msgTxt = findViewById(R.id.msgTxt);
        actionTxt = findViewById(R.id.actionTxt);
        transactionTypeTxt = findViewById(R.id.transactionTypeTxt);
        activationCodeTxt = findViewById(R.id.activationCodeTxt);
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
        ISV_merchantId = findViewById(R.id.ISV_merchantId);
        ISV_currencyCode = findViewById(R.id.ISV_currencyCode);
        ISV_MerchantSourceCode = findViewById(R.id.ISV_merchantSourceCode);
        ISV_customerTrns = findViewById(R.id.ISV_customerTrns);
        ISV_ClientTransactionId = findViewById(R.id.ISV_clientTransactionId);

        commandTxt = findViewById(R.id.commandTxt);
        batchIdTxt = findViewById(R.id.batchIdTxt);
        batchNameTxt = findViewById(R.id.batchNameTxt);

        aid = findViewById(R.id.aidTxt);
        vatNumber = findViewById(R.id.vatNumberTxt);
        address = findViewById(R.id.addressTxt);
        businessDescription = findViewById(R.id.businessDescriptionTxt);
        merchantReceiptPAN = findViewById(R.id.merchantReceiptPAN);
        cardholderReceiptPAN = findViewById(R.id.cardholderReceiptPAN);
        transactionReceiptAcquirerZone = findViewById(R.id.transactionReceiptAcquirerZone);
        cardholderReceiptText = findViewById(R.id.cardholderReceiptText);
        merchantReceiptText = findViewById(R.id.merchantReceiptText);
        cardholderName = findViewById(R.id.cardholderName);
        cardExpirationDate = findViewById(R.id.cardExpirationDate);
        cardholderNameExpirationDateFlags = findViewById(R.id.cardholderNameExpirationDateFlags);
        needsSignature = findViewById(R.id.needsSignature);
        addQRCode = findViewById(R.id.addQRCode);
        terminalSerialNumber = findViewById(R.id.terminalSerialNumber);
        currency = findViewById(R.id.currency);
        errorText = findViewById(R.id.errorText);
        applicationVersion = findViewById(R.id.applicationVersion);
        oldBalance = findViewById(R.id.oldBalance);
        newBalance = findViewById(R.id.newBalance);
        entryMode = findViewById(R.id.entryMode);
        //loyalties
        loyaltyMerchant = findViewById(R.id.loyaltyMerchant);
        loyaltyTerminal = findViewById(R.id.loyaltyTerminal);
        loyaltyPacketNo = findViewById(R.id.loyaltyPacketNo);
        loyaltyTransactionNo = findViewById(R.id.loyaltyTransactionNo);
        loyaltyRedemptionAmount = findViewById(R.id.loyaltyRedemptionAmount);
        loyaltyPaymentAmount = findViewById(R.id.loyaltyPaymentAmount);
        loyaltyFinalAmount = findViewById(R.id.loyaltyFinalAmount);
        loyaltyPointsCollected = findViewById(R.id.loyaltyPointsCollected);
        loyaltyPointsRedeemed = findViewById(R.id.loyaltyPointsRedeemed);
        loyaltyPointsPrevBalance = findViewById(R.id.loyaltyPointsPrevBalance);
        loyaltyPointsNewBalance = findViewById(R.id.loyaltyPointsNewBalance);
        loyaltyExtraMessage = findViewById(R.id.loyaltyExtraMessage);
        loyaltyProgramId = findViewById(R.id.loyaltyProgramId);
        loyaltyLogoUrl = findViewById(R.id.loyaltyLogoUrl);

        Intent i = getIntent();

        Uri data = i.getData();
        if (data != null){
            Log.d("deeplinkResponsePath:", data.toString());
            virtualIdTxt.setText(data.getQueryParameter("virtualId"));
            sourceTerminalIdTxt.setText(data.getQueryParameter("sourceTerminalId"));
            merchantIDTxt.setText(data.getQueryParameter("merchantID"));

            statusTxt.setText(data.getQueryParameter("status"));
            msgTxt.setText(data.getQueryParameter("message"));
            actionTxt.setText(data.getQueryParameter("action"));
            activationCodeTxt.setText(data.getQueryParameter("activationCode"));
            transactionTypeTxt.setText(data.getQueryParameter("transactionType"));
            clientTransactionIdTxt.setText(data.getQueryParameter("clientTransactionId"));
            amountTxt.setText(data.getQueryParameter("amount"));
            tipAmountTxt.setText(data.getQueryParameter("tipAmount"));
            verificationMethodTxt.setText(data.getQueryParameter("verificationMethod"));
            rrnTxt.setText(data.getQueryParameter("rrn"));
            cardTypeTxt.setText(data.getQueryParameter("cardType"));
            referenceNumberTxt.setText(data.getQueryParameter("referenceNumber"));
            authorisationCodeTxt.setText(data.getQueryParameter("authorisationCode"));
            tidCodeTxt.setText(data.getQueryParameter("tid"));
            accountNumbertTxt.setText(data.getQueryParameter("accountNumber"));
            installmentsTxt.setText(data.getQueryParameter("installments"));

            orderCodeTxt.setText(data.getQueryParameter("orderCode"));
            shortOrderCodeTxt.setText(data.getQueryParameter("shortOrderCode"));
            dateTxt.setText(data.getQueryParameter("transactionDate"));
            transactionIdTxt.setText(data.getQueryParameter("transactionId"));
            billPaymentTokenTxt.setText(data.getQueryParameter("billPaymentToken"));
            customerTrnsTxt.setText(data.getQueryParameter("customerTrns"));
            fullNameTxt.setText(data.getQueryParameter("fullName"));

            businessDescriptionType.setText(data.getQueryParameter("businessDescriptionType"));
            printLogoOnMerchantReceipt.setText(data.getBooleanQueryParameter("printLogoOnMerchantReceipt", false) + "");
            printVATOnMerchantReceipt.setText(data.getBooleanQueryParameter("printVATOnMerchantReceipt", false) + "");
            isBarcodeEnabled.setText(data.getBooleanQueryParameter("isBarcodeEnabled", false) + "");
            businessDescriptionEnabled.setText(data.getBooleanQueryParameter("businessDescriptionEnabled", false) + "");
            printAddressOnReceipt.setText(data.getBooleanQueryParameter("printAddressOnReceipt", false) + "");
            isMerchantReceiptEnabled.setText(data.getBooleanQueryParameter("isMerchantReceiptEnabled", false) + "");
            isCustomerReceiptEnabled.setText(data.getBooleanQueryParameter("isCustomerReceiptEnabled", false) + "");
            ISV_amount.setText(data.getQueryParameter("ISV_amount"));
            ISV_clientId.setText(data.getQueryParameter("ISV_clientId"));
            ISV_clientSecret.setText(data.getQueryParameter("ISV_clientSecret"));
            ISV_sourceCode.setText(data.getQueryParameter("ISV_sourceCode"));
            ISV_merchantId.setText(data.getQueryParameter("ISV_merchantId"));
            ISV_currencyCode.setText(data.getQueryParameter("ISV_currencyCode"));
            ISV_MerchantSourceCode.setText(data.getQueryParameter("ISV_merchantSourceCode"));
            ISV_customerTrns.setText(data.getQueryParameter("ISV_customerTrns"));
            ISV_ClientTransactionId.setText(data.getQueryParameter("ISV_clientTransactionId"));

            commandTxt.setText(data.getQueryParameter("command"));
            batchIdTxt.setText(data.getQueryParameter("batchId"));
            batchNameTxt.setText(data.getQueryParameter("batchName"));

            aid.setText(data.getQueryParameter("aid"));
            vatNumber.setText(data.getQueryParameter("vatNumber"));
            address.setText(data.getQueryParameter("address"));
            businessDescription.setText(data.getQueryParameter("businessDescription"));

            merchantReceiptPAN.setText(data.getQueryParameter("merchantReceiptPAN"));
            cardholderReceiptPAN.setText(data.getQueryParameter("cardholderReceiptPAN"));
            transactionReceiptAcquirerZone.setText(data.getQueryParameter("transactionReceiptAcquirerZone"));
            cardholderReceiptText.setText(data.getQueryParameter("cardholderReceiptText"));
            merchantReceiptText.setText(data.getQueryParameter("merchantReceiptText"));
            cardholderName.setText(data.getQueryParameter("cardholderName"));
            cardExpirationDate.setText(data.getQueryParameter("cardExpirationDate"));
            cardholderNameExpirationDateFlags.setText(data.getQueryParameter("cardholderNameExpirationDateFlags"));
            needsSignature.setText(data.getQueryParameter("needsSignature"));
            addQRCode.setText(data.getQueryParameter("addQRCode"));
            terminalSerialNumber.setText(data.getQueryParameter("terminalSerialNumber"));
            currency.setText(data.getQueryParameter("currency"));
            errorText.setText(data.getQueryParameter("errorText"));
            applicationVersion.setText(data.getQueryParameter("applicationVersion"));
            oldBalance.setText(data.getQueryParameter("oldBalance"));
            newBalance.setText(data.getQueryParameter("newBalance"));
            entryMode.setText(data.getQueryParameter("entryMode"));

            loyaltyMerchant.setText(data.getQueryParameter("loyaltyMerchant"));
            loyaltyTerminal.setText(data.getQueryParameter("loyaltyTerminal"));
            loyaltyPacketNo.setText(data.getQueryParameter("loyaltyPacketNo"));
            loyaltyTransactionNo.setText(data.getQueryParameter("loyaltyTransactionNo"));
            loyaltyPaymentAmount.setText(data.getQueryParameter("loyaltyPaymentAmount"));
            loyaltyRedemptionAmount.setText(data.getQueryParameter("loyaltyRedemptionAmount"));
            loyaltyFinalAmount.setText(data.getQueryParameter("loyaltyFinalAmount"));
            loyaltyPointsCollected.setText(data.getQueryParameter("loyaltyPointsCollected"));
            loyaltyPointsRedeemed.setText(data.getQueryParameter("loyaltyPointsRedeemed"));
            loyaltyPointsPrevBalance.setText(data.getQueryParameter("loyaltyPointsPrevBalance"));
            loyaltyPointsNewBalance.setText(data.getQueryParameter("loyaltyPointsNewBalance"));
            loyaltyExtraMessage.setText(data.getQueryParameter("loyaltyExtraMessage"));
            loyaltyProgramId.setText(data.getQueryParameter("loyaltyProgramId"));
            loyaltyLogoUrl.setText(data.getQueryParameter("loyaltyLogoUrl"));
        }else{

        }


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
