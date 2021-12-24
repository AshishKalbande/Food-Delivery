package com.example.hingolifood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hingolifood.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {


    ActivityRegisterBinding binding;
    String name, email, password;
    //
    private FirebaseAuth auth;
    private DatabaseReference reference;
    private DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();


        binding.loginLink.setOnClickListener(v -> {
            startActivity(new Intent(this,mLActivity.class));
        });

        binding.btnRegister.setOnClickListener(v -> {
            validateData();
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null) {
            openMain();
        }
    }

    private void openMain() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    private void validateData(){
        name = binding.etname.getText().toString();
        email = binding.etemail.getText().toString();
        password = binding.etpassword.getText().toString();

        if (name.isEmpty()){
            binding.etname.setError("Pls Enter Name");
            binding.etname.requestFocus();
        }
        else if (email.isEmpty()){
            binding.etemail.setError("pls enter email");
            binding.etemail.requestFocus();
        }else if(password.isEmpty()){
            binding.etpassword.setError("pls enter password");
            binding.etpassword.requestFocus();
        }else {
            createUser();
        }

    }

    private void createUser() {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            uploadData();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void uploadData() {

        dbref = reference.child("users");
        // key get krne ke liey
        String Key = dbref.push().getKey();

        HashMap<String, String > user = new HashMap<>();
        user.put("key",Key);
        user.put("name",name);
        user.put("email",email);

        dbref.child(Key).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "user created", Toast.LENGTH_SHORT).show();
                            openMain();
                        }else {
                            Toast.makeText(RegisterActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull  Exception e) {
                Toast.makeText(RegisterActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }
}

