package com.example.itc406_lab_assesment21;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderSummaryActivity extends AppCompatActivity {

    private TextView txtName, txtPhone, txtEmail, txtFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        txtName = findViewById(R.id.txtSummaryName);
        txtPhone = findViewById(R.id.txtSummaryPhone);
        txtEmail = findViewById(R.id.txtSummaryEmail);
        txtFood = findViewById(R.id.txtSummaryFood);
        Button btnCancelOrder = findViewById(R.id.btnCancelOrder);

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("CUSTOMER_NAME");
            String phone = intent.getStringExtra("CUSTOMER_PHONE");
            String email = intent.getStringExtra("CUSTOMER_EMAIL");
            String food = intent.getStringExtra("FOOD_ORDERED");

            txtName.setText("Name: " + name);
            txtPhone.setText("Phone: " + phone);
            txtEmail.setText("Email: " + email);
            txtFood.setText("Food Ordered: " + food);
        }

        btnCancelOrder.setOnClickListener(v -> finish());
    }
}