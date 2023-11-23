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
import com.example.adminhealthymind.modelos.transaccionModel;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class transaccionAdapter extends ArrayAdapter<transaccionModel> {

    public transaccionAdapter(@NonNull Context context, ArrayList<transaccionModel> dataModalArrayList){
        super(context, 0, dataModalArrayList);

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_transaccion, parent, false);
        }

        transaccionModel dataModal = getItem(position);

        TextView nomTV = listitemView.findViewById(R.id.nomPac);
        TextView fechaTV = listitemView.findViewById(R.id.fecha);
        TextView costoTV = listitemView.findViewById(R.id.costo);


        nomTV.setText("Paciente: "+dataModal.getId_paciente());
        fechaTV.setText("Fecha de la cita: "+dataModal.getFecha_cita());
        costoTV.setText("Total: $300.00 MXN");

        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Item clicked is : " + dataModal.getRfc_especialista(), Toast.LENGTH_SHORT).show();
            }
        });
        return listitemView;
    }
}
