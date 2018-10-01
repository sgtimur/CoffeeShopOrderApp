package com.sgtimur.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent intent = getIntent();
        order = (Order) intent.getSerializableExtra(MainActivity.MESSAGE);

        TextView textView = findViewById(R.id.textView);
        textView.setText(getOrderDetails(order));
    }

    /**
     * Displays the details of the order
     *
     * @param order
     */
    private String getOrderDetails(Order order) {
        String message = getString(R.string.total) + "\n" + getString(R.string.name) + ": " + order.getCustomerName() +
                "\n" + getString(R.string.add_cream) + "? " + String.valueOf(order.isHasWhippedCream()) +
                "\n" + getString(R.string.add_chocolate) + "? " + String.valueOf(order.isHasChocolate()) +
                "\n" + getString(R.string.quantity) + ": " + order.getCupsNumber() + "\n" + getString(R.string.resut) +
                ": $" + MainActivity.calculatePrice(order) + "\n" + getString(R.string.thanks) + "!";
        return message;
    }

    /**
     * Opens a post app with the filled fields
     *
     * @param view
     */
    public void sendOrder(View view) {
        Intent gIntent = new Intent();
        gIntent.setAction(Intent.ACTION_SENDTO);
        gIntent.setData(Uri.parse("mailto:"));
        gIntent.putExtra(Intent.EXTRA_SUBJECT, "StarBox order for " + order.getCustomerName());
        gIntent.putExtra(Intent.EXTRA_TEXT, getOrderDetails(order));
        gIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(gIntent);
    }
}
