package com.example.adminhealthymind;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adminhealthymind.adaptadores.especialistaAdapter;
import com.example.adminhealthymind.modelos.especialistaModel;
import com.example.adminhealthymind.modelos.transaccionModel;
import com.example.adminhealthymind.adaptadores.transaccionAdapter;
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
public class listatransacciones extends AppCompatActivity {
    ListView ListaTransaccion;
    ArrayList<transaccionModel> dataModalArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listatransacciones);
        ListaTransaccion = (ListView) findViewById(R.id.Listatransacciones);
        FirebaseFirestore mFirestore =  FirebaseFirestore.getInstance();
        dataModalArrayList = new ArrayList<>();
        mFirestore.collection("transacciones").whereEqualTo("rfc_especialista", getIntent().getStringExtra("rfc")).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            java.util.List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                transaccionModel dataModal = d.toObject(transaccionModel.class);
                                dataModalArrayList.add(dataModal);
                            }
                            transaccionAdapter adapter = new transaccionAdapter(listatransacciones.this, dataModalArrayList);

                            ListaTransaccion.setAdapter(adapter);


                        } else {

                            Toast.makeText(listatransacciones.this, "No hay transacciones.", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }
}