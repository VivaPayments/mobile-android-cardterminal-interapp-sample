package com.vivawallet.demopaymentapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.vivawallet.demopaymentapp.MainActivity;
import com.vivawallet.demopaymentapp.R;
import com.vivawallet.demopaymentapp.databinding.FragmentUnattendedBinding;

import java.util.HashMap;
import java.util.List;

import timber.log.Timber;

/**
 * Unattended
 * action=setMode?parameter=0,1,2
 */
public class UnattendedFragment extends Fragment {
    FragmentUnattendedBinding binding;
    String selectedMode;

    public static UnattendedFragment newInstance() {
        return new UnattendedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUnattendedBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireActivity(),
                R.array.unattendedModes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerModes.setAdapter(adapter);
        binding.spinnerModes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMode = String.valueOf(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.btnSendUnattended.setOnClickListener(view1 -> {
            sendUnattendedMode(selectedMode);
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void sendUnattendedMode(String mode) {
        String callback = "mycallbackscheme://result";
        String merchantKey = "12345678909";
        String appId = "com.example.myapp";
        String action = "setMode";
        String pin = "";

        if (binding.pinEditText.getText() != null &&
                !binding.pinEditText.getText().toString().isEmpty()) {
            pin = "&pin=" + binding.pinEditText.getText().toString().trim();
        }

        String deeplinkPath = "vivapayclient://pay/v1"
                + "?merchantKey=" + merchantKey
                + "&appId=" + appId
                + "&action=" + action
                + "&mode=" + mode
                + pin
                + "&callback=" + callback;

        Timber.i("Deep link unattended Mode: %s", deeplinkPath);
        Intent payIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deeplinkPath));
        payIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        payIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(payIntent);
    }

}