package com.juliosueiras.midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.content.Intent;
import android.content.Context;

/**
 * The MainActivity Class , contain the all of the controller logic codes
 * @author Julio Tain Sueiras <juliosueiras@gmail.com>
 */
public class MainActivity extends AppCompatActivity
{

    private TextView txtPizzaOrderResult;
    private Button btnAddToppings;
    private RadioGroup rdgSize;
    private String result = "";

    private final static String DATA = "result";
    public static final int TOPPINGS_ACTIVITY = 2;

    public static Intent newIntent(Context context, String data) {

        Intent intent = new Intent(context, MainActivity.class);

        intent.putExtra(DATA, data);

        return intent;

    }

    /**
     * overridden from Activity
     * contain initial setup process for the main activity
     * @see Activity#onCreate(Bundle savedInstanceState)
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignAllCurrentComponents();
        Intent intent = getIntent();

        if (intent != null) {
             result = (String) intent.getStringExtra(DATA);
             btnAddToppings.setText("NEW PIZZA");
             txtPizzaOrderResult.setText(result);
        }
    }

    public void addToppings(View v) {

        if (getIntent() != null) {
             result = (String) getIntent().getStringExtra(DATA);
             btnAddToppings.setText("ADD TOPPINGS");
             txtPizzaOrderResult.setText("");
        } else {
            String size = ((RadioButton) findViewById(rdgSize.getCheckedRadioButtonId())).getText().toString();
            Pizza userPizza = new Pizza(size);

            Intent intent = ToppingsActivity.newIntent(
                    getApplicationContext(),
                    userPizza
                    );

            startActivityForResult(intent, TOPPINGS_ACTIVITY);
        }
    }

    /**
     * Helper method for assign all component from layout view to their private fields
     */
    private void assignAllCurrentComponents() {
        txtPizzaOrderResult = (TextView) findViewById(R.id.text_pizza_order_result);
        rdgSize = (RadioGroup) findViewById(R.id.rdg_size);
        btnAddToppings = (Button) findViewById(R.id.button_add_toppings);
    }

}

