package com.sgtimur.android.justjava;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private Order order;

    /**
     * Price of the one coffee cup without any additives
     */
    private static final int coffeePrice = 5;

    /**
     * Additional price of the whipping cream topping
     */
    private static final int creamPrice = 1;

    /**
     * Additional price of the chocolate topping
     */
    private static final int chocolatePrice = 2;

    /**
     * The quantity of coffee cups that app initially prompts
     */
    private static final int initialAmountOfCoffee = 1;

    public static final String MESSAGE = "com.sgtimur.android.justjava.MainActivity.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayQuantity(initialAmountOfCoffee);

        order = new Order();
        order.setCupsNumber(initialAmountOfCoffee);

        displayPrice();

        EditText editText = findViewById(R.id.nameTextField);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    hideKeyboard(v);
            }
        });
    }

    /**
     * Called, when button is clicked
     */
    public void submitOrder(View view) {

        boolean hasWhippedCream = ((CheckBox) findViewById(R.id.whippedCreamBox)).isChecked();
        boolean hasChocolate = ((CheckBox) findViewById(R.id.chocolateBox)).isChecked();
        String name = ((EditText) findViewById(R.id.nameTextField)).getText().toString();

        order.setHasWhippedCream(hasWhippedCream);
        order.setHasChocolate(hasChocolate);
        order.setCustomerName(name);

        ((EditText) findViewById(R.id.nameTextField)).clearFocus();

        Intent intent = new Intent(this, TestActivity.class);
        intent.putExtra(MESSAGE, order);
        startActivity(intent);
    }

    /**
     * Increases the number of cups
     */
    public void increaseNumber(View view) {
        if (order.getCupsNumber() < 100) {
            order.setCupsNumber(order.getCupsNumber() + 1);
            displayQuantity(order.getCupsNumber());
        } else {
            Toast.makeText(this, "You cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
        }

        displayPrice();
    }

    /**
     * Decreases the number of cups
     */
    public void decreaseNumber(View view) {
        if (order.getCupsNumber() > 1) {
            order.setCupsNumber(order.getCupsNumber() - 1);
            displayQuantity(order.getCupsNumber());
        } else {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
        }

        displayPrice();
    }

    /**
     * Displays the ordered number on View
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * Calculates the price of the order
     */
    public static int calculatePrice(Order order) {
        int resultPrice = coffeePrice;
        if (order.isHasWhippedCream()) resultPrice += creamPrice;
        if (order.isHasChocolate()) resultPrice += chocolatePrice;

        return resultPrice * order.getCupsNumber();
    }

    /**
     * Displays price on the screen
     */
    private void displayPrice() {
        TextView textView = findViewById(R.id.order_summary_text_view);
        textView.setText("$" + calculatePrice(order));
    }

    /**
     * Hides the keyboard
     */
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Shows the price when toppings are changed
     */
    public void checkBoxClicked(View view) {
        CheckBox whippedCreamBox = findViewById(R.id.whippedCreamBox);
        CheckBox chocolateCreamBox = findViewById(R.id.chocolateBox);
        order.setHasWhippedCream(whippedCreamBox.isChecked());
        order.setHasChocolate(chocolateCreamBox.isChecked());
        displayPrice();
    }
}