package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private RecyclerView adapter;

    private int liczbaOcen;
    private String[] przedm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        adapter = (RecyclerView) findViewById(R.id.przedmioty);
        LinearLayoutManager rlm = new LinearLayoutManager(this);
        adapter.setLayoutManager(rlm);
        InteraktywnyAdapterTablicy interakt = new InteraktywnyAdapterTablicy(getListaOcen(),this);
        adapter.setAdapter(interakt);
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