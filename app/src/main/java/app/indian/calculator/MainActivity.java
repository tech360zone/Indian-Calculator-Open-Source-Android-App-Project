package app.indian.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText firstValue, secondValue;
    TextView resultValue;
    ImageView closebtn, btnInsta, btnYoutube;
    LinearLayout btnPlus, btnSubtract, btnMultiply, btnDivide, linkClick;
    Button clearBtn, percentageBtn, ageBtn;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Indian Calculator");

        firstValue = findViewById(R.id.firstVl);
        secondValue = findViewById(R.id.secondVl);

        resultValue = findViewById(R.id.resultVl);

        btnPlus = findViewById(R.id.buttonPlus);
        btnSubtract = findViewById(R.id.buttonSubtract);
        btnMultiply = findViewById(R.id.buttonMultiply);
        btnDivide = findViewById(R.id.buttonDivide);
        clearBtn = findViewById(R.id.clrButton);
        percentageBtn = findViewById(R.id.per_cal);
        ageBtn = findViewById(R.id.age_cal);

        clearBtn.setVisibility(View.GONE);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float firstVal, secondVal, ans, guessInt;

                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(70);

                try{
                    guessInt = new Float(firstValue.getText().toString());
                } catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Values!", Toast.LENGTH_SHORT).show();
                    return;
                }

                firstVal = Float.parseFloat(firstValue.getText() + "");
                secondVal = Float.parseFloat(secondValue.getText() + "");

                ans = firstVal + secondVal;

                resultValue.setText(new DecimalFormat("##.##").format(ans));

                clearBtn.setVisibility(View.VISIBLE);
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float firstVal, secondVal, ans, guessInt;

                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(70);

                try{
                    guessInt = new Float(firstValue.getText().toString());
                } catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Values!", Toast.LENGTH_SHORT).show();
                    return;
                }

                firstVal = Float.parseFloat(firstValue.getText().toString());
                secondVal = Float.parseFloat(secondValue.getText().toString());

                ans = firstVal - secondVal;

                resultValue.setText(new DecimalFormat("##.##").format(ans));

                clearBtn.setVisibility(View.VISIBLE);

            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float firstVal, secondVal, ans, guessInt;

                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(70);

                try{
                    guessInt = new Float(firstValue.getText().toString());
                } catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Values!", Toast.LENGTH_SHORT).show();
                    return;
                }

                firstVal = Float.parseFloat(firstValue.getText().toString());
                secondVal = Float.parseFloat(secondValue.getText().toString());

                ans = firstVal * secondVal;

                resultValue.setText(new DecimalFormat("##.##").format(ans));

                clearBtn.setVisibility(View.VISIBLE);
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float firstVal, secondVal, ans, guessInt;

                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(70);

                try{
                    guessInt = new Float(firstValue.getText().toString());
                } catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Values!", Toast.LENGTH_SHORT).show();
                    return;
                }

                firstVal = Float.parseFloat(firstValue.getText().toString());
                secondVal = Float.parseFloat(secondValue.getText().toString());

                ans = firstVal / secondVal;

                resultValue.setText(new DecimalFormat("##.##").format(ans));

                clearBtn.setVisibility(View.VISIBLE);
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstValue.setText("");
                secondValue.setText("");

                clearBtn.setVisibility(View.GONE);
            }
        });

        percentageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PercentageCalculator.class));
            }
        });

        ageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AgeCalculator.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, "Indian Calculator");
                share.putExtra(Intent.EXTRA_TEXT, "Share this Indian Calculator to other Indian people: " + "http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());
                startActivity(Intent.createChooser(share, "Share app!"));
                return true;
            case R.id.rate:
                Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
                }
                return true;
            case R.id.more:
                Intent more = new Intent(Intent.ACTION_VIEW);
                more.setData(Uri.parse("https://play.google.com/store/apps/dev?id=6586212709102045308&hl=en"));
                startActivity(more);
                return true;
            case R.id.about:
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_dialog_about);
                dialog.show();

                linkClick = dialog.findViewById(R.id.sourceCodeLink);
                linkClick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://github.com/tech360zone/Indian-Calculator-Open-Source-Android-App-Project");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });

                closebtn = dialog.findViewById(R.id.btnClose);
                closebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                Context context = getApplicationContext(); // or activity.getApplicationContext()
                PackageManager packageManager = context.getPackageManager();
                String packageName = context.getPackageName();

                String myVersionName = "not available"; // initialize String
                String first = "Current Version: ";

                try {
                    myVersionName = packageManager.getPackageInfo(packageName, 0).versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }

                TextView textView = (TextView) dialog.findViewById(R.id.myVersion);
                textView.setText(first + myVersionName);

                btnInsta = dialog.findViewById(R.id.insta);
                btnInsta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://instagram.com/tech360zone");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });

                btnYoutube = dialog.findViewById(R.id.youtube);
                btnYoutube.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://youtube.com/tech360zone?sub_confirmation=1");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
