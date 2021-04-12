package com.vivawallet.demopaymentapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;

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

public class MainActivity extends AppCompatActivity {

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
    DatePickerDialog dialog;
    TextView dateFrom;
    TextView billFees;
    TextView dateTo;
    LinearLayout dateToContainer;
    LinearLayout dateFromContainer;
    LinearLayout prefInstallmentsLayout;
    EditText prefInstallmentsTxt;
    EditText orderCode;
    EditText shortOrderCode;
    EditText amountRefund;
    EditText amountBill;

    BillResponse billResponse = null;
    static final String UTC_DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emptyMerchantKey = findViewById(R.id.emptyMerchantKey);
        emptyAppId = findViewById(R.id.emptyAppId);
        emptyCallback = findViewById(R.id.emptyCallback);
        emptyAction = findViewById(R.id.emptyAction);
        amountTxt = findViewById(R.id.amountTxt);
        tipAmountTxt = findViewById(R.id.tipAmountTxt);
        refTxt = findViewById(R.id.refTxt);
        referenceNumberTxt = findViewById(R.id.stanTxt);
        installmentsCheck = findViewById(R.id.installmentsCheck);
        receiptCheck = findViewById(R.id.show_receipt);
        ratingCheck = findViewById(R.id.show_rating);
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

        if (!installmentsCheck.isChecked()){
            prefInstallmentsLayout.setVisibility(View.GONE);
        }else{
            prefInstallmentsLayout.setVisibility(View.VISIBLE);
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

        token = findViewById(R.id.token);
        sendTockenBtn = findViewById(R.id.sendTockenBtn);
        getTockenBtn = findViewById(R.id.getTockenBtn);

        gson = new Gson();
        sendTockenBtn.setVisibility(View.GONE);

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
        String amountL = "1200";
        String tipL = "0";
        String callback = "mycallbackscheme://result";
        String merchantKey = "12345678909";
        String appId = "com.example.myapp";
        String action = "sale";

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
        String prefInstallments = prefInstallmentsTxt.getText().toString();

        String deeplinkPath = "vivapayclient://pay/v1"
                + "?merchantKey=" + merchantKey
                + "&appId=" + appId
                + "&action=" + action
                + "&clientTransactionId=1234567801234"
                + "&amount=" + amountL
                + "&tipAmount=" + tipL
                + "&show_receipt=" + receipt
                + "&show_rating=" + rating
                + "&withInstallments=" + installments
                + "&preferredInstallments=" + prefInstallments
                + "&callback=" + callback;

        Log.d("deeplinkPath:", deeplinkPath);

        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deeplinkPath));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

        try{
            startActivity(payIntent);
        }catch (Exception e){

        }
    }
    public void cancelTransactionAction(View v){


        boolean receipt = receiptCheck.isChecked();
        boolean rating = ratingCheck.isChecked();

        String callback = "mycallbackscheme://result";
        String merchantKey = "12345678909";
        String appId = "com.example.myapp";
        String action = "cancel";

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

        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse( reqStr));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
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

        Log.d("correlationId","correlationId: " + correlationId);

        return correlationId;
    }

    public void sendBillPaymentToken(View v){
        String tokenStr = token.getText().toString();
        if (tokenStr.equalsIgnoreCase("no token")) {
            Toast.makeText(getBaseContext(), "ErGET TOKEN FIRST", Toast.LENGTH_LONG).show();
        } else {
            boolean receipt = receiptCheck.isChecked();
            boolean rating = ratingCheck.isChecked();

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
                    + "&callback=" + callback;

            Log.d("deeplinkPath:", deeplinkPath);

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
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
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
}
