package com.vivawallet.demopaymentapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.vivawallet.demopaymentapp.R;
import com.vivawallet.demopaymentapp.databinding.FragmentPrintingSettingsBinding;
import com.vivawallet.demopaymentapp.databinding.FragmentUnattendedBinding;
import timber.log.Timber;


public class PrintingSettingsFragment extends Fragment {

    FragmentPrintingSettingsBinding binding;

    public static PrintingSettingsFragment newInstance() {
        return new PrintingSettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentPrintingSettingsBinding.inflate(inflater, container, false);

        binding.setPrintingSettingsBtn.setOnClickListener(v -> setPrintingSettings());
        binding.getPrintingSettingsBtn.setOnClickListener(v -> getPrintingSettings());
        binding.businessName.setOnClickListener(v -> setBusinessDescriptionType((String) v.getTag()));
        binding.storeName.setOnClickListener(v -> setBusinessDescriptionType((String) v.getTag()));
        binding.tradeName.setOnClickListener(v -> setBusinessDescriptionType((String) v.getTag()));

        return binding.getRoot();
    }


    public void setPrintingSettings() {
        String callback = "mycallbackscheme://result";
        String merchantKey = "12345678909";
        String appId = "com.example.myapp";
        String action = "set_printing_settings";

        if (binding.emptyMerchantKey.isChecked()) {
            merchantKey = "";
        }
        if (binding.emptyAppId.isChecked()) {
            merchantKey = "";
        }
        if (binding.emptyCallback.isChecked()) {
            appId = "";
        }
        if (binding.emptyAction.isChecked()) {
            action = "";
        }

        String reqStr = "vivapayclient://pay/v1"
                + "?merchantKey=" + merchantKey
                + "&appId=" + appId
                + "&action=" + action
                + "&callback=" + callback
                + "&businessDescriptionEnabled=" + binding.printBusinessDescription.isChecked()
                + "&businessDescriptionType=" + binding.businessDescriptionType.getText().toString()
                + "&printLogoOnMerchantReceipt=" + binding.logoOnMerchantReceipt.isChecked()
                + "&printVATOnMerchantReceipt=" + binding.vatOnMerchantReceipt.isChecked()
                + "&isBarcodeEnabled=" + binding.printOrderCode.isChecked()
                + "&printAddressOnReceipt=" + binding.printAddress.isChecked()
                + "&isMerchantReceiptEnabled=" + binding.printMerchantReceipt.isChecked()
                + "&isCustomerReceiptEnabled=" + binding.printCustomerReceipt.isChecked();

        Timber.d(action + " " + reqStr);
        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(reqStr));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(payIntent);
    }


    public void getPrintingSettings() {
        String callback = "mycallbackscheme://result";
        String merchantKey = "12345678909";
        String appId = "com.example.myapp";
        String action = "getPrintingSettings";

        if (binding.emptyMerchantKey.isChecked()) {
            merchantKey = "";
        }
        if (binding.emptyAppId.isChecked()) {
            merchantKey = "";
        }
        if (binding.emptyCallback.isChecked()) {
            appId = "";
        }
        if (binding.emptyAction.isChecked()) {
            action = "";
        }

        String reqStr = "vivapayclient://pay/v1"
                + "?merchantKey=" + merchantKey
                + "&appId=" + appId
                + "&action=" + action
                + "&callback=" + callback;

        Timber.d(action + " " + reqStr);
        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(reqStr));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(payIntent);
    }


    public void setBusinessDescriptionType(String input) {
        binding.businessDescriptionType.setText(input);
    }
}