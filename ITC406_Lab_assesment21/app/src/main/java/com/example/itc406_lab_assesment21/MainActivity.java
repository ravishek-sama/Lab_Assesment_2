package com.example.itc406_lab_assesment21;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText txtName, txtPhone, txtEmail;
    private Spinner spinnerFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);
        spinnerFood = findViewById(R.id.spinnerFood);
        Button btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        Button btnClearOrder = findViewById(R.id.btnClearOrder);

        // Populate Spinner with food items, including a default "Select" option
        String[] foodItems = {"-- Select Food Item --", "Chicken Palau", "Lamb Palau", "Pizza", "Chow Mein"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, foodItems);
        spinnerFood.setAdapter(adapter);

        // Set click listeners using lambdas
        btnPlaceOrder.setOnClickListener(v -> validateAndPlaceOrder());
        btnClearOrder.setOnClickListener(v -> clearOrderFields());
    }

    private void clearOrderFields() {
        txtName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        spinnerFood.setSelection(0);
        
        // Clear errors
        txtName.setError(null);
        txtPhone.setError(null);
        txtEmail.setError(null);
        
        Toast.makeText(this, "Fields cleared", Toast.LENGTH_SHORT).show();
    }

    private void validateAndPlaceOrder() {
        String name = txtName.getText().toString().trim();
        String phone = txtPhone.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        
        // Get the selected food item
        int selectedPosition = spinnerFood.getSelectedItemPosition();
        String food = spinnerFood.getSelectedItem().toString();

        // Validation logic
        if (name.isEmpty()) {
            txtName.setError("Name cannot be empty");
            txtName.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            txtPhone.setError("Phone cannot be empty");
            txtPhone.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            txtEmail.setError("Email cannot be empty");
            txtEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmail.setError("Invalid email format");
            txtEmail.requestFocus();
            return;
        }

        // Validate Spinner selection (position 0 is the default "Select" option)
        if (selectedPosition == 0) {
            Toast.makeText(this, "Please make a selection", Toast.LENGTH_SHORT).show();
            return;
        }

        // If all valid, navigate to OrderSummaryActivity
        Intent intent = new Intent(MainActivity.this, OrderSummaryActivity.class);
        intent.putExtra("CUSTOMER_NAME", name);
        intent.putExtra("CUSTOMER_PHONE", phone);
        intent.putExtra("CUSTOMER_EMAIL", email);
        intent.putExtra("FOOD_ORDERED", food);
        startActivity(intent);

        Toast.makeText(this, "Processing Order...", Toast.LENGTH_SHORT).show();
    }
}