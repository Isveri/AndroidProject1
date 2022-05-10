package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements InteraktywnyAdapterTablicy.GetBackSum {
    private Button p;
    private int liczbaOcen;
    private String[] przedm;
    private double srednia;
    private List<Integer> suma;
    List<ModelOceny> modelList = new ArrayList<ModelOceny>();


    /**
     *
     * Funkcja wywoływana przy tworzeniu aktywności, ustawia odpowiednie pola do tych na ekranie,
     * ustawia wybraną ilosc ocen w wierszach za pomocą RecyclerView
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        p = findViewById(R.id.sredniaBTN);

        RecyclerView numberRecyclerView=
                findViewById(R.id.przedmioty);
        InteraktywnyAdapterTablicy myAdapter=new InteraktywnyAdapterTablicy(getListaOcen(),this);
        numberRecyclerView.setAdapter(myAdapter);
        numberRecyclerView.setLayoutManager(
                new LinearLayoutManager(this));


        /**
         * Listener przycisku obliczający srednią zaznaczonych ocen i wysyłający je do głównej aktywności
         * tak jak napisałem wczesniej jak srednia <3 to resul 2 jak >3 to resul 3 tak zeby bylo wiadomo jakie
         * info dac na ekranie głównym i jaki komunikat ma dostac uzytkownik
         */

        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tm = 0;
                for (int i:suma){
                    tm += i;
                }
                srednia = (double)tm/liczbaOcen;
                Intent resultIntent = new Intent();
                resultIntent.putExtra("srednia",srednia);
                if(srednia<3){
                    setResult(2,resultIntent);
                    finish();}
                else{
                    setResult(3,resultIntent);
                    finish();
                }
            }
        });
    }

    /**
     * Funckja zwracająca listę wszystkich ocen
     */
    private List<ModelOceny> getListaOcen(){

        Bundle ext = getIntent().getExtras();
        liczbaOcen = ext.getInt("liczbaOcen");
        przedm = getResources().getStringArray(R.array.przedmioty);
        suma = new ArrayList<>();
        for( int i =0 ;i<liczbaOcen;i++){
            suma.add(0);
        }
        for(int i = 0 ;i<liczbaOcen;i++){
            modelList.add(new ModelOceny(przedm[i]));
        }
        return modelList;
    }

    /**
     * Nadpisanie funkcji zaimplementowanego interfejsu w celu pobrania sumy zaznaczonych ocen z przedmiotów
     */
    @Override
    public void getBackSum(Intent intent) {
        Bundle args = intent.getBundleExtra("suma");
        suma = (List<Integer>) args.getSerializable("ARRAYLIST");
    }
}