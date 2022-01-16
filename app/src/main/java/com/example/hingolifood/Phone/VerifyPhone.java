package com.example.hingolifood.Phone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hingolifood.databinding.ActivityVerifyPhoneBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyPhone extends AppCompatActivity {

    ActivityVerifyPhoneBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyPhoneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.inputMobileNumber.requestFocus();

        binding.buttongetotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!binding.inputMobileNumber.getText().toString().trim().isEmpty()){
                    if ((binding.inputMobileNumber.getText().toString()).length() == 10){

                        binding.progressbarSendingOtp.setVisibility(View.VISIBLE);
                        binding.buttongetotp.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + binding.inputMobileNumber.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                VerifyPhone.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        binding.progressbarSendingOtp.setVisibility(View.GONE);
                                        binding.buttongetotp.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        binding.progressbarSendingOtp.setVisibility(View.GONE);
                                        binding.buttongetotp.setVisibility(View.VISIBLE);
                                        Toast.makeText(VerifyPhone.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        super.onCodeSent(backOtp, forceResendingToken);
                                        binding.progressbarSendingOtp.setVisibility(View.GONE);
                                        binding.buttongetotp.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent(getApplicationContext(), VerifyOtpActivity.class)
                                                .putExtra("backOtp",backOtp);
                                        startActivity(intent);
                                    }
                                }
                        );
//
                    }else {
                        Toast.makeText(getApplicationContext(), "Please enter correct number", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Enter Mobile number", Toast.LENGTH_SHORT).show();
                }

//                Intent intent = new Intent(VerifyPhone.this,VerifyOtpActivity.class);
//                intent.putExtra("phoneNumber",binding.CountryEt.getText().toString() + binding.inputMobileNumber.getText().toString());
//                startActivity(intent);
            }
        });
    }
}