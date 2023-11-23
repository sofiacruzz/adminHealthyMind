package com.example.adminhealthymind;

import androidx.appcompat.app.AppCompatActivity;
import com.example.adminhealthymind.modelos.especialistaModel;
import com.example.adminhealthymind.adaptadores.especialistaAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class listaespe extends AppCompatActivity {
    ListView ListaEspe;
    ArrayList<especialistaModel> dataModalArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaespe);
        ListaEspe = (ListView) findViewById(R.id.Listaespe);
        FirebaseFirestore mFirestore =  FirebaseFirestore.getInstance();
        dataModalArrayList = new ArrayList<>();
        mFirestore.collection("users-especialista").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            java.util.List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                especialistaModel dataModal = d.toObject(especialistaModel.class);
                                dataModalArrayList.add(dataModal);
                            }
                            especialistaAdapter adapter = new especialistaAdapter(listaespe.this, dataModalArrayList);

                            ListaEspe.setAdapter(adapter);
                        } else {

                            Toast.makeText(listaespe.this, "No hay especialistas.", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }
}