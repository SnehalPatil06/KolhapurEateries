package com.example.loginactivity.ConsumerActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.loginactivity.R;
import com.example.loginactivity.adapters.ViewAllAdapter;
import com.example.loginactivity.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ViewPopularProducts extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ViewAllModel> viewAllModelList;
    ViewAllAdapter viewAllAdapter;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_popular_products);

        recyclerView = findViewById(R.id.view_all_rec);
        fstore = FirebaseFirestore.getInstance();

        String type = getIntent().getStringExtra("type");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(this, viewAllModelList);
        recyclerView.setAdapter(viewAllAdapter);

        ////////////Getting fruits////////////////
        if(type!= null && type.equalsIgnoreCase("fruits")){
            fstore.collection("ViewAllPopularProducts").whereEqualTo("type","fruits").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }

                }
            });
        }

        ////////////Getting grocery////////////////
        if(type!= null && type.equalsIgnoreCase("grocery")){
            fstore.collection("ViewAllPopularProducts").whereEqualTo("type","grocery").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }

                }
            });
        }


        ////////////Getting vegetables////////////////
        if(type!= null && type.equalsIgnoreCase("vegetables")){
            fstore.collection("ViewAllPopularProducts").whereEqualTo("type","vegetables").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }

                }
            });
        }

        ////////////Getting drinks////////////////
        if(type!= null && type.equalsIgnoreCase("drinks")){
            fstore.collection("ViewAllPopularProducts").whereEqualTo("type","drinks").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }

                }
            });
        }


        ////////////Getting other////////////////
        if(type!= null && type.equalsIgnoreCase("other")){
            fstore.collection("ViewAllPopularProducts").whereEqualTo("type","other").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }

                }
            });
        }


        ////////////Getting dairy////////////////
        if(type!= null && type.equalsIgnoreCase("dairy")){
            fstore.collection("ViewAllPopularProducts").whereEqualTo("type","dairy").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }

                }
            });
        }

        ////////////Getting Bakery////////////////
        if(type!= null && type.equalsIgnoreCase("bakery")){
            fstore.collection("ViewAllPopularProducts").whereEqualTo("type","bakery").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }

                }
            });
        }

        ////////////Getting Spices////////////////
        if(type!= null && type.equalsIgnoreCase("spices")){
            fstore.collection("ViewAllPopularProducts").whereEqualTo("type","spices").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }

                }
            });
        }


    }
}