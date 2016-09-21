package com.juliosueiras.tip_calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.EditText;

public class MainActivity extends Activity
{
    private Spinner spnTip;
    private Spinner spnPeople;
    private Button btnCalculate;
    private EditText edtAmount;
    private Button btnClear;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        assignAllCurrentComponents();
        setupButtonListeners();
        setupPeopleSpinner(this);
        setupTipSpinner(this);
    }

    private void assignAllCurrentComponents() {
        spnTip = (Spinner) findViewById(R.id.spinner_tip);
        spnPeople = (Spinner) findViewById(R.id.spinner_people);
        btnCalculate = (Button) findViewById(R.id.button_calculate);
        btnClear = (Button) findViewById(R.id.button_clear);
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

    private void setupPeopleSpinner(MainActivity ma) {
        spnPeople.setAdapter(_createSpinnerAdapter(ma, "1,2,3,4,5,6,7,8,9,10".split(",")));
    }

    private void setupTipSpinner(MainActivity ma) {
        spnTip.setAdapter(_createSpinnerAdapter(ma, "10%,15%,20%,Other".split(",")));
    }

    public void onClickHST(View view) {

    }

    private void setupButtonListeners() {
        btnCalculate.setOnClickListener(makeCalculateButtonListener());
        btnClear.setOnClickListener(makeClearButtonListener());
    }

    private View.OnClickListener makeCalculateButtonListener() {
        return (View v) -> {
            btnCalculate.setText("Hello");
        };
    }

    private View.OnClickListener makeClearButtonListener() {
        return (View v) -> {
            // Button button_calculate = (Button) findViewById(R.id.button_calculate);
            // button_calculate.setText("Hello");
        };
    }
}
