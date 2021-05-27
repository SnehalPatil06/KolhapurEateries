package com.example.loginactivity.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginactivity.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ProducerRegistration extends AppCompatActivity {
    EditText name, email, mobile, ifsc, account_number, address, upi, password, city, state;

    Button button_register;
    ImageView location_image;
    FusedLocationProviderClient fusedLocationProviderClient;

    private FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_registration);

        name = findViewById(R.id.fullName);
        address = findViewById(R.id.address_register);
        mobile = findViewById(R.id.phone_register);

        location_image = findViewById(R.id.locationimageID);
        email = findViewById(R.id.email_register);
        password = findViewById(R.id.passwrod_register);

        upi = findViewById(R.id.upiID);
        ifsc = findViewById(R.id.ifsc);
        account_number = findViewById(R.id.accountNumberID);


        city = findViewById(R.id.cityRegister);
        state = findViewById(R.id.stateRegister);

        button_register = findViewById(R.id.register_btn);
        progressBar = findViewById(R.id.progressbarID);

        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString().trim();
                String address1 = address.getText().toString().trim();
                String mobile1 = mobile.getText().toString();
                String email1 = email.getText().toString().trim();
                String password1 = password.getText().toString().trim();
                String upi1 = upi.getText().toString().trim();
                String ifsc1 = ifsc.getText().toString().trim();
                String account_number1 = account_number.getText().toString().trim();

                if (name1.isEmpty()) {
                    name.setError("Please Enter Name!");
                    return;
                }

                if (address1.isEmpty()) {
                    address.setError("Please Enter Address!");
                    return;

                }

                if (mobile1.isEmpty()) {
                    mobile.setError("Please Enter Mobile !");
                    return;

                }
                if (email1.isEmpty()) {
                    email.setError("Please Enter Email!");
                    return;

                }
                if (password1.isEmpty()) {
                    password.setError("Please Enter Password!");
                    return;

                }
                if (upi1.isEmpty()) {
                    upi.setError("Please Enter UPI ID!");
                    return;

                }
                if (ifsc1.isEmpty()) {
                    ifsc.setError("Please Enter IFSC Code!");
                    return;

                }
                if (account_number1.isEmpty()) {
                    account_number.setError("Please Enter Account Number!");
                    return;

                }
                if (password1.length() < 6) {
                    password.setError("Length should greater than 6");
                    return;

                }

                if (mobile1.length() < 10) {
                    mobile.setError("Enter valid number");
                    return;

                }


//Create New Registration , using email & Password
                mAuth.createUserWithEmailAndPassword(email1, password1).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(ProducerRegistration.this, "Registration Success", Toast.LENGTH_SHORT).show();

                        DocumentReference df = fstore.collection("producerAuthentications").document(mAuth.getCurrentUser().getUid());

                        Map<String, Object> values = new HashMap<>();
                        values.put("FullName", name1);
                        values.put("Address", address1);
                        values.put("Phone", mobile1);
                        values.put("Email", email1);
                        values.put("Password", password1);
                        values.put("IFSC", ifsc1);
                        values.put("UPI", upi1);
                        values.put("Account", account_number1);
                        values.put("isAdmin", "1");

                        df.set(values);

                        checkUserAccessLevel(authResult.getUser().getUid());

                        progressBar.setVisibility(View.VISIBLE);
                        // new Delayed().getDelay(20)
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(getApplicationContext(), Login.class));
                                finish();
                                progressBar.setVisibility(View.GONE);
                            }
                        }, 2000);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(ProducerRegistration.this, "Registration Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


}

    private void checkUserAccessLevel(String uid)
    {
        DocumentReference df = fstore.collection("Producer").document(uid);

        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "OnSuccess: "+ documentSnapshot.getData());

                if(documentSnapshot.getString("isAdmin")!= null)
                {
                    Toast.makeText(ProducerRegistration.this, "Login as admin", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });


    }



    @SuppressLint("MissingPermission")
    private void getLocation() {

     //   Toast.makeText(this, "Clicked get location", Toast.LENGTH_SHORT).show();
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Location> task) {
                Location location = task.getResult();
                if(location != null){

                    try {

                        progressBar.setVisibility(View.VISIBLE);

                        Toast.makeText(ProducerRegistration.this, "Your current Location", Toast.LENGTH_SHORT).show();

                        Geocoder geocoder = new Geocoder(ProducerRegistration.this,
                                Locale.getDefault());

                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude()
                                , location.getLongitude(), 1);

                        city.setText(addresses.get(0).getLocality());
                        state.setText(addresses.get(0).getCountryName());
                        address.setText(addresses.get(0).getAddressLine(0));

                        progressBar.setVisibility(View.GONE);


                    }catch (Exception e){
                        e.printStackTrace();
//                        Toast.makeText(Register.this, "boogle boogle boogle", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });


    }

    public void getLocation(View view) {

        //check permission
        if (ActivityCompat.checkSelfPermission(ProducerRegistration.this
                , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(ProducerRegistration.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }


    }


}


