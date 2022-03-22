package com.example.lab1;

import android.app.Activity;
import android.content.res.Resources;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InteraktywnyAdapterTablicy extends RecyclerView.Adapter<InteraktywnyAdapterTablicy.OcenyViewHolder>{

    private List<ModelOceny> mListaOcen;
    private LayoutInflater mPompka;
    private RadioButton radioButton;
    private RadioGroup lastCheckedRadioGroup;




    public InteraktywnyAdapterTablicy(List<ModelOceny> listaOcen,Activity kontekst){
        mPompka = kontekst.getLayoutInflater();
        this.mListaOcen = listaOcen;
    }

    @NonNull
    @Override
    public OcenyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View wiersz = mPompka.inflate(R.layout.wiersz_oceny,null);
        return new OcenyViewHolder(wiersz);
    }

    @Override
    public void onBindViewHolder(@NonNull OcenyViewHolder holder, int position) {
        ModelOceny ocena = mListaOcen.get(position);


        holder.mText.setText(ocena.getNazwa());
        holder.mGroup.setTag(position);
        int id = (position*16);

        for(int oc:ocena.getOceny()) {
            RadioButton rb = new RadioButton(InteraktywnyAdapterTablicy.this.mPompka.getContext());
            rb.setId(id++);
            rb.setText(String.valueOf(oc));

            holder.mGroup.addView(rb);
        }

    }

    @Override
    public int getItemCount() {
        return mListaOcen.size();
    }





    public class OcenyViewHolder extends RecyclerView.ViewHolder {

        private RadioGroup mGroup;
        private TextView mText;

        public OcenyViewHolder(@NonNull View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.package_name);
            mGroup =(RadioGroup) itemView.findViewById(R.id.radioGroup);

       mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                lastCheckedRadioGroup = radioGroup;
                if (lastCheckedRadioGroup.getCheckedRadioButtonId() != -1) {


                    radioButton = (RadioButton) itemView.findViewById(i);
                    SecondActivity.suma.set((Integer) radioGroup.getTag(), Integer.parseInt((String) radioButton.getText()));

                }
                lastCheckedRadioGroup = radioGroup;

            }
        });


        }
        }
    }

