package com.example.hingolifood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.hingolifood.databinding.ActivityOtpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class OtpActivity extends AppCompatActivity {

    ActivityOtpBinding binding;
    String getotpBackend;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        getotpBackend = getIntent().getStringExtra("backOtp");

        numberoptmove();
        binding.buttongetotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!binding.inputotp1.getText().toString().trim().isEmpty() && !binding.inputotp2.getText().toString().trim().isEmpty()
               && !binding.inputotp3.getText().toString().trim().isEmpty() && !binding.inputotp4.getText().toString().trim().isEmpty()){
                    String entercodeotp = binding.inputotp1.getText().toString() +
                            binding.inputotp2.getText().toString() +
                            binding.inputotp3.getText().toString() +
                            binding.inputotp4.getText().toString() +
                            binding.inputotp5.getText().toString() +
                            binding.inputotp6.getText().toString();

                    if (getotpBackend!=null){
                        binding.progressbarSendingOtp.setVisibility(View.VISIBLE);
                        binding.buttongetotp.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getotpBackend,entercodeotp
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                   binding.progressbarSendingOtp.setVisibility(View.GONE);
                                   binding.buttongetotp.setVisibility(View.VISIBLE);

                                   if (task.isSuccessful()){
                                       Intent intent = new Intent(getApplicationContext(),SetupProfileActivity.class);
                                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                       startActivity(intent);
                                       finish();
                                   }else {
                                       Toast.makeText(getApplicationContext(), "enter correct otp", Toast.LENGTH_SHORT).show();
                                   }
                                    }
                                });

                    }else {
                        Toast.makeText(getApplicationContext(), "pleas check intenet connection", Toast.LENGTH_SHORT).show();
                    }
//                    Toast.makeText(getApplicationContext(), "otp verify", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "please enter all number", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    public void numberoptmove(){
        binding.inputotp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    binding.inputotp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.inputotp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    binding.inputotp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.inputotp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    binding.inputotp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.inputotp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    binding.inputotp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.inputotp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    binding.inputotp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

}