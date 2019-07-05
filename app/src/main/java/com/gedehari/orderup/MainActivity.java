package com.gedehari.orderup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /* Final variables for savedInstanceState */
    final String KEY_COUNT = "quantity";

    /* Declaring activity objects */
    Button order, plus, minus;
    TextView display, debug, price;
    Spinner spinner_coffee;
    ImageView display_coffee;

    List<CoffeeData> listCoffeeData = new ArrayList<>();

    RelativeLayout layout;

    String selectedCoffee = "";

    /* Quantity counter */
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCoffeeData.add(new CoffeeData("Latte", R.drawable.latte));
        listCoffeeData.add(new CoffeeData("Cappuccino", R.drawable.cappuccino));
        listCoffeeData.add(new CoffeeData("Espresso", R.drawable.espresso));
        listCoffeeData.add(new CoffeeData("Flat White", R.drawable.flat_white));
        listCoffeeData.add(new CoffeeData("Matcha", R.drawable.matcha));

        if (savedInstanceState != null) quantity = savedInstanceState.getInt(KEY_COUNT, 0);

        layout = findViewById(R.id.layoutParent);
        order = findViewById(R.id.order);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        spinner_coffee = findViewById(R.id.spinner_coffeetype);
        debug = findViewById(R.id.debug);
        display_coffee = findViewById(R.id.coffeeDisplay);
        price = findViewById(R.id.priceDisplay);

        minus.setEnabled(false);

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

        final ArrayAdapter<CoffeeData> adapter_coffee = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listCoffeeData);
//        adapter_coffee.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_coffee.setAdapter(adapter_coffee);
        spinner_coffee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CoffeeData selCoffee = listCoffeeData.get(i);
                display_coffee.setImageResource(selCoffee.getCoffeeDrawables());
                selectedCoffee = selCoffee.getCoffeeData();
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
