package com.juliosueiras.tip_calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CheckBox;


public class MainActivity extends Activity
{
    private class TipAmount {
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

    private TextView txtResult;

    private double tipPercAmount = 0;
    private boolean applyTax = false;
    private final double HST_TAX = 1.13;


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
    }

    private void assignAllCurrentComponents() {
        spnTip = (Spinner) findViewById(R.id.spinner_tip);
        spnPeople = (Spinner) findViewById(R.id.spinner_people);

        btnCalculate = (Button) findViewById(R.id.button_calculate);
        btnClear = (Button) findViewById(R.id.button_clear);

        edtAmount = (EditText) findViewById(R.id.edit_text_cash_amount);
        edtTip = (EditText) findViewById(R.id.edit_text_tip_percentage);

        chkHST = (CheckBox) findViewById(R.id.check_box_hst);
        txtResult = (TextView) findViewById(R.id.text_result);
    }

    private ArrayAdapter<String> _createSpinnerAdapter(MainActivity ma, String[] entries) {

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

    private void assignTipPerAmount(int tipPerc) {
        edtTip.setVisibility(-1);
        edtTip.setText("");
        tipPercAmount = tipPerc;
    }


    private void setupPeopleSpinner(MainActivity ma) {
        spnPeople.setAdapter(_createSpinnerAdapter(ma, getResources().getStringArray(R.array.people)));
    }

    private void setupTipSpinner(MainActivity ma) {
        spnTip.setAdapter(_createSpinnerAdapter(ma, getResources().getStringArray(R.array.tip_amount)));

        spnTip.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
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
            }
        });
    }

    private void setupButtonListeners() {
        btnCalculate.setOnClickListener(makeCalculateButtonListener());
        btnClear.setOnClickListener(makeClearButtonListener());
    }

    private View.OnClickListener makeCalculateButtonListener() {
        return (View v) -> {
            double tip = Double.parseDouble(edtTip.getText().toString());
            double amount = Double.parseDouble(edtAmount.getText().toString());
            double tip_amount = (tip/100) * amount;
            double result = 1;
            
            if(applyTax) {
                result = amount * HST_TAX * (1 + (tip/100));
            } else {
                result = amount * (1 + (tip/100));
            }

            txtResult.setText("Total is: " + result);
            txtResult.setVisibility(1);

        };
    }

    private View.OnClickListener makeClearButtonListener() {
        return (View v) -> {
        };
    }

    private void setupHSTCheckboxListener() {
        chkHST.setOnCheckedChangeListener((buttonView, isChecked) -> {
            applyTax = isChecked;
        });
    }
}

