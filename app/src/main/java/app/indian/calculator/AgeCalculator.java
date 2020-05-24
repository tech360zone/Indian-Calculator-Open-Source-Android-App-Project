package app.indian.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AgeCalculator extends AppCompatActivity {

    TextView clearDOB;
    Button button2;
    Toolbar toolbarAge;

    TextView textViewFinalYears, textViewFinalMonths, textViewFinalDays, textViewNextBirthdayDays, textViewNextBirthdayMonths;
    EditText editTextCurrentDay, editTextCurrentMonth, editTextCurrentYear;
    EditText editTextBirthDay, editTextBirthMonth, editTextBirthYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculator);

        toolbarAge = findViewById(R.id.toolbarages);
        toolbarAge.setTitle("Age Calculator");
        setSupportActionBar(toolbarAge);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button2 = findViewById(R.id.myAgeBtnTwo);
        clearDOB = findViewById(R.id.clr_dob);

        editTextCurrentDay = findViewById(R.id.editTextCurrentDay);
        editTextCurrentMonth = findViewById(R.id.editTextCurrentMonth);
        editTextCurrentYear = findViewById(R.id.editTextCurrentYear);

        editTextBirthDay = findViewById(R.id.editTextBirthDay);
        editTextBirthMonth = findViewById(R.id.editTextBirthMonth);
        editTextBirthYear = findViewById(R.id.editTextBirthYear);

        textViewFinalYears = findViewById(R.id.textViewFinalYears);
        textViewFinalMonths = findViewById(R.id.textViewFinalMonths);
        textViewFinalDays = findViewById(R.id.textViewFinalDays);

        textViewNextBirthdayDays = findViewById(R.id.textViewNextBirthdayDays);
        textViewNextBirthdayMonths = findViewById(R.id.textViewNextBirthdayMonths);

        clearDOB.setVisibility(View.GONE);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view == button2) {
                    if (!TextUtils.isEmpty(editTextBirthDay.getText()) && !TextUtils.isEmpty(editTextBirthMonth.getText()) && !TextUtils.isEmpty(editTextBirthYear.getText()) && !TextUtils.isEmpty(editTextCurrentDay.getText()) && !TextUtils.isEmpty(editTextCurrentMonth.getText()) && !TextUtils.isEmpty(editTextCurrentYear.getText())) {

                        Calendar cal = Calendar.getInstance();

                        int currentDay = Integer.valueOf(editTextCurrentDay.getText().toString());
                        int currentMonth = Integer.valueOf(editTextCurrentMonth.getText().toString());
                        int currentYear = Integer.valueOf(editTextCurrentYear.getText().toString());

                        Date now = new Date(currentYear, currentMonth, currentDay);

                        int birthDay = Integer.valueOf(editTextBirthDay.getText().toString());
                        int birthMonth = Integer.valueOf(editTextBirthMonth.getText().toString());
                        int birthYear = Integer.valueOf(editTextBirthYear.getText().toString());

                        Date dob = new Date(birthYear, birthMonth, birthDay);

                        if (dob.after(now)) {
                            Toast.makeText(AgeCalculator.this, "Birthday can't in future", Toast.LENGTH_LONG).show();
                            return;
                        }
                        // days of every month
                        int month[] = {31, 28, 31, 30, 31, 30, 31,
                                31, 30, 31, 30, 31};

                        // if birth date is greater then current birth
                        // month then do not count this month and add 30
                        // to the date so as to subtract the date and
                        // get the remaining days
                        if (birthDay > currentDay) {
                            currentDay = currentDay + month[birthMonth - 1];
                            currentMonth = currentMonth - 1;
                        }

                        // if birth month exceeds current month, then do
                        // not count this year and add 12 to the month so
                        // that we can subtract and find out the difference
                        if (birthMonth > currentMonth) {
                            currentYear = currentYear - 1;
                            currentMonth = currentMonth + 12;
                        }

                        // calculate date, month, year
                        int calculated_date = currentDay - birthDay;
                        int calculated_month = currentMonth - birthMonth;
                        int calculated_year = currentYear - birthYear;

                        textViewFinalDays.setText(String.valueOf(calculated_date));
                        textViewFinalMonths.setText(String.valueOf(calculated_month));
                        textViewFinalYears.setText(String.valueOf(calculated_year));

                        clearDOB.setVisibility(View.VISIBLE);

                    } else {
                        Toast.makeText(AgeCalculator.this, "All fields are required", Toast.LENGTH_LONG).show();
                    }
                }

                nextBirthday();
            }
        });

        clearDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();

                editTextBirthDay.setText("");
                editTextBirthMonth.setText("");
                editTextBirthYear.setText("");

                textViewFinalDays.setText("00");
                textViewFinalMonths.setText("00");
                textViewFinalYears.setText("00");

                textViewNextBirthdayDays.setText("00");
                textViewNextBirthdayMonths.setText("00");

                setupCurrentDate();

                clearDOB.setVisibility(View.GONE);
            }
        });

        setupCurrentDate();
    }

    private void nextBirthday() {

        if (!TextUtils.isEmpty(editTextBirthDay.getText()) && !TextUtils.isEmpty(editTextBirthMonth.getText()) && !TextUtils.isEmpty(editTextBirthYear.getText()) && !TextUtils.isEmpty(editTextCurrentDay.getText()) && !TextUtils.isEmpty(editTextCurrentMonth.getText()) && !TextUtils.isEmpty(editTextCurrentYear.getText())) {

            int currentDay = Integer.valueOf(editTextCurrentDay.getText().toString());
            int currentMonth = Integer.valueOf(editTextCurrentMonth.getText().toString());
            int currentYear = Integer.valueOf(editTextCurrentYear.getText().toString());

            Calendar current = Calendar.getInstance();
            current.set(currentYear, currentMonth, currentDay);

            int birthDay = Integer.valueOf(editTextBirthDay.getText().toString());
            int birthMonth = Integer.valueOf(editTextBirthMonth.getText().toString());
            int birthYear = Integer.valueOf(editTextBirthYear.getText().toString());

            Calendar birthday = Calendar.getInstance();
            birthday.set(birthYear, birthMonth, birthDay);

            long difference = birthday.getTimeInMillis() - current.getTimeInMillis();

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(difference);

            textViewNextBirthdayMonths.setText(String.valueOf(cal.get(Calendar.MONTH)));
            textViewNextBirthdayDays.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
        } else {
            Toast.makeText(AgeCalculator.this, "All fields are required", Toast.LENGTH_LONG).show();
        }
    }

    private void setupCurrentDate() {
        final Calendar c = Calendar.getInstance();
        editTextCurrentYear.setText(String.valueOf(c.get(Calendar.YEAR)));
        editTextCurrentMonth.setText(addZero(c.get(Calendar.MONTH) + 1));
        editTextCurrentDay.setText(addZero(c.get(Calendar.DAY_OF_MONTH)));

        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
        Date date = new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH) - 1);
        String dayOfWeek = simpledateformat.format(date);
    }

    private String addZero(int number) {
        String n;
        if (number < 10) {
            n = "0" + number;
        } else {
            n = String.valueOf(number);
        }
        return n;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
