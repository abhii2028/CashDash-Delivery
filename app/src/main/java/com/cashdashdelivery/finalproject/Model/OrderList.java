package com.cashdashdelivery.finalproject.Model;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.cashdashdelivery.finalproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;

public class OrderList extends ArrayAdapter<Order> {

    private Activity context;
    private List<Order> OrderList;
    DatabaseReference mdatabaseassign,mdatabaseorder;

    public OrderList (Activity context, List<Order> OrderList)
    {
        super(context, R.layout.order_card,OrderList);
        this.context=context;
        this.OrderList=OrderList;

    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.order_card,null,true);


        mdatabaseassign = FirebaseDatabase.getInstance().getReference("AssignList");
        mdatabaseorder = FirebaseDatabase.getInstance().getReference("OrderList");

        TextView txtid = listViewItem.findViewById(R.id.orderID);
        TextView txtname = listViewItem.findViewById(R.id.name);
        TextView txtaddress = listViewItem.findViewById(R.id.address);
        TextView txtnumber = listViewItem.findViewById(R.id.number);
        TextView txtamount = listViewItem.findViewById(R.id.amount);

        final Order request = OrderList.get(position);
        txtid.setText("Order ID : (" + request.getId() + ")");
        txtname.setText(request.getName());
        txtaddress.setText(request.getAddress());
        txtnumber.setText("+91-" + request.getNumber());
        txtamount.setText("Amount : ₹" + request.getAmount());

        listViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabaseassign.child(request.getId()).setValue(OrderList.get(position));
                mdatabaseorder.child(request.getId()).removeValue();
            }
        });

        return listViewItem;


    }

}
