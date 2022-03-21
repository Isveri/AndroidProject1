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

public class SecondActivity extends AppCompatActivity {
    private RecyclerView adapter;
    private Button p;
    private int liczbaOcen;
    private String[] przedm;
    private Double srednia = 3.5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        p = findViewById(R.id.sredniaBTN);
        adapter = (RecyclerView) findViewById(R.id.przedmioty);
        LinearLayoutManager rlm = new LinearLayoutManager(this);
        adapter.setLayoutManager(rlm);
        InteraktywnyAdapterTablicy interakt = new InteraktywnyAdapterTablicy(getListaOcen(),this);
        adapter.setAdapter(interakt);
       // p = findViewById(R.id.sredniaBTN);



        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        List<ModelOceny> modelList = new ArrayList<ModelOceny>();
        for(int i = 0 ;i<liczbaOcen;i++){
            modelList.add(new ModelOceny(przedm[i]));
        }
        return modelList;
    }

}