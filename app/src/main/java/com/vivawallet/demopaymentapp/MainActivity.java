package com.vivawallet.demopaymentapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import com.google.gson.Gson;
import com.vivawallet.demopaymentapp.fragments.PrintingSettingsFragment;
import com.vivawallet.demopaymentapp.fragments.UnattendedFragment;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;
import timber.log.Timber.DebugTree;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "DEEP LINK ACTIVITY";

    EditText posActivationClientId;
    EditText posActivationClientSecret;
    EditText activationCode;
    EditText posActivationSource;
    EditText posActivationPinCode;
    CheckBox skipExternalDeviceSetup;
    CheckBox activateMoto;
    CheckBox activateQR;
    CheckBox disableManualAmountEntry;
    CheckBox forceCardPresentmentForRefund;
    CheckBox lockRefund;
    CheckBox lockTransactionsList;
    CheckBox lockMoto;
    CheckBox lockCapture;
    CheckBox lockPreAuth;

    FragmentContainerView containerView;
    EditText amountTxt;
    EditText tipAmountTxt;
    EditText refTxt;
    EditText referenceNumberTxt;
    CheckBox emptyMerchantKey;
    CheckBox emptyAppId;
    CheckBox emptyCallback;
    CheckBox emptyAction;
    CheckBox installmentsCheck;
    CheckBox receiptCheck;
    CheckBox ratingCheck;
    CheckBox resultCheck;
    CheckBox isvCheck;
    CheckBox protocolCheck;

    DatePickerDialog dialog;
    TextView dateFrom;
    TextView billFees;
    TextView dateTo;
    LinearLayout dateToContainer;
    LinearLayout dateFromContainer;
    LinearLayout prefInstallmentsLayout;
    LinearLayout ISVFeeLayout;
    LinearLayout ISVClientIdLayout;
    LinearLayout ISVClientSecretLayout;
    LinearLayout ISVSourceCodeLayout;
    LinearLayout ISVMerchantLayout;
    LinearLayout protocolContainer;

    EditText prefInstallmentsTxt;
    EditText orderCode;
    EditText shortOrderCode;
    EditText amountRefund;
    EditText amountBill;
    EditText paymentReferenceTxt;
    EditText ISVClientIdTxt;
    EditText ISVFeeTxt;
    EditText ISVClientSecretTxt;
    EditText ISVSourceCodeTxt;
    CheckBox ISVMerchantCheck;
    EditText ISVMerchantIdTxt;
    EditText ISVMerchantCurrencyTxt;
    EditText ISVMerchantSourceCodeTxt;
    EditText ISVCustomerTrnsTxt;
    EditText ISVClientTransactionId;
    EditText protocolType;

    EditText resellerAmountTxt;
    Button resellerSendBtn;

    BillResponse billResponse = null;

    EditText resellerMerchId;
    EditText currencyCode;
    EditText resellerSourceCodeTxt;
    EditText customerTrnsTxt;
    EditText resellerTipAmountTxt;
    EditText merchantTrnsTxt;
    EditText multimerchantSourceCodeTxt;

    EditText batchIdTxt;
    EditText batchNameTxt;

    EditText reprintTransactionOrderCode;

    Spinner spinnerDecimalAmountModes;
    TextView textDecimalAmountMode;
    Button btnDecimalAmount;

    EditText transactionDetailsClientTransactionId;
    EditText transactionDetailsSourceTerminalId;

    EditText amountPreAuthTxt;
    EditText paymentPreAuthReferenceTxt;

    Spinner spinnerPaymentMethod;

    static final String UTC_DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
    private String selectedAmountMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        Timber.plant(new DebugTree());

        posActivationClientId = findViewById(R.id.clientIdForActivation);
        posActivationClientSecret = findViewById(R.id.clientSecretForActivation);
        activationCode = findViewById(R.id.activationCode);
        posActivationSource = findViewById(R.id.sourceCodeForActivation);
        posActivationPinCode = findViewById(R.id.pinCodeForActivation);

        skipExternalDeviceSetup = findViewById(R.id.skipSetupExternalDevice);
        activateMoto = findViewById(R.id.activateMoto);
        activateQR= findViewById(R.id.activateQR);
        disableManualAmountEntry= findViewById(R.id.disableManualAmountEntry);
        forceCardPresentmentForRefund = findViewById(R.id.forceCardPresentmentForRefund);
        lockRefund= findViewById(R.id.lockRefund);
        lockTransactionsList= findViewById(R.id.lockTransactionsList);
        lockMoto= findViewById(R.id.lockMoto);
        lockCapture = findViewById(R.id.lockCapture);
        lockPreAuth = findViewById(R.id.lockPreAuth);

        emptyMerchantKey = findViewById(R.id.emptyMerchantKey);
        emptyAppId = findViewById(R.id.emptyAppId);
        emptyCallback = findViewById(R.id.emptyCallback);
        paymentReferenceTxt = findViewById(R.id.paymentReferenceTxt);
        emptyAction = findViewById(R.id.emptyAction);
        amountTxt = findViewById(R.id.amountTxt);
        tipAmountTxt = findViewById(R.id.tipAmountTxt);
        refTxt = findViewById(R.id.refTxt);
        referenceNumberTxt = findViewById(R.id.stanTxt);
        installmentsCheck = findViewById(R.id.installmentsCheck);
        receiptCheck = findViewById(R.id.show_receipt);
        ratingCheck = findViewById(R.id.show_rating);
        resultCheck = findViewById(R.id.show_result);
        prefInstallmentsLayout = findViewById(R.id.prefInstallmentsLayout);
        prefInstallmentsTxt = findViewById(R.id.prefInstallmentsTxt);
        dateFrom = findViewById(R.id.dateFrom);
        dateTo = findViewById(R.id.dateTo);
        orderCode = findViewById(R.id.orderCodeTxt);
        shortOrderCode = findViewById(R.id.shortOrderCodeTxt);
        amountRefund = findViewById(R.id.amountRefund);
        dateFromContainer = findViewById(R.id.dateFromContainer);
        dateToContainer = findViewById(R.id.dateToContainer);
        amountBill = findViewById(R.id.amountBill);
        billFees = findViewById(R.id.billFees);
        resellerAmountTxt = findViewById(R.id.resellerAmountTxt);
        resellerSendBtn = findViewById(R.id.resellerSendBtn);
        isvCheck = findViewById(R.id.isvCheck);
        ISVClientIdTxt = findViewById(R.id.ISVClientIdTxt);
        ISVFeeTxt = findViewById(R.id.ISVFeeTxt);
        ISVSourceCodeLayout = findViewById(R.id.ISVSourceCodeLayout);
        ISVClientSecretLayout = findViewById(R.id.ISVClientSecretLayout);
        ISVMerchantLayout = findViewById(R.id.ISVMerchantLayout);
        resellerMerchId = findViewById(R.id.resellerMerchId);
        currencyCode = findViewById(R.id.currencyCodeTxt);

        resellerSourceCodeTxt = findViewById(R.id.resellerSourceCodeTxt);
        customerTrnsTxt = findViewById(R.id.customerTrnsTxt);
        resellerTipAmountTxt = findViewById(R.id.resellerTipAmountTxt);
        merchantTrnsTxt = findViewById(R.id.merchantTrnsTxt);
        multimerchantSourceCodeTxt = findViewById(R.id.multimerchantSourceCodeTxt);
        ISVClientIdLayout = findViewById(R.id.ISVClientIdLayout);
        ISVFeeLayout = findViewById(R.id.ISVFeeLayout);
        ISVSourceCodeTxt = findViewById(R.id.ISVSourceCodeTxt);
        ISVClientSecretTxt = findViewById(R.id.ISVClientSecretTxt);
        ISVMerchantCheck = findViewById(R.id.ISVMerchantCheck);
        ISVMerchantIdTxt = findViewById(R.id.ISVMerchantIdTxt);
        ISVMerchantCurrencyTxt = findViewById(R.id.ISVMerchantCurrencyTxt);
        ISVMerchantSourceCodeTxt = findViewById(R.id.ISVMerchantSourceCodeTxt);
        ISVCustomerTrnsTxt = findViewById(R.id.ISVCustomerTrnsTxt);
        ISVClientTransactionId = findViewById(R.id.ISVMerchantTrnsTxt);
        batchIdTxt = findViewById(R.id.batchIdText);
        batchNameTxt = findViewById(R.id.batchNameText);
        protocolType = findViewById(R.id.protocolType);
        protocolContainer = findViewById(R.id.protocolContainer);
        protocolCheck = findViewById(R.id.protocolCheck);
        containerView = findViewById(R.id.fragment_container_view);
        posActivationClientId = findViewById(R.id.clientIdForActivation);
        posActivationClientSecret = findViewById(R.id.clientSecretForActivation);
        posActivationSource = findViewById(R.id.sourceCodeForActivation);
        posActivationPinCode = findViewById(R.id.pinCodeForActivation);

        reprintTransactionOrderCode = findViewById(R.id.reprintTransactionOrderCode);

        transactionDetailsClientTransactionId = findViewById(R.id.transactionDetailsClientTransactionId);
        transactionDetailsSourceTerminalId = findViewById(R.id.transactionDetailsSourceTerminalId);

        spinnerDecimalAmountModes = findViewById(R.id.spinnerDecimalAmountModes);
        textDecimalAmountMode = findViewById(R.id.textDecimalAmountMode);
        btnDecimalAmount = findViewById(R.id.btnDecimalAmount);

        amountPreAuthTxt = findViewById(R.id.amountPreAuthTxt);
        paymentPreAuthReferenceTxt = findViewById(R.id.paymentPreAuthReferenceTxt);

        spinnerPaymentMethod = findViewById(R.id.spinnerPaymentMethod);
        spinnerPaymentMethod.setTag(getResources().getStringArray(R.array.decimalAmountModes)[0]);

        if (!installmentsCheck.isChecked()){
            prefInstallmentsLayout.setVisibility(View.GONE);
        }else{
            prefInstallmentsLayout.setVisibility(View.VISIBLE);
        }

        if (!isvCheck.isChecked()){
            ISVFeeLayout.setVisibility(View.GONE);
            ISVClientIdLayout.setVisibility(View.GONE);
            ISVSourceCodeLayout.setVisibility(View.GONE);
            ISVClientSecretLayout.setVisibility(View.GONE);
            ISVMerchantCheck.setVisibility(View.GONE);
        }else{
            ISVFeeLayout.setVisibility(View.VISIBLE);
            ISVClientIdLayout.setVisibility(View.VISIBLE);
            ISVSourceCodeLayout.setVisibility(View.VISIBLE);
            ISVClientSecretLayout.setVisibility(View.VISIBLE);
            ISVMerchantCheck.setVisibility(View.VISIBLE);
        }


        dateFromContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker(true);
            }
        });

        dateToContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker(false);
            }
        });

        installmentsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                prefInstallmentsLayout.setVisibility(b ? View.VISIBLE : View.GONE);
            }
        });

        isvCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ISVFeeLayout.setVisibility(b ? View.VISIBLE : View.GONE);
                ISVClientIdLayout.setVisibility(b ? View.VISIBLE : View.GONE);
                ISVSourceCodeLayout.setVisibility(b ? View.VISIBLE : View.GONE);
                ISVClientSecretLayout.setVisibility(b ? View.VISIBLE : View.GONE);
                ISVMerchantCheck.setVisibility(b ? View.VISIBLE : View.GONE);
            }
        });

        protocolCheck.setOnCheckedChangeListener(
                (compoundButton, b) -> protocolContainer.setVisibility(b ? View.VISIBLE : View.GONE));
        ISVMerchantCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                ISVMerchantIdLayout.setVisibility(isChecked ? View.VISIBLE:View.GONE);
//                ISVMerchantCurrencyLayout.setVisibility(isChecked ? View.VISIBLE:View.GONE);
                ISVMerchantLayout.setVisibility(isChecked ? View.VISIBLE:View.GONE);
            }
        });

        token = findViewById(R.id.token);
        sendTockenBtn = findViewById(R.id.sendTockenBtn);
        getTockenBtn = findViewById(R.id.getTockenBtn);

        gson = new Gson();
        sendTockenBtn.setVisibility(View.GONE);

        // Set up spinnerDecimalAmount
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.decimalAmountModes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDecimalAmountModes.setAdapter(adapter);
        spinnerDecimalAmountModes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedAmountMode = String.valueOf(i);
                if (i == 0) {
                    textDecimalAmountMode.setText("Decimal Amount");
                    btnDecimalAmount.setText("Set Decimal Amount");
                } else {
                    textDecimalAmountMode.setText("No Decimal Amount");
                    btnDecimalAmount.setText("Set No Decimal Amount");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> paymentMethodAdapter = ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.paymentMethod,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerPaymentMethod.setAdapter(paymentMethodAdapter);
        spinnerPaymentMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String[] methods = getResources().getStringArray(R.array.paymentMethod);
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPaymentMethod.setTag(methods[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

    public void posActivationAction(View v){
        String callback = "mycallbackscheme://result";
        String appId = "com.example.myapp";
        String action = "activatePos";

        if(emptyCallback.isChecked()){
            appId = "";
        }
        if(emptyAction.isChecked()){
            action = "";
        }

        String deeplinkPath = "vivapayclient://pay/v1"
                        + "?appId=" + appId
                        + "&action=" + action
                        + "&apikey=" + posActivationClientId.getText().toString()
                        + "&apiSecret=" + posActivationClientSecret.getText().toString()
                        + "&activationCode=" + activationCode.getText().toString()
                        + "&sourceID=" + posActivationSource.getText().toString()
                        + "&pinCode=" + posActivationPinCode.getText().toString()
                        + "&skipExternalDeviceSetup=" + skipExternalDeviceSetup.isChecked()
                        + "&activateMoto=" + activateMoto.isChecked()
                        + "&activateQRCodes=" + activateQR.isChecked()
                        + "&disableManualAmountEntry=" + disableManualAmountEntry.isChecked()
                        + "&forceCardPresentmentForRefund=" + forceCardPresentmentForRefund.isChecked()
                        + "&lockRefund=" + lockRefund.isChecked()
                        + "&lockTransactionsList=" + lockTransactionsList.isChecked()
                        + "&lockMoto=" + lockMoto.isChecked()
                        + "&lockCapture=" + lockCapture.isChecked()
                        + "&lockPreAuth=" + lockPreAuth.isChecked();

        if (protocolCheck.isChecked()) {
            deeplinkPath = deeplinkPath + "&protocol=" + protocolType.getText().toString();
        }

        deeplinkPath = deeplinkPath + "&callback=" + callback;

        Log.d(TAG, "deeplinkPath:" + " " +deeplinkPath);

        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deeplinkPath));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

        try{
            startActivity(payIntent);
        }catch (Exception e){

        }
    }

    public void getActivationCode(View v) {
        String deeplinkPath = "vivapayclient://pay/v1?action=getActivationCode"
                + "&appId=com.example.myapp"
                + "&callback=mycallbackscheme://result";
        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deeplinkPath));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        try {
            startActivity(payIntent);
        } catch (Exception exception) {
            Timber.e(exception.getLocalizedMessage());
        }
    }

    public void saleAction(View v){
        String amountL = "1200";
        String tipL = "0";
        String callback = "mycallbackscheme://result";
        String merchantKey = "12345678909";
        String appId = "com.example.myapp";
        String action = "sale";
        String paymentMethod = (String) spinnerPaymentMethod.getTag();

        if (v.getTag().toString().equalsIgnoreCase("0.5")) {
            amountL = "50";
            tipL = "15";
        } else if (v.getTag().toString().equalsIgnoreCase("55")) {
            amountL = "5500";
        } else if (v.getTag().toString().equalsIgnoreCase("499")) {
            amountL = "499";
        } else if (v.getTag().toString().equalsIgnoreCase("6482")) {
            amountL = "6482";
        } else if (v.getTag().toString().equalsIgnoreCase("6")) {
            amountL = "600";
            callback = "mymissingcallbackscheme://result_missing";
        } else if (v.getTag().toString().equalsIgnoreCase("101")) {
            amountL = "101";
        } else if (v.getTag().toString().equalsIgnoreCase("000")) {
            amountL = amountTxt.getText().toString();
            tipL = tipAmountTxt.getText().toString();
        }

        if(emptyMerchantKey.isChecked()){
            merchantKey = "";
        }
        if(emptyAppId.isChecked()){
            merchantKey = "";
        }
        if(emptyCallback.isChecked()){
            appId = "";
        }
        if(emptyAction.isChecked()){
            action = "";
        }

        boolean installments = installmentsCheck.isChecked();
        boolean receipt = receiptCheck.isChecked();
        boolean rating = ratingCheck.isChecked();
        boolean result = resultCheck.isChecked();
        String prefInstallments = prefInstallmentsTxt.getText().toString();

        String deeplinkPath = "vivapayclient://pay/v1"
                + "?merchantKey=" + merchantKey
                + "&appId=" + appId
                + "&action=" + action
                + "&amount=" + amountL
                + "&tipAmount=" + tipL
                + "&show_receipt=" + receipt
                + "&show_rating=" + rating
                + "&show_transaction_result=" + result
                + "&withInstallments=" + installments
                + "&preferredInstallments=" + prefInstallments
                + "&paymentMethod=" + paymentMethod;

        if (!paymentReferenceTxt.getText().toString().equals("")) {
            deeplinkPath = deeplinkPath + "&clientTransactionId=" + paymentReferenceTxt.getText().toString();
        }

        if (isvCheck.isChecked()) {
            deeplinkPath = deeplinkPath + "&ISV_amount=" + ISVFeeTxt.getText().toString();
            deeplinkPath = deeplinkPath + "&ISV_clientId=" + ISVClientIdTxt.getText().toString();
            deeplinkPath = deeplinkPath + "&ISV_clientSecret=" + ISVClientSecretTxt.getText().toString();
            deeplinkPath = deeplinkPath + "&ISV_sourceCode=" + ISVSourceCodeTxt.getText().toString();
            if(ISVMerchantCheck.isChecked()) {
                deeplinkPath += "&ISV_merchantId=" + ISVMerchantIdTxt.getText().toString();
                deeplinkPath += "&ISV_currencyCode=" + ISVMerchantCurrencyTxt.getText().toString();
                deeplinkPath += "&ISV_merchantSourceCode=" + ISVMerchantSourceCodeTxt.getText().toString();
                deeplinkPath += "&ISV_customerTrns=" + ISVCustomerTrnsTxt.getText().toString();
                deeplinkPath += "&ISV_clientTransactionId=" + ISVClientTransactionId.getText().toString();
            }
        }

        if (protocolCheck.isChecked()) {
            deeplinkPath = deeplinkPath + "&protocol=" + protocolType.getText().toString();
        }

        deeplinkPath = deeplinkPath + "&callback=" + callback;

        Log.d(TAG, "deeplinkPath:" + " " +deeplinkPath);

        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deeplinkPath));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

        try{
            startActivity(payIntent);
        }catch (Exception e){

        }
    }

    public void preAuthAction(View view) {
        String callback = "mycallbackscheme://result";
        String merchantKey = "12345678909";
        String appId = "com.example.myapp";
        String action = "pre_auth";
        String amountL = "000";

        if (amountPreAuthTxt.getText().toString() != null && !amountPreAuthTxt.getText().toString().isEmpty()) {
            amountL = amountPreAuthTxt.getText().toString();
        }

        if(emptyMerchantKey.isChecked()){
            merchantKey = "";
        }
        if(emptyAppId.isChecked()){
            merchantKey = "";
        }
        if(emptyCallback.isChecked()){
            appId = "";
        }
        if(emptyAction.isChecked()){
            action = "";
        }

        boolean receipt = receiptCheck.isChecked();
        boolean rating = ratingCheck.isChecked();
        boolean result = resultCheck.isChecked();

        String deeplinkPath = "vivapayclient://pay/v1"
                + "?merchantKey=" + merchantKey
                + "&appId=" + appId
                + "&action=" + action
                + "&amount=" + amountL
                + "&show_receipt=" + receipt
                + "&show_rating=" + rating
                + "&show_transaction_result=" + result;

        if (!paymentPreAuthReferenceTxt.getText().toString().equals("")) {
            deeplinkPath = deeplinkPath + "&clientTransactionId=" + paymentPreAuthReferenceTxt.getText().toString();
        }

        deeplinkPath = deeplinkPath + "&callback=" + callback;

        Log.d(TAG, "deeplinkPath:" + " " +deeplinkPath);

        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deeplinkPath));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

        try{
            startActivity(payIntent);
        }catch (Exception e){

        }
    }

    public void capturePreAuthTransactionAction(View view) {
        cancelOrPreAuthTransaction("capture_pre_auth");
    }

    public void cancelTransactionAction(View v){
        cancelOrPreAuthTransaction("cancel");
    }

    private void cancelOrPreAuthTransaction(String action) {
        boolean receipt = receiptCheck.isChecked();
        boolean rating = ratingCheck.isChecked();
        boolean result = resultCheck.isChecked();

        String callback = "mycallbackscheme://result";
        String merchantKey = "12345678909";
        String appId = "com.example.myapp";

        if(emptyMerchantKey.isChecked()){
            merchantKey = "";
        }
        if(emptyAppId.isChecked()){
            merchantKey = "";
        }
        if(emptyCallback.isChecked()){
            appId = "";
        }
        if(emptyAction.isChecked()){
            action = "";
        }

        String reqStr =  "vivapayclient://pay/v1"
                + "?merchantKey=" + merchantKey
                + "&appId=" + appId
                + "&action=" + action
                + "&show_receipt=" + receipt
                + "&show_rating=" + rating
                + "&show_transaction_result=" + result
                + "&callback=" + callback;

        if (referenceNumberTxt.getText().toString().length()>0){
            reqStr = reqStr + "&referenceNumber="+ referenceNumberTxt.getText().toString();
        }
        if (dateFrom.getText().length()>0){
            reqStr = reqStr + "&txnDateFrom="+ dateFrom.getText().toString();
        }
        if (dateTo.getText().length()>0){
            reqStr = reqStr + "&txnDateTo="+ dateTo.getText().toString();
        }
        if (orderCode.getText().toString().length()>0){
            reqStr = reqStr + "&orderCode="+ orderCode.getText().toString();
        }
        if (shortOrderCode.getText().toString().length()>0){
            reqStr = reqStr + "&shortOrderCode="+ shortOrderCode.getText().toString();
        }
        if (amountRefund.getText().toString().length()>0){
            reqStr = reqStr + "&amount="+ amountRefund.getText().toString();
        }

        if (protocolCheck.isChecked()) {
            reqStr = reqStr + "&protocol=" + protocolType.getText().toString();
        }

        Log.d(TAG, "deeplinkPath:" + reqStr);
        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse( reqStr));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        //Those two flags should be added for paydroid implementations
//        payIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        payIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(payIntent);
    }


    TextView token;

    Gson gson;
    private HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    Button sendTockenBtn;
    Button getTockenBtn;
    private OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build();

    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private String createBasicAuth(String username, String pass) {
        byte[] b = (username + ":" + pass).getBytes();
        return "Basic " + Base64.encodeToString(b, Base64.NO_WRAP);
    }

    String billPaymentAmount = "2846";
    public void getBillPaymentToken(View v){
        sendTockenBtn.setVisibility(View.GONE);
        getTockenBtn.setVisibility(View.GONE);
        if (amountBill.getText().toString().length() > 0) {
            billPaymentAmount = amountBill.getText().toString();
        }
        String correlationId = correlationId();
        String dummyCall = "{\n" +
                "    \"amount\": " + billPaymentAmount + ",\n" +
                "    \"billId\": 26,\n" +
                "    \"firstName\": \"Aris\",\n" +
                "    \"lastName\": \"Kourbetis\",\n" +
                "    \"merchantTrns\": \"00000000000000177709\",\n" +
                "    \"phone\": \"6973407149\"\n" +
                "}";

        RequestBody body = RequestBody.create(JSON, dummyCall);

        // Replace with your own credentials and base url enpoints
        final Request request = new Request.Builder()
                .addHeader("Authorization", createBasicAuth("DBC3E8CA-D1C2-4485-9226-8FF64D10B18E:51638F3B-4D32-4837-A47D-B6917953A183","DBC3E8CA-D1C2-4485-9226-8FF64D10B18E"))
                .addHeader("X-Viva-CorrelationId", correlationId)
                .url("https://uat.vivapayments.com/api/resellers/pos/bills/transactions/tokens")
                .post(body)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getTockenBtn.setVisibility(View.VISIBLE);
                        sendTockenBtn.setVisibility(View.GONE);
                        token.setText("no token");

                        Toast.makeText(getBaseContext(),"Error getting Token",Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String responseString = null;
                try {
                    responseString = response.body().string();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                try{
                    billResponse = gson.fromJson(responseString,BillResponse.class);
                }catch (Exception e){
                    e.printStackTrace();
                }

                getBillPaymentFees();
            }
        });
    }

    long fees;

    private void getBillPaymentFees() {
        String correlationId = correlationId();

        client.newCall(new Request.Builder()
                .addHeader("Authorization", createBasicAuth("DBC3E8CA-D1C2-4485-9226-8FF64D10B18E:51638F3B-4D32-4837-A47D-B6917953A183", "DBC3E8CA-D1C2-4485-9226-8FF64D10B18E"))
                .addHeader("X-Viva-CorrelationId", correlationId)
                .url("https://uat.vivapayments.com/api/fees?amount=" + billPaymentAmount + "&billid=26&resellersourcecode=default")
                .get()
                .build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getTockenBtn.setVisibility(View.VISIBLE);
                sendTockenBtn.setVisibility(View.GONE);
                token.setText("no token");

                Toast.makeText(getBaseContext(), "Error getting Token", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, final Response response) {
                String responseString = null;
                try {
                    responseString = response.body().string();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                FeesResponse feesResponse = null;
                try {
                    feesResponse = gson.fromJson(responseString, FeesResponse.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (feesResponse != null) {
                    try {
                        long amount = Long.parseLong(billPaymentAmount);
                        fees = (long) (feesResponse.Fee * 100);

                        billPaymentAmount = (amount + fees) + "";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (billResponse != null) {
                            token.setText(billResponse.getPaymentToken());
                            billFees.setText(fees + "");
                            sendTockenBtn.setVisibility(View.VISIBLE);
                            getTockenBtn.setVisibility(View.VISIBLE);
                            Toast.makeText(getBaseContext(), "SUCCESS getting Token", Toast.LENGTH_LONG).show();
                        } else {
                            sendTockenBtn.setVisibility(View.GONE);
                            getTockenBtn.setVisibility(View.VISIBLE);
                            Toast.makeText(getBaseContext(), "Error getting Token ---- null responseStr", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    public String correlationId() {

        Date date = utcDatetimeAsDate();

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        String uuid = UUID.randomUUID().toString();
        String correlationId = String.format("%1$ty-%1$tj-%2$s", calendar, uuid.substring(0,8).toUpperCase());

        Log.d(TAG,"correlationId: " + correlationId);

        return correlationId;
    }

    public void sendBillPaymentToken(View v){
        String tokenStr = token.getText().toString();
        if (tokenStr.equalsIgnoreCase("no token")) {
            Toast.makeText(getBaseContext(), "ErGET TOKEN FIRST", Toast.LENGTH_LONG).show();
        } else {
            boolean receipt = receiptCheck.isChecked();
            boolean rating = ratingCheck.isChecked();
            boolean result = resultCheck.isChecked();

            String callback = "mycallbackscheme://result";
            String merchantKey = "12345678909";
            String appId = "com.example.myapp";
            String action = "billPayment";

            if (emptyMerchantKey.isChecked()) {
                merchantKey = "";
            }
            if (emptyAppId.isChecked()) {
                merchantKey = "";
            }
            if (emptyCallback.isChecked()) {
                appId = "";
            }
            if (emptyAction.isChecked()) {
                action = "";
            }

            String deeplinkPath = "vivapayclient://pay/v1"
                    + "?merchantKey=" + merchantKey
                    + "&appId=" + appId
                    + "&action=" + action
                    + "&billPaymentToken=" + token.getText().toString()
                    + "&amount=" + billPaymentAmount
                    + "&show_receipt=" + receipt
                    + "&show_rating=" + rating
                    + "&show_transaction_result=" + result
                    + "&callback=" + callback;

            if (isvCheck.isChecked()) {
                deeplinkPath = deeplinkPath + "&ISV_amount=" + ISVFeeTxt.getText().toString();
                deeplinkPath = deeplinkPath + "&ISV_clientId=" + ISVClientIdTxt.getText().toString();
                deeplinkPath = deeplinkPath + "&ISV_clientSecret=" + ISVClientSecretTxt.getText().toString();
                deeplinkPath = deeplinkPath + "&ISV_sourceCode=" + ISVSourceCodeTxt.getText().toString();
            }

            Log.d(TAG, "deeplinkPath: " + deeplinkPath);

            Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deeplinkPath));
            payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

            try{
                startActivity(payIntent);
            }catch (Exception e){

            }
        }

    }


    public Date utcDatetimeAsDate()
    {
        //note: doesn't check for null
        return stringDateToDate(utcDatetimeAsString());
    }
    public String utcDatetimeAsString()
    {
        final SimpleDateFormat sdf = new SimpleDateFormat(UTC_DATEFORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String utcTime = sdf.format(new Date());

        return utcTime;
    }
    public Date stringDateToDate(String strDate)
    {
        Date dateToReturn = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(UTC_DATEFORMAT);

        try
        {
            dateToReturn = (Date)dateFormat.parse(strDate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return dateToReturn;
    }

    public void abortButtonTapped(View v) {
        String callback = "mycallbackscheme://result";
        String merchantKey = "12345678909";
        String appId = "com.example.myapp";
        String action = "abort";

        if (emptyMerchantKey.isChecked()) {
            merchantKey = "";
        }
        if (emptyAppId.isChecked()) {
            merchantKey = "";
        }
        if (emptyCallback.isChecked()) {
            appId = "";
        }
        if (emptyAction.isChecked()) {
            action = "";
        }

        String reqStr = "vivapayclient://pay/v1"
                + "?merchantKey=" + merchantKey
                + "&appId=" + appId
                + "&action=" + action
                + "&callback=" + callback;


        Log.d(TAG, "deeplinkPath:" + reqStr);
        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse( reqStr));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(payIntent);
    }

    public void showDateTimePicker(final boolean isStart) {
        if (dialog != null && dialog.isShowing()) {
            return;
        }

        final Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener startListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                showTimePicker(isStart, myCalendar);
            }
        };
        dialog = new DatePickerDialog(this, startListener, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        dialog.show();
    }

    public void showTimePicker(final boolean isStart, final Calendar myCalendar) {
        TimePickerDialog.OnTimeSetListener startListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                myCalendar.set(Calendar.MINUTE, minute);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
                String dateText = df.format(myCalendar.getTimeInMillis());
                if (isStart) {
                    dateFrom.setText(dateText);
                } else {
                    dateTo.setText(dateText);
                }
            }

        };
        new TimePickerDialog(this, startListener, myCalendar
                .get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true).show();
    }

    public void clearDateFrom(View v) {
        dateFrom.setText("");
    }

    public void clearDateTo(View v) {
        dateTo.setText("");
    }


    public void setProtocolType(View v) {
        protocolType.setText((String)v.getTag());
    }

    // Multi-merchant
    public void sendResellerOrder(View v){


        boolean receipt = receiptCheck.isChecked();
        boolean rating = ratingCheck.isChecked();
        boolean result = resultCheck.isChecked();

        String merchId="";
        String merchSource="Default";
        String currency ="978"; // EURO


        if (resellerMerchId.getText().toString().length()>0){
            merchId = resellerMerchId.getText().toString();
        }


        if (currencyCode.getText().toString().length()>0){
            currency = currencyCode.getText().toString();
        }else{
            Toast.makeText(getBaseContext(),"Setting currencyCode 978 (EUR)",Toast.LENGTH_LONG).show();
            currencyCode.setText("978");
        }


        String resellerAmount = resellerAmountTxt.getText().toString();
        String callback = "mycallbackscheme://result";
        String merchantKey = "12345678909";
        String appId = "com.example.myapp";
        String merchTrns = merchantTrnsTxt.getText().toString();
        String action = "mmSale";

        boolean installments = installmentsCheck.isChecked();
        String prefInstallments = prefInstallmentsTxt.getText().toString();

        if (emptyMerchantKey.isChecked()) {
            merchantKey = "";
        }
        if (emptyAppId.isChecked()) {
            merchantKey = "";
        }
        if (emptyCallback.isChecked()) {
            appId = "";
        }
        if (emptyAction.isChecked()) {
            action = "";
        }

        String deeplinkPath = "vivapayclient://pay/v1"
                + "?merchantKey=" + merchantKey
                + "&appId=" + appId
                + "&action=" + action
                + "&amount=" + resellerAmount
                + "&tipAmount=" + resellerTipAmountTxt.getText().toString()
                + "&show_receipt=" + receipt
                + "&show_rating=" + rating
                + "&show_transaction_result=" + result
                + "&multi_merchant_id=" + merchId
                + "&multi_merchant_source_code=" + multimerchantSourceCodeTxt.getText().toString()
                + "&multi_merchant_reseller_source_code=" + resellerSourceCodeTxt.getText().toString()
                + "&currencyCode=" + currency
                + "&clientTransactionId=" + merchTrns
                + "&withInstallments=" + installments
                + "&preferredInstallments=" + prefInstallments
                + "&customerTrns=" + customerTrnsTxt.getText().toString()
                + "&callback=" + callback;

        Log.d(TAG, "deeplinkPath:" + deeplinkPath);

        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deeplinkPath));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

        try{
            startActivity(payIntent);
        }catch (Exception e){

        }

    }

    public void foregroundButtonTapped(View v){
        String callback = "mycallbackscheme://result";
        String merchantKey = "12345678909";
        String appId = "com.example.myapp";
        String action = "foreground";

        if (emptyMerchantKey.isChecked()) {
            merchantKey = "";
        }
        if (emptyAppId.isChecked()) {
            merchantKey = "";
        }
        if (emptyCallback.isChecked()) {
            appId = "";
        }
        if (emptyAction.isChecked()) {
            action = "";
        }
        String deeplinkPath = "vivapayclient://pay/v1"
                + "?merchantKey=" + merchantKey
                + "&appId=" + appId
                + "&action=" + action
                + "&callback=" + callback;

        Log.d(TAG, "deeplinkPath:" + deeplinkPath);

        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deeplinkPath));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(payIntent);

    }

    public void openBatchButtonTapped(View v) {
        String callback = "mycallbackscheme://result";
        String merchantKey = "12345678909";
        String appId = "com.example.myapp";
        String action = "batch";

        if (emptyMerchantKey.isChecked()) {
            merchantKey = "";
        }
        if (emptyAppId.isChecked()) {
            merchantKey = "";
        }
        if (emptyCallback.isChecked()) {
            appId = "";
        }
        if (emptyAction.isChecked()) {
            action = "";
        }

        String deeplinkPath = "vivapayclient://pay/v1"
                + "?merchantKey=" + merchantKey
                + "&appId=" + appId
                + "&action=" + action
                + "&callback=" + callback
                + "&command=" + "open";

        if (batchIdTxt.getText() != null) {
            if (!batchIdTxt.getText().toString().isEmpty()) {
                deeplinkPath = deeplinkPath
                        + "&batchId=" + batchIdTxt.getText().toString();
            }
        }

        if (batchNameTxt.getText() != null) {
            if (!batchNameTxt.getText().toString().isEmpty()) {
                deeplinkPath = deeplinkPath
                        + "&batchName=" + batchNameTxt.getText().toString();
            }
        }

        Log.d(TAG, "deeplinkPath:" + deeplinkPath);

        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deeplinkPath));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(payIntent);
    }

    public void closeBatchButtonTapped(View v) {
        String callback = "mycallbackscheme://result";
        String merchantKey = "12345678909";
        String appId = "com.example.myapp";
        String action = "batch";

        if (emptyMerchantKey.isChecked()) {
            merchantKey = "";
        }
        if (emptyAppId.isChecked()) {
            merchantKey = "";
        }
        if (emptyCallback.isChecked()) {
            appId = "";
        }
        if (emptyAction.isChecked()) {
            action = "";
        }

        String deeplinkPath = "vivapayclient://pay/v1"
                + "?merchantKey=" + merchantKey
                + "&appId=" + appId
                + "&action=" + action
                + "&callback=" + callback
                + "&command=" + "close";

        if (batchIdTxt.getText() != null) {
            if (!batchIdTxt.getText().toString().isEmpty()) {
                deeplinkPath = deeplinkPath
                        + "&batchId=" + batchIdTxt.getText().toString();
            }
        }

        if (batchNameTxt.getText() != null) {
            if (!batchNameTxt.getText().toString().isEmpty()) {
                deeplinkPath = deeplinkPath
                        + "&batchName=" + batchNameTxt.getText().toString();
            }
        }

        Log.d(TAG, "deeplinkPath:" + deeplinkPath);

        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deeplinkPath));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(payIntent);
    }

    public void sendLogsButtonTapped(View v) {
        String callback = "mycallbackscheme://result";
        String merchantKey = "12345678909";
        String appId = "com.example.myapp";
        String action = "sendLogs";

        if (emptyCallback.isChecked()) {
            appId = "";
        }

        if (emptyAction.isChecked()) {
            action = "";
        }

        String deeplinkPath = "vivapayclient://pay/v1"
                + "?merchantKey=" + merchantKey
                + "&appId=" + appId
                + "&action=" + action
                + "&callback=" + callback;

        Log.d(TAG, "deeplinkPath:" + deeplinkPath);

        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deeplinkPath));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(payIntent);
    }

    public void tempBtn(View v){
        String callback = "mycallbackscheme://result";

//        String req = "vivapayclient://pay/v1?merchantKey=SG23323424EXS3&appId=com.frob.vivawallet.app&action=sale&clientTransactionId=000000000001019781&amount=1&tipAmount=0&withInstallments=false&preferredInstallments=0&callback=vivaproxy://result";
        String req = "vivapayclient://pay/v1?merchantKey=SG23323424EXS3&appId=com.frob.vivawallet.app&action=sale&clientTransactionId=000000000001019781&amount=1&tipAmount=0&withInstallments=false&preferredInstallments=0&callback=" + callback;
        Log.d(TAG, "deeplinkPath:" + req);

        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(req));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

        try{
            startActivity(payIntent);
        }catch (Exception e){

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_unattendedModes) {
            loadFragment(UnattendedFragment.newInstance());
            return true;
        } else if (item.getItemId() == R.id.action_printingSettings) {
            loadFragment(PrintingSettingsFragment.newInstance());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        containerView.setVisibility(View.GONE);
    }

    private void  loadFragment(Fragment fragment){
        containerView.setVisibility(View.VISIBLE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment, null)
                .setReorderingAllowed(true)
                .addToBackStack("name") // name can be null
                .commit();

    }

    public void reprintTransaction(View view) {
        String callback = "mycallbackscheme://result";
        String merchantKey = "12345678909";
        String appId = "com.example.myapp";
        String action = "print";

        if (emptyMerchantKey.isChecked()) {
            merchantKey = "";
        }
        if (emptyAppId.isChecked()) {
            merchantKey = "";
        }
        if (emptyCallback.isChecked()) {
            appId = "";
        }
        if (emptyAction.isChecked()) {
            action = "";
        }

        String deeplinkPath = "vivapayclient://pay/v1"
                + "?merchantKey=" + merchantKey
                + "&appId=" + appId
                + "&action=" + action
                + "&callback=" + callback
                + "&command=" + "reprint";

        if (reprintTransactionOrderCode.getText() != null) {
            if (!reprintTransactionOrderCode.getText().toString().isEmpty()) {
                deeplinkPath = deeplinkPath
                        + "&orderCode=" + reprintTransactionOrderCode.getText().toString();
            }
        }

        Log.d(TAG, "deeplinkPath:" + deeplinkPath);

        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deeplinkPath));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(payIntent);
    }

    public void getTransactionDetails(View view) {
        String callback = "mycallbackscheme://result";
        String merchantKey = "12345678909";
        String appId = "com.example.myapp";
        String action = "transactionDetails";

        if (emptyMerchantKey.isChecked()) {
            merchantKey = "";
        }
        if (emptyAppId.isChecked()) {
            merchantKey = "";
        }
        if (emptyCallback.isChecked()) {
            appId = "";
        }
        if (emptyAction.isChecked()) {
            action = "";
        }

        String deeplinkPath = "vivapayclient://pay/v1"
                + "?merchantKey=" + merchantKey
                + "&appId=" + appId
                + "&action=" + action
                + "&callback=" + callback;

        if (transactionDetailsClientTransactionId.getText() != null) {
            if (!transactionDetailsClientTransactionId.getText().toString().isEmpty()) {
                deeplinkPath = deeplinkPath
                        + "&clientTransactionId=" + transactionDetailsClientTransactionId.getText().toString();
            }
        }

        if (transactionDetailsSourceTerminalId.getText() != null) {
            if (!transactionDetailsSourceTerminalId.getText().toString().isEmpty()) {
                deeplinkPath = deeplinkPath
                        + "&sourceTerminalId=" + transactionDetailsSourceTerminalId.getText().toString();
            }
        }

        Log.d(TAG, "deeplinkPath:" + deeplinkPath);

        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deeplinkPath));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(payIntent);
    }

    public void setDecimalAmount(View view) {
        String callback = "mycallbackscheme://result";
        String merchantKey = "12345678909";
        String appId = "com.example.myapp";
        String action = "amountDecimalMode";

        if (emptyMerchantKey.isChecked()) {
            merchantKey = "";
        }
        if (emptyAppId.isChecked()) {
            merchantKey = "";
        }
        if (emptyCallback.isChecked()) {
            appId = "";
        }
        if (emptyAction.isChecked()) {
            action = "";
        }

        String deeplinkPath = "vivapayclient://pay/v1"
                + "?merchantKey=" + merchantKey
                + "&appId=" + appId
                + "&action=" + action
                + "&callback=" + callback
                + "&decimalMode=" + selectedAmountMode;

        Log.d(TAG, "deeplinkPath:" + deeplinkPath);

        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deeplinkPath));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(payIntent);
    }

    public void softReset(View v){
        String deeplinkPath = getResetBaseRequest()
                + "&softReset=" + true;

        Log.d(TAG, "deeplinkPath:" + deeplinkPath);

        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deeplinkPath));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(payIntent);
    }

    public void fullReset(View v){

        String deeplinkPath = getResetBaseRequest()
                + "&softReset=" + false;

        Log.d(TAG, "deeplinkPath:" + deeplinkPath);

        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deeplinkPath));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(payIntent);
    }

    private String getResetBaseRequest(){
        String callback = "mycallbackscheme://result";
        String merchantKey = "12345678909";
        String appId = "com.example.myapp";
        String action = "reset";

        return  "vivapayclient://pay/v1"
                + "?merchantKey=" + merchantKey
                + "&appId=" + appId
                + "&action=" + action
                + "&callback=" + callback;
    }
}
