package com.example.hingolifood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hingolifood.databinding.ActivityPhoneBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneActivity extends AppCompatActivity {

    ActivityPhoneBinding binding;
    private FirebaseAuth auth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;

    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null){
            Intent intent = new Intent(PhoneActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

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
                                PhoneActivity.this,
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
                                        Toast.makeText(PhoneActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        super.onCodeSent(backOtp, forceResendingToken);
                                        binding.progressbarSendingOtp.setVisibility(View.GONE);
                                        binding.buttongetotp.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent(getApplicationContext(),OtpActivity.class)
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
            }
        });

//        binding.sendOtpBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String country_code = binding.CountryEt.getText().toString();
//                String phone = binding.phoneNumberEt.getText().toString();
//                String phonNumber = "+" + country_code+""+phone;
//
//                if (!country_code.isEmpty() || !phone.isEmpty()){
//                    PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
//                            .setPhoneNumber(phonNumber)
//                            .setTimeout(60L, TimeUnit.SECONDS)
//                            .setActivity(PhoneActivity.this)
//                            .setCallbacks(mCallBacks)
//                            .build();
//                    PhoneAuthProvider.verifyPhoneNumber(options);
//                }else {
//                    Toast.makeText(getApplicationContext(), "Plsese Enter Country Code", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        mCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//            @Override
//            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//               signIn(phoneAuthCredential);
//            }
//
//            @Override
//            public void onVerificationFailed(@NonNull FirebaseException e) {
//
//                binding.verifyagain.setText(e.getMessage());
//                binding.verifyagain.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                super.onCodeSent(s, forceResendingToken);
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent optIntent = new Intent(PhoneActivity.this,OtpActivity.class);
//                        optIntent.putExtra("auth",s);
//                        startActivity(optIntent);
//                    }
//                },10000);
//                binding.verifyagain.setText("OTP has been Sent");
//                binding.verifyagain.setTextColor(Color.RED);
//                binding.verifyagain.setText(View.VISIBLE);
//
//
//            }
//        };



    }
//    private void sendToMain(){
//        startActivity(new Intent(PhoneActivity.this,MainActivity.class));
//        finish();
//    }

//    private void signIn(PhoneAuthCredential credential){
//        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()){
//                    sendToMain();
//                }else {
//                    binding.verifyagain.setText("OTP has been Sent");
//                    binding.verifyagain.setTextColor(Color.RED);
//                    binding.verifyagain.setText(View.VISIBLE);
//
//                }
//            }
//        });

//    }
}