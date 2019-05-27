package com.cashdashdelivery.finalproject.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cashdashdelivery.finalproject.Model.AssignList;
import com.cashdashdelivery.finalproject.Model.Order;
import com.cashdashdelivery.finalproject.Model.OrderList;
import com.cashdashdelivery.finalproject.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PendingDeliveryActivity extends AppCompatActivity {

    ListView orderListView;
    ArrayList<Order> AssignList;
    DatabaseReference mdatabaseorder;
    com.cashdashdelivery.finalproject.Model.AssignList adapter;
    Order a;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_delivery);

        orderListView = findViewById(R.id.orderList);

        mdatabaseorder = FirebaseDatabase.getInstance().getReference("AssignList");

        AssignList = new ArrayList<>();

        mdatabaseorder.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void fetchData(DataSnapshot dataSnapshot)
    {
        AssignList.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            a = ds.getValue(Order.class);
            AssignList.add(0, a);
        }
        adapter = new AssignList(PendingDeliveryActivity.this, AssignList);
        orderListView.setAdapter(adapter);
    }



}
