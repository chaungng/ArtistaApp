package com.example.artistaapp.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.artistaapp.R;
import com.example.artistaapp.login.LoginActivity;

public class SignOutFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "SignOutFragment";
    private AppCompatButton btnYes, btnNo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signout, container, false);
        btnYes = view.findViewById(R.id.btn_yes);
        btnNo = view.findViewById(R.id.btn_no);

        // Set onClick Listener
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btnNo) {
            getActivity().onBackPressed();
        }

        if (v == btnYes) {
            getActivity().finish();

            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }
}
