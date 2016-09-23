package com.juliosueiras.tip_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The MainActivity Class , contain the all of the controller logic codes
 * @author Julio Tain Sueiras <juliosueiras@gmail.com>
 */
public class MainActivity extends AppCompatActivity
{
    /**
     * Helper class for indicating the tip amount for tip spinner component
     * @author Julio Tain Sueiras <juliosueiras@gmail.com>
     */
    private static class TipAmount {
        static final int TIP_10_PER = 0;
        static final int TIP_15_PER = 1;
        static final int TIP_20_PER = 2;
        static final int TIP_OTHER  = 3;
    }

    private Spinner spnTip;
    private Spinner spnPeople;

    private Button btnCalculate;
    private Button btnClear;

    private EditText edtAmount;
    private EditText edtTip;

    private CheckBox chkHST;

    private TextView txtResultTip;
    private TextView txtResultTotal;
    private TextView txtResultPerPerson;

    private double tipPercAmount = 10;
    private boolean applyTax = false;
    private final static double HST_TAX = 0.13;


    /**
     * overridden from Activity
     * contain initial setup process for the main activity
     * @see Activity#onCreate(Bundle savedInstanceState)
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        assignAllCurrentComponents();
        setupButtonListeners();
        setupHSTCheckboxListener();
        setupPeopleSpinner(this);
        setupTipSpinner(this);

        setupTipEditTextListener();
        setupAmountEditTextListener();
    }

    /**
     * Helper method for assign all component from layout view to their private fields
     */
    private void assignAllCurrentComponents() {
        spnTip = (Spinner) findViewById(R.id.spinner_tip);
        spnPeople = (Spinner) findViewById(R.id.spinner_people);

        btnCalculate = (Button) findViewById(R.id.button_calculate);
        btnClear = (Button) findViewById(R.id.button_clear);

        edtAmount = (EditText) findViewById(R.id.edit_text_cash_amount);
        edtTip = (EditText) findViewById(R.id.edit_text_tip_percentage);

        chkHST = (CheckBox) findViewById(R.id.check_box_hst);

        txtResultTip = (TextView) findViewById(R.id.text_result_tip);
        txtResultTotal = (TextView) findViewById(R.id.text_result_total);

        txtResultPerPerson = (TextView) findViewById(R.id.text_result_per_person);
    }

    /**
     * Helper method for creating spinner adapter with the given entries
     * @param ma Main activity of the app
     * @param entries The spinner list
     * @return The array adapter object that contain the entries
     */
    private ArrayAdapter<String> createSpinnerAdapter(MainActivity ma, String[] entries) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                ma,
                android.R.layout.simple_spinner_item,
                entries
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        return adapter;
    }

    /**
     * Helper method for assigning tip field
     *
     * @param tipPerc The tip percentage to be assign
     */
    private void assignTipPerAmount(double tipPerc) {
        edtTip.setVisibility(-1);
        edtTip.setText("");
        tipPercAmount = tipPerc;
    }

    /**
     * Helper method for setting up the amount component listener for text changed event
     */
    private void setupAmountEditTextListener() {
        edtAmount.addTextChangedListener(createTextChangeListener());
    }

    /**
     * Helper method for setting up tip text component listener for text changed event
     */
    private void setupTipEditTextListener() {
        edtTip.addTextChangedListener(createTextChangeListener());
    }

    /**
     * Helper method for creating a TextWatcher class with cleanAllResult implemented
     * @return an instance of TextWatcher that has the cleanAllResult implemented
     * @see #cleanAllResult()
     */
    private TextWatcher createTextChangeListener() {
        return new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cleanAllResult();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s) { }

        };
    }


    /**
     * Helper method for setting up number of people spinner component
     *
     * @param ma Main activity of the app
     */
    private void setupPeopleSpinner(MainActivity ma) {
        spnPeople.setAdapter(createSpinnerAdapter(ma, getResources().getStringArray(R.array.people)));

        spnPeople.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                cleanAllResult();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                cleanAllResult();
            }
        });
    }

    /**
     * Helper method for setting up number of tip spinner component
     *
     * @param ma Main activity of the app
     */
    private void setupTipSpinner(MainActivity ma) {
        spnTip.setAdapter(createSpinnerAdapter(ma, getResources().getStringArray(R.array.tip_amount)));

        spnTip.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                cleanAllResult();
                switch (position) {
                    case TipAmount.TIP_10_PER:
                        assignTipPerAmount(10);
                        break;

                    case TipAmount.TIP_15_PER:
                        assignTipPerAmount(15);
                        break;

                    case TipAmount.TIP_20_PER:
                        assignTipPerAmount(20);
                        break;

                    case TipAmount.TIP_OTHER:
                        edtTip.setVisibility(1);
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                cleanAllResult();
            }
        });
    }

    /**
     * Helper method for setting up all the button listeners
     */
    private void setupButtonListeners() {
        btnCalculate.setOnClickListener(makeCalculateButtonListener());

        btnClear.setOnClickListener(makeClearButtonListener());
    }

    /**
     * Helper method for HST check box listener
     */
    private void setupHSTCheckboxListener() {
        chkHST.setOnCheckedChangeListener((buttonView, isChecked) -> {
            cleanAllResult();
            applyTax = isChecked;
        });
    }

    /**
     * Helper method for creating a on click listener for calculate button
     *
     * IMPORTANT: This method uses lambda, which is only support in JDK8, to add support for JDK6,7 and android
     * please look at https://github.com/evant/gradle-retrolambda
     *
     * @return the OnClickListener that has all the logic implemented
     */
    private View.OnClickListener makeCalculateButtonListener() {
        return (View v) -> {
            double tip = 0, amount = 0;
            if(!(edtTip.isShown())) {
                tip = tipPercAmount/100;
            } else {
                try {
                    tip = Double.parseDouble(edtTip.getText().toString())/100;
                } catch(NumberFormatException e){
                    Toast.makeText(this,"Tip cannot be blank", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            try {
                amount = Double.parseDouble(edtAmount.getText().toString());
            } catch(NumberFormatException e){
                Toast.makeText(this, "Amount cannot be blank", Toast.LENGTH_SHORT).show();
                return;
            }

            double result = 1, tip_amount = 0;
            String resultMsg = "";

            if(applyTax) {
                tip_amount = amount * (1 + HST_TAX) * tip;
                result = amount * (1 + HST_TAX) * (1 + tip);
                resultMsg = String.format("Total is: $%.2f (%.2f hst)",result, amount*HST_TAX);
            } else {
                result = amount * (1 + tip);
                tip_amount = amount * tip;
                resultMsg = String.format("Total is: $%.2f",result);
            }

            int numOfPeople = Integer.parseInt(spnPeople.getSelectedItem().toString());
            setResultToView(String.format("Tip is: $%.2f", tip_amount), resultMsg, String.format("Per person: $%.2f", result/numOfPeople));
        };
    }

    /**
     * Helper method for creating a on click listener for clear button
     *
     * IMPORTANT: This method uses lambda, which is only support in JDK8, to add support for JDK6,7 and android
     * please look at https://github.com/evant/gradle-retrolambda
     *
     * @return the OnClickListener that has all the logic implemented
     */
    private View.OnClickListener makeClearButtonListener() {
        return (View v) -> {
            edtTip.setText("");

            edtAmount.setText("");

            cleanAllResult();
            spnTip.setSelection(TipAmount.TIP_10_PER);
            spnPeople.setSelection(0);

            chkHST.setChecked(false);
        };
    }

    /**
     * Helper method to link all the result message to their components
     *
     * @param resultTipMsg the tip result message
     * @param resultTotalMsg the total result message
     * @param resultPersonMsg the per person result message
     */
    private void setResultToView(String resultTipMsg, String resultTotalMsg, String resultPersonMsg) {
        txtResultTip.setText(resultTipMsg);
        txtResultTip.setVisibility(1);

        txtResultTotal.setText(resultTotalMsg);
        txtResultTotal.setVisibility(1);


        int numOfPeople = Integer.parseInt(spnPeople.getSelectedItem().toString());

        if(!( numOfPeople == 1)) {
            txtResultPerPerson.setText(resultPersonMsg);
            txtResultPerPerson.setVisibility(1);
        }
    }

    /**
     * Helper method to clean the output(The text in Tip, Total, and Per Person)
     */
    private void cleanAllResult() {
        txtResultTip.setText("");
        txtResultTip.setVisibility(-1);

        txtResultTotal.setText("");
        txtResultTotal.setVisibility(-1);

        txtResultPerPerson.setText("");
        txtResultPerPerson.setVisibility(-1);
    }
}

