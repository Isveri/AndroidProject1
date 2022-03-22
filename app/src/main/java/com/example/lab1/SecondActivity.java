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

    @Override
    public void getBackSum(Intent intent) {
        Bundle args = intent.getBundleExtra("suma");
        suma = (List<Integer>) args.getSerializable("ARRAYLIST");
    }
}