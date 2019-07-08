package com.gedehari.orderup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    /* Final variables for savedInstanceState */
    final String KEY_COUNT = "quantity";

    /* Declaring activity objects */
    Button order, plus, minus;
    TextView display, debug, price;
    Spinner spinnerCoffee;
    ImageView displayCoffee;
    CheckBox bubble, jelly;

    List<CoffeeData> coffeeList = new ArrayList<>();

    RelativeLayout layout;

    String selectedCoffee = "";

    /* Quantity counter */
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Locale localeID = new Locale("in", "ID");
        final NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        /* Add all coffee to List. Unreliable, but who gives a s#%t. */
        coffeeList.add(new CoffeeData("Latte", R.drawable.latte, "a", 10000));
        coffeeList.add(new CoffeeData("Cappuccino", R.drawable.cappuccino, "b", 20000));
        coffeeList.add(new CoffeeData("Espresso", R.drawable.espresso, "c", 30000));
        coffeeList.add(new CoffeeData("Flat White", R.drawable.flat_white, "d", 40000));
        coffeeList.add(new CoffeeData("Matcha", R.drawable.matcha, "e", 50000));

        if (savedInstanceState != null) quantity = savedInstanceState.getInt(KEY_COUNT, 0);

        layout = findViewById(R.id.layoutParent);
        order = findViewById(R.id.order);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        spinnerCoffee = findViewById(R.id.spinner_coffeetype);
        debug = findViewById(R.id.debug);
        displayCoffee = findViewById(R.id.coffeeDisplay);
        price = findViewById(R.id.priceDisplay);

        minus.setEnabled(false);
        price.setTypeface(null, Typeface.BOLD);
        display = findViewById(R.id.qtyDisplay);

        /* Test button */
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(layout, MainActivity.this.getString(R.string.button_working), Snackbar.LENGTH_SHORT).show();
            }
        });
        /* Add qty amount */
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                render(quantity);
            }
        });
        /* Subtract qty amount */
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity--;
                render(quantity);
            }
        });

        final ArrayAdapter<CoffeeData> adapter_coffee = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, coffeeList);
//        adapter_coffee.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCoffee.setAdapter(adapter_coffee);
        spinnerCoffee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CoffeeData selected = coffeeList.get(i);
                displayCoffee.setImageResource(selected.getCoffeeDrawables());
                selectedCoffee = selected.getCoffee();
                price.setText(formatRupiah.format(selected.getPrice()));
                render(quantity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        /* Update the screen at the end */
        render(quantity);
    }

    /* Update and render screen */
    void render(int quantity) {
        display.setText(String.valueOf(quantity));
        minus.setEnabled(!isQtyZero(quantity));
        debug.setText("You selected " + selectedCoffee);
    }

    boolean isQtyZero(int q) {
        return (q <= 0);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(KEY_COUNT, quantity);
        super.onSaveInstanceState(outState);
    }
}
