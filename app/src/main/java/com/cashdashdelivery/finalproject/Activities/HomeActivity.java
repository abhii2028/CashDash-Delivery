package com.cashdashdelivery.finalproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cashdashdelivery.finalproject.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void pendingDelivery(View view)
    {
        startActivity(new Intent(HomeActivity.this,PendingDeliveryActivity.class));
    }

    public void allDelivery(View view)
    {
        startActivity(new Intent(HomeActivity.this,AllDeliveryActivity.class));
    }
}
