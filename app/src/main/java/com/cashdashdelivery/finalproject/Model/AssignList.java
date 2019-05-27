package com.cashdashdelivery.finalproject.Model;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cashdashdelivery.finalproject.Activities.TrackingActivity;
import com.cashdashdelivery.finalproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AssignList extends ArrayAdapter<Order> {

    private Activity context;
    private List<Order> AssignList;
    DatabaseReference mdatabaseassign,mdatabaseorder,mdatabaseaddress;

    public AssignList (Activity context, List<Order> AssignList)
    {
        super(context, R.layout.assign_card,AssignList);
        this.context=context;
        this.AssignList=AssignList;

    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.assign_card,null,true);

        mdatabaseassign = FirebaseDatabase.getInstance().getReference("AssignList");
        mdatabaseorder = FirebaseDatabase.getInstance().getReference("OrderList");
        mdatabaseaddress = FirebaseDatabase.getInstance().getReference("TrackingAddress");

        TextView txtid = listViewItem.findViewById(R.id.orderID);
        TextView txtname = listViewItem.findViewById(R.id.name);
        TextView txtaddress = listViewItem.findViewById(R.id.address);
        TextView txtnumber = listViewItem.findViewById(R.id.number);
        TextView txtamount = listViewItem.findViewById(R.id.amount);

        final Order request = AssignList.get(position);
        txtid.setText("Order ID : (" + request.getId() + ")");
        txtname.setText(request.getName());
        txtaddress.setText(request.getAddress());
        txtnumber.setText("+91-" + request.getNumber());
        txtamount.setText("Amount : ₹" + request.getAmount());

        listViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabaseaddress.setValue(request.getAddress());
                context.startActivity(new Intent(context,TrackingActivity.class));
            }
        });

        return listViewItem;


    }

}
