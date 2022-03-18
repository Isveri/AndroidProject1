package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    String imie;
    String nazwisko;
    int liczbaOcen;

    EditText wpisOcen;
    EditText wpisImie;
    EditText wpisNazwisko;
    Button przycisk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wpisOcen = findViewById(R.id.ocenyWpis);
        wpisImie = findViewById(R.id.imieWpis);
        wpisNazwisko = findViewById(R.id.nazwiskoWpis);
        przycisk = findViewById(R.id.button);


        imie = wpisImie.getText().toString();
        nazwisko = wpisNazwisko.getText().toString();
        przycisk.setVisibility(View.INVISIBLE);

        Toast blad = Toast.makeText(this,"Niepoprawne dane", Toast.LENGTH_SHORT);

        wpisImie.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                imie = wpisImie.getText().toString();
                checkRequiredFields();
                if (imie.equals("")) {
                    wpisImie.setError("Niepoprawne dane");
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
                    wpisNazwisko.setError("Niepoprawne dane");
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
                    wpisOcen.setError("Niepoprawne dane");
                    blad.show();
                    checkRequiredFields();
                } else {
                    liczbaOcen = Integer.parseInt(String.valueOf(wpisOcen.getText()));
                    checkRequiredFields();
                    if (liczbaOcen < 5 || liczbaOcen > 15) {
                        wpisOcen.setError("Niepoprawne dane");
                        blad.show();
                    }
                }
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
        }
        else{
            przycisk.setVisibility((View.INVISIBLE));
        }
    }
}
