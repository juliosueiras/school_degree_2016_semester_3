package week_01.hello.world;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity
{
    private EditText edtAmount;
    private TextView txtTotal;
    private RadioGroup grpHst;

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button btnCalculate = (Button) findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(createCalculateClickListener());

        edtAmount = (EditText) findViewById(R.id.edtAmount);
        txtTotal = (TextView) findViewById(R.id.txtTotal);
        grpHst = (RadioGroup) findViewById(R.id.grpHst);

    }

    private void calculate() {
        try {
            double amount = Double.parseDouble(edtAmount.getText().toString());
            int selected = grpHst.getCheckedRadioButtonId();
            switch (selected) {
                case R.id.rdHst:
                    amount *= 1.13;
                    break;

                case R.id.rdNoHst:
                    break;
            }

            String format = "Total is %.2f";
            txtTotal.setText(String.format(format, amount));
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
            edtAmount.setError("Please enter valid amount");

        }
    }

    private OnClickListener createCalculateClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        };
    }

}
