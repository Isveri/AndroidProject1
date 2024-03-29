package com.example.lab1;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InteraktywnyAdapterTablicy extends RecyclerView.Adapter<InteraktywnyAdapterTablicy.OcenyViewHolder>{

    private List<ModelOceny> mListaOcen;
    private LayoutInflater mPompka;
    private RadioButton radioButton;
    private RadioGroup lastCheckedRadioGroup;
    private GetBackSum getSum;
    private List<Integer> suma = new ArrayList<>();


    /**
     * konstruktor ustawiający wstepne wartości obiektu
     */

    public InteraktywnyAdapterTablicy(List<ModelOceny> listaOcen,Activity kontekst){
        mPompka = kontekst.getLayoutInflater();
        this.mListaOcen = listaOcen;
        for( int i =0 ;i<listaOcen.size();i++){
            suma.add(0);
        }
        try{
            this.getSum = ((GetBackSum)mPompka.getContext());
        }catch (ClassCastException e){
            throw new ClassCastException(e.getMessage());
        }
    }

    /**
     * Nadpisanie metod
     * onCreateViewHolder - w momencie tworzenia nowego ViewHoldera zwracany jest obiekt
     * klasy OcenyViewHolder na podstawie layoutu wiersz_oceny, klasa OcenyViewHolder widoczna jest nizej
     */
    @NonNull
    @Override
    public OcenyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View wiersz = mPompka.inflate(R.layout.wiersz_oceny,null);
        return new OcenyViewHolder(wiersz);
    }

    /**
     * Z tego co rozumiem to dla każdego elementu w liście wykonywana jest ta metoda
     * ustawiane jest tu id dla każdego wiersza, nazwa dla oceny, a także tag dla grupy by wybrac potem 1 wartosc
     * potem w pętli tworzone są radiobuttony dla każdego radiogroupa z ocenami {2-5}, na koncu dodawane jest to do widoku
     */
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

    /**
     * Nadpisanie funckji zwracającej rozmiar tablicy
     * potrzebne jest bo inaczej nie bedzie działalo
     */
    @Override
    public int getItemCount() {
        return mListaOcen.size();
    }


    /**
     * Klasa odpowiedzialna za wiersz w RecyclerView
     * Tutaj ustawiane są które pola mają odnosić się do jakich elementow na ekranie
      */

    public class OcenyViewHolder extends RecyclerView.ViewHolder {

        private RadioGroup mGroup;
        private TextView mText;

        public OcenyViewHolder(@NonNull View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.package_name);
            mGroup =(RadioGroup) itemView.findViewById(R.id.radioGroup);

        /**
        * Dodanie listenera obsługującego zmianę zaznaczonego radiobuttona,
         * liczona jest tu suma zaznaczonych elementów i wysyłana
        */
            mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                lastCheckedRadioGroup = radioGroup;
                if (lastCheckedRadioGroup.getCheckedRadioButtonId() != -1) {


                    radioButton = (RadioButton) itemView.findViewById(i);
//                    SecondActivity.suma.set((Integer) radioGroup.getTag(), Integer.parseInt((String) radioButton.getText()));
                    suma.set((Integer) radioGroup.getTag(), Integer.parseInt((String) radioButton.getText()));
                    Intent intent = new Intent();
                    Bundle args = new Bundle();
                    args.putSerializable("ARRAYLIST",(Serializable) suma);
                    intent.putExtra("suma", args);
                    getSum.getBackSum(intent);
                }
                lastCheckedRadioGroup = radioGroup;

            }
        });
        }

        }
    public interface GetBackSum{
        public void getBackSum(Intent intent);
    }
    }

