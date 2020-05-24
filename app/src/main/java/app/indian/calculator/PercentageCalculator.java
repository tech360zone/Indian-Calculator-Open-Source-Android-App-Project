package app.indian.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class PercentageCalculator extends AppCompatActivity {

    Toolbar pergentage_toolbar;
    EditText firstValue, secondValue;
    TextView textView, clrTv;
    Button cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percentage_calculator);

        pergentage_toolbar = findViewById(R.id.toolbar2);
        pergentage_toolbar.setTitle("Percentage Calculator");
        setSupportActionBar(pergentage_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firstValue = findViewById(R.id.etOne);
        secondValue = findViewById(R.id.etTwo);
        textView = findViewById(R.id.percentage_TextView);
        cal = findViewById(R.id.btn_percentage_cal);
        clrTv = findViewById(R.id.clear_TextView);

        clrTv.setVisibility(View.GONE);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float firstNo, secondNo, ans, guessInt;

                try{
                    guessInt = new Float(firstValue.getText().toString());
                } catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Values!", Toast.LENGTH_SHORT).show();
                    return;
                }

                firstNo = Float.parseFloat(firstValue.getText().toString());
                secondNo = Float.parseFloat(secondValue.getText().toString());

                ans = (firstNo / secondNo) * 100;

                textView.setText(new DecimalFormat("##.##").format(ans) + "%");

                clrTv.setVisibility(View.VISIBLE);
            }
        });

        clrTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstValue.setText("");
                secondValue.setText("");

                clrTv.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
