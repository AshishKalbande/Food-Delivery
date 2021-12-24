package com.example.hingolifood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hingolifood.databinding.ActivityUserProfileBinding;
import com.example.hingolifood.models.users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class UserProfile extends AppCompatActivity {


    ActivityUserProfileBinding binding;
//    FirebaseUser user;


    FirebaseAuth fAuth;
    private static FirebaseUser fUser;
//    private static FirebaseDatabase firebaseDatabase;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference("Profile");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user_profile);

        fAuth = FirebaseAuth.getInstance();

        readData();


        binding.setProfile.setOnClickListener(v -> {
            startActivity(new Intent(UserProfile.this,SetupProfileActivity.class));
        });




    }
    private void readData(){
        databaseReference = FirebaseDatabase.getInstance().getReference("Profile");
        databaseReference.child(fAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){
//                    Toast.makeText(UserProfile.this, "Suceesfully", Toast.LENGTH_SHORT).show();
                    DataSnapshot dataSnapshot = task.getResult();
                    String username = String.valueOf(dataSnapshot.child("name").getValue());
                    String phone = String.valueOf(dataSnapshot.child("Phone").getValue());
//                    String ProfilePIC = String.valueOf(dataSnapshot.child("profilImage").getValue());
//                    String Email = String.valueOf(dataSnapshot.child("email").getValue());
//                    String password = String.valueOf(dataSnapshot.child("password").getValue());

                    binding.username.setText(username);
                    binding.phone.setText(phone);
//                    binding.ImgProfile.setImageURI(Uri.parse(ProfilePIC));
                }else {
                    Toast.makeText(UserProfile.this, "Failed to read", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}