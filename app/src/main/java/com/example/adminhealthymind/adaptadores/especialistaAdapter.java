package com.example.adminhealthymind.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.adminhealthymind.R;
import com.example.adminhealthymind.listatransacciones;
import com.example.adminhealthymind.modelos.especialistaModel;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class especialistaAdapter extends ArrayAdapter<especialistaModel> {

    public especialistaAdapter(@NonNull Context context, ArrayList<especialistaModel> dataModalArrayList){
        super(context, 0, dataModalArrayList);

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_especialista, parent, false);
        }

        especialistaModel dataModal = getItem(position);

        TextView nomTV = listitemView.findViewById(R.id.nomEspe);
        TextView espeTV = listitemView.findViewById(R.id.especialidad);
        TextView rfcTV = listitemView.findViewById(R.id.rfc);


        nomTV.setText("Especialista: "+dataModal.getNombres());
        espeTV.setText("Especialidad: "+dataModal.getEspecialidad());
        rfcTV.setText("RFC: "+dataModal.getRfc());

        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), listatransacciones.class);
                intent.putExtra("rfc", dataModal.getRfc());
                getContext().startActivity(intent);
            }
        });
        return listitemView;
    }
}
