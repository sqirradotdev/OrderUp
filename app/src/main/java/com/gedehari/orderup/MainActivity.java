package com.gedehari.orderup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    /* Final variables for savedInstanceState */
    final String KEY_COUNT = "quantity";

    /* Declaring activity objects */
    Button order, plus, minus;
    TextView display, debug;
    Spinner spinner_coffee;

    LinearLayout layout;

    /* Quantity counter */
    int quantity = 0;

    String selectedCoffee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) quantity = savedInstanceState.getInt(KEY_COUNT, 0);

        layout = findViewById(R.id.layout);
        order = findViewById(R.id.order);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        spinner_coffee = findViewById(R.id.spinner_coffeetype);
        debug = findViewById(R.id.debug);

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
                render();
            }
        });
        /* Subtract qty amount */
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity--;
                render();
            }
        });

        final ArrayAdapter<CharSequence> adapter_coffee = ArrayAdapter.createFromResource(this, R.array.array_coffetype, android.R.layout.simple_spinner_item);
        adapter_coffee.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_coffee.setAdapter(adapter_coffee);
        spinner_coffee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCoffee = adapter_coffee.getItem(i).toString();
                debug.setText("You selected " + selectedCoffee);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        /* Update the screen at the end */
        render();
    }

    /* Update and render screen */
    void render() {
        display.setText(String.valueOf(quantity));
        minus.setEnabled(!isQtyZero(quantity));
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
