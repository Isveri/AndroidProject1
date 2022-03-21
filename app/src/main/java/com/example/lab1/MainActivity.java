package com.example.lab1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private String imie;
    private String nazwisko;
    private int liczbaOcen;
    private Double srednia;

    EditText wpisOcen;
    EditText wpisImie;
    EditText wpisNazwisko;
    TextView sredniaTxt;
    Button przycisk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wpisOcen = findViewById(R.id.ocenyWpis);
        wpisImie = findViewById(R.id.imieWpis);
        wpisNazwisko = findViewById(R.id.nazwiskoWpis);
        przycisk = findViewById(R.id.button);
        sredniaTxt = findViewById(R.id.sredniaTxt);



        imie = wpisImie.getText().toString();
        nazwisko = wpisNazwisko.getText().toString();
        przycisk.setVisibility(View.INVISIBLE);

        Toast blad = Toast.makeText(this, getString(R.string.bledne_dane), Toast.LENGTH_SHORT);


        ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        Bundle ext = result.getData().getExtras();
                        srednia = ext.getDouble("srednia");
                        sredniaTxt.setVisibility(View.VISIBLE);
                        sredniaTxt.setText(getString(R.string.twojaSr)+" "+ srednia);
                        if (result.getResultCode() == 3 && srednia>3.0) {
                            przycisk.setText(getString(R.string.zdane));
                            przycisk.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    new AlertDialog.Builder(MainActivity.this)
                                            .setTitle(getString(R.string.wynik))
                                            .setMessage(getString(R.string.gratulacje))
                                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    finish();
                                                    }})
                                            .setIcon(android.R.drawable.ic_dialog_info)
                                            .show();


                                }
                            });
                        } else if(result.getResultCode() == 2 && srednia<3.0) {
                            przycisk.setText(getString(R.string.fail));
                            przycisk.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    new AlertDialog.Builder(MainActivity.this)
                                            .setTitle(R.string.wynik)
                                            .setMessage(getString(R.string.warunek))
                                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    finish();
                                                }})
                                            .setIcon(android.R.drawable.ic_dialog_info)
                                            .show();

                                }
                            });
                        }
                    }
                });

        wpisImie.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                imie = wpisImie.getText().toString();
                checkRequiredFields();
                if (imie.equals("")) {
                    wpisImie.setError(getString(R.string.bledne_dane));
                    blad.show();
                }
            }
        });


//        wpisImie.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////                if(imie.equals("")){
////                    wpisImie.setError("Niepoprawne dane");
////                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                imie = wpisImie.getText().toString();
//                checkRequiredFields();
//                if (imie.equals("")) {
//                    wpisImie.setError("Niepoprawne dane");
//                    blad.show();
//                }
//            }
//        });
        wpisNazwisko.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                nazwisko = wpisNazwisko.getText().toString();
                checkRequiredFields();
                if (nazwisko.equals("")) {
                    wpisNazwisko.setError(getString(R.string.bledne_dane));
                    blad.show();
                }
            }
        });
//        wpisNazwisko.addTextChangedListener((new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////                if(nazwisko.equals("")){
////                    wpisNazwisko.setError("Niepoprawne dane");
////                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                nazwisko = wpisNazwisko.getText().toString();
//                checkRequiredFields();
//                if (nazwisko.equals("")) {
//                    wpisNazwisko.setError("Niepoprawne dane");
//                    blad.show();
//                }
//            }
//        }));


        wpisOcen.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (wpisOcen.getText().toString().equals("") || !StringUtils.isNumeric(wpisOcen.getText().toString())) {
                    wpisOcen.setError(getString(R.string.bledne_dane));
                    blad.show();
                    checkRequiredFields();
                } else {
                    liczbaOcen = Integer.parseInt(String.valueOf(wpisOcen.getText()));
                    checkRequiredFields();
                    if (liczbaOcen < 5 || liczbaOcen > 15) {
                        wpisOcen.setError(getString(R.string.bledne_dane));
                        blad.show();
                    }
                }
            }
        });

        przycisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intencja = new Intent(MainActivity.this,SecondActivity.class);
                intencja.putExtra("liczbaOcen",liczbaOcen);
                activityResultLaunch.launch(intencja);

            }
        });


//        wpisOcen.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////                if(liczbaOcen <5 || liczbaOcen > 15 ){
////                    wpisOcen.setError("Niepoprawne dane");
////                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//                if (wpisOcen.getText().toString().equals("")) {
//                    wpisOcen.setError("Niepoprawne dane");
//                    blad.show();
//                    checkRequiredFields();
//                } else {
//                    liczbaOcen = Integer.valueOf(String.valueOf(wpisOcen.getText()));
//                    checkRequiredFields();
//                    if (liczbaOcen < 5 || liczbaOcen > 15) {
//                        wpisOcen.setError("Niepoprawne dane");
//                        blad.show();
//                    }
//                }
//            }
//        });
    }

    private void checkRequiredFields() {
        if (!StringUtils.isEmpty(wpisOcen.getText().toString()) && StringUtils.isNumeric(wpisOcen.getText().toString())) {
            if (!wpisImie.getText().toString().isEmpty() && !wpisNazwisko.getText().toString().isEmpty() && Integer.parseInt(String.valueOf(wpisOcen.getText())) >= 5 && Integer.parseInt(String.valueOf(wpisOcen.getText())) <= 15) {
                przycisk.setVisibility(View.VISIBLE);
            } else {
                przycisk.setVisibility(View.INVISIBLE);
            }
        } else {
            przycisk.setVisibility((View.INVISIBLE));
        }
    }
}
