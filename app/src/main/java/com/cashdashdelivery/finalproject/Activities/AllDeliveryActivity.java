package com.cashdashdelivery.finalproject.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cashdashdelivery.finalproject.Model.Order;
import com.cashdashdelivery.finalproject.Model.OrderList;
import com.cashdashdelivery.finalproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllDeliveryActivity extends AppCompatActivity {

    ListView orderListView;
    ArrayList<Order> OrderList;
    DatabaseReference mdatabase;
    OrderList adapter;
    Order a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_delivery);

        orderListView = findViewById(R.id.orderList);

        mdatabase = FirebaseDatabase.getInstance().getReference("OrderList");
        OrderList = new ArrayList<>();

        mdatabase.addValueEventListener(new ValueEventListener() {
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
        OrderList.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            a = ds.getValue(Order.class);
            OrderList.add(0,a);
        }
        adapter = new OrderList(AllDeliveryActivity.this, OrderList);
        orderListView.setAdapter(adapter);
    }
}
