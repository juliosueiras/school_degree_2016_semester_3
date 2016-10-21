package com.juliosueiras.midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.CheckBox;
import android.content.Context;
import android.content.Intent;

/**
 * @author Julio Tain Sueiras <juliosueiras@gmail.com>
 */
public class ToppingsActivity extends AppCompatActivity
{
    private TextView txtToppingsTitle;
    private CheckBox chkCheese;
    private CheckBox chkPepperoni;
    private CheckBox chkGreenPepper;
    private Pizza userPizza = new Pizza("");

    private final static String DATA = "pizza";

    public static final int MAIN_ACTIVITY = 1;

    public static Intent newIntent(Context context, Pizza data) {

        Intent intent = new Intent(context, ToppingsActivity.class);

        intent.putExtra(DATA, data);

        return intent;

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toppings);

        Intent intent = getIntent();


        if (intent != null) {
             userPizza = (Pizza) intent.getExtras().getParcelable(DATA);
        }

        txtToppingsTitle = (TextView) findViewById(R.id.text_toppings_title);
        txtToppingsTitle.setText(userPizza.getSize() + " pizza");

        chkCheese = (CheckBox) findViewById(R.id.check_box_cheese);
        chkPepperoni = (CheckBox) findViewById(R.id.check_box_pepperoni);
        chkGreenPepper = (CheckBox) findViewById(R.id.check_box_green_pepper);
    }

    public void completeOrder(View v) {

        userPizza.setHasCheese(chkCheese.isChecked());
        userPizza.setHasPepperoni(chkPepperoni.isChecked());
        userPizza.setHasGreenPepper(chkGreenPepper.isChecked());

        if(userPizza.hasCheese() || userPizza.hasPepperoni() || userPizza.hasGreenPepper()) {
            String result = userPizza.getSize() + " pizza with";
            if(userPizza.hasCheese()) {
                result += " cheese";
            }
            if(userPizza.hasPepperoni()) {
                result += " pepperoni";
            }
            if(userPizza.hasGreenPepper()) {
                result += " green pepper";
            }

            Intent intent = MainActivity.newIntent(
                    getApplicationContext(),
                    result
                    );

            startActivityForResult(intent, MAIN_ACTIVITY);
        }

    }



}

