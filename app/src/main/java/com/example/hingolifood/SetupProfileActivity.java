package com.example.hingolifood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hingolifood.databinding.ActivitySetupProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;

public class SetupProfileActivity extends AppCompatActivity {

    ActivitySetupProfileBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;
//    FirebaseStorage storage;
    Uri selectedImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetupProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();
//        reference = FirebaseDatabase.getInstance().getReference("profilImage");
        auth = FirebaseAuth.getInstance();



        binding.setProfile.setOnClickListener(v -> {
            addUserProfile();
        });
        binding.updateProfile.setOnClickListener(v -> {
            UpdateUserProfile();
        });
        binding.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,45);
            }
        });


    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (auth.getCurrentUser() != null){
//            Intent intent = new Intent(SetupProfileActivity.this,MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode ==45 && data != null && data.getData() != null){
//                binding.imageview.setImageURI(data.getData());
                selectedImage = data.getData();
                binding.imageview.setImageURI(data.getData());
                selectedImage = data.getData();

        }
    }

    public void addUserProfile(){
        String name = binding.name.getText().toString();
        if (name.isEmpty()){
            binding.name.setError("plase enter name");
            return;
        }
//        if (selectedImage != null){
            HashMap<String,Object> m = new HashMap<String, Object>();
            m.put("profilImage",selectedImage.toString());
            m.put("name",binding.name.getText().toString());
            m.put("Phone",auth.getCurrentUser().getPhoneNumber());
            m.put("uid",auth.getUid());

            FirebaseDatabase.getInstance().getReference("Profile").child(auth.getUid()).setValue(m);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
//
                    Intent intent = new Intent(SetupProfileActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
//        }
    }

    // end add profile
    //start update profile
    public void UpdateUserProfile() {
        String name = binding.name.getText().toString();
        if (name.isEmpty()) {
            binding.name.setError("plase enter name");
            return;
        }
//        if (selectedImage != null) {
            HashMap<String, Object> m = new HashMap<String, Object>();
            m.put("profilImage", selectedImage.toString());
            m.put("name", binding.name.getText().toString());
            m.put("Phone", auth.getCurrentUser().getPhoneNumber());

            FirebaseDatabase.getInstance().getReference("Profile").child(auth.getUid()).updateChildren(m)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent intent = new Intent(SetupProfileActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(SetupProfileActivity.this, "update Succesfully", Toast.LENGTH_SHORT).show();

                            }else {
                                Toast.makeText(SetupProfileActivity.this, "Failed to update", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
//    }

}