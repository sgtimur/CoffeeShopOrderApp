package com.sgtimur.android.justjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent intent = getIntent();
        Order order = (Order) intent.getSerializableExtra(MainActivity.MESSAGE);

        TextView textView = findViewById(R.id.textView);
        String message = "Total\nName: " + order.getCustomerName() + "\nAdd whipped cream? " + String.valueOf(order.isHasWhippedCream()) +
                "\nAdd chocolate? " + String.valueOf(order.isHasChocolate()) +
                "\nQuantity: " + order.getCupsNumber() +
                "\nTotal: $" + MainActivity.calculatePrice(order) + "\nThank you!";
        textView.setText(message);
    }

    /**
     * Displays the details of the order
     * @param order
     */
    private void displayOrder(Order order) {
        TextView textView = findViewById(R.id.textView);
        String message = "Total\nName: " + order.getCustomerName() + "\nAdd whipped cream? " +
                String.valueOf(order.isHasWhippedCream()) + "\nAdd chocolate? " + String.valueOf(order.isHasChocolate()) +
                "\nQuantity: " + order.getCupsNumber() + "\nTotal: $" + MainActivity.calculatePrice(order) + "\nThank you!";
        textView.setText(message);
    }
}
