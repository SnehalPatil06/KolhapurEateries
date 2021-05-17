package com.example.loginactivity.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginactivity.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    Button registerBtn;
    EditText nameEdit, addressEdit, phoneEdit, emailEdit, passwordEdit;
    String name, address, phone, email, password;

    FirebaseAuth mAuth;
    FirebaseFirestore mStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerBtn = findViewById(R.id.register_btn);
        nameEdit = findViewById(R.id.fullName);
        addressEdit = findViewById(R.id.address_register);
        phoneEdit = findViewById(R.id.phone_register);
        emailEdit = findViewById(R.id.email_register);
        passwordEdit = findViewById(R.id.passwrod_register);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = nameEdit.getText().toString().trim();
                address = addressEdit.getText().toString();
                phone = phoneEdit.getText().toString().trim();
                email = emailEdit.getText().toString().trim();
                password = passwordEdit.getText().toString().trim();

                if(name.isEmpty()){
                    nameEdit.setError("Name is required!");
                    return;
                }

                if(address.isEmpty()){
                    addressEdit.setError("Enter valid address!");
                    return;
                }

                if(phone.isEmpty()){
                    phoneEdit.setError("Enter valid phone!");
                    return;
                }

                if(email.isEmpty()){
                    emailEdit.setError("Enter valid email!");
                    return;
                }

                if(password.isEmpty()){
                    passwordEdit.setError("Enter password!");
                    return;
                }
                if(password.length() < 6){
                    passwordEdit.setError("password length should be more than 6!");
                    return;
                }

                mAuth = FirebaseAuth.getInstance();
                mStore = FirebaseFirestore.getInstance();

                mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        FirebaseUser user = mAuth.getCurrentUser();
                        //send to next page
                        Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();

                        DocumentReference df = mStore.collection("userAuthentication").document(user.getUid());

                        Map<String, Object> userInfo = new HashMap<>();
                        userInfo.put("FullName",name);
                        userInfo.put("Address", address);
                        userInfo.put("PhoneNumber", phone);
                        userInfo.put("UserEmail", email);
                        userInfo.put("imageUrl", "");
                        userInfo.put("isUser", "0");


                        df.set(userInfo);


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @org.jetbrains.annotations.NotNull Exception e) {
                        Toast.makeText(Register.this, "Unable to create user, please try again!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }



}