package com.example.adminhealthymind;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class registro extends AppCompatActivity {
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    EditText nombre, apellido, curp, telefono, correo, fecha, rfc;
    Spinner especialidad;
    String[] opcionesesp ={"Psicologia clinica", "Psicologia educativa", "Tanatologia"};
    String espeact ="";
    String username;
    String pswd = "1234567";
    Button registrar;
    final Calendar myCalendar= Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        FirebaseApp.initializeApp(this);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        nombre=(EditText) findViewById(R.id.setnombre);
        apellido=(EditText) findViewById(R.id.setapellidos);
        curp=(EditText) findViewById(R.id.curp);
        telefono =(EditText) findViewById(R.id.set_telefono);
        correo=(EditText)findViewById(R.id.setemail);
        fecha =(EditText) findViewById(R.id.setfecha);
        rfc =(EditText) findViewById(R.id.rfc);
        especialidad=(Spinner)findViewById(R.id.setespe);
        registrar =(Button)findViewById(R.id.registerbtn);
        apellido.setError(null);
        curp.setError(null);
        telefono.setError(null);
        correo.setError(null);
        fecha.setError(null);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = nombre.getText().toString().trim();
                String ape = apellido.getText().toString().trim();
                String curpp = curp.getText().toString().trim();
                String email = correo.getText().toString().trim();
                String tele = telefono.getText().toString().trim();
                String rfcc = rfc.getText().toString().trim();
                String fechanac = fecha.getText().toString().trim();
                final String regex = "(?:[^<>()\\[\\].,;:\\s@\"]+(?:\\.[^<>()\\[\\].,;:\\s@\"]+)*|\"[^\\n\"]+\")@(?:[^<>()\\[\\].,;:\\s@\"]+\\.)+[^<>()\\[\\]\\.,;:\\s@\"]{2,63}";
                final String regexcurp ="^[A-Z]{4}[0-9]{6}[HM]{1}[A-Z]{6}[0-9]{1}$";

                if (nom.isEmpty() && ape.isEmpty() && curpp.isEmpty() && email.isEmpty() && tele.isEmpty() && rfcc.isEmpty() && fechanac.isEmpty()){
                    Toast.makeText(registro.this, "Es obligatorio llenar todos los campos", Toast.LENGTH_LONG).show();
                }
                else if (!curpp.matches(regexcurp)) {
                    curp.setError("Ingresa una curp valida");
                    curp.requestFocus();

                } else if(tele.length()>10 || tele.length()<10){
                    telefono.setError("Ingresa un numero de telefono de 10 digitos");
                    telefono.requestFocus();

                }else if( !email.matches(regex)){
                    correo.setError("Ingresa un correo valido");
                    correo.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(correo.getText().toString(), pswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("nombres", nom);
                        map.put("apellido", ape);
                        map.put("email",email);
                        map.put("fechanac",fechanac);
                        map.put("telefono",tele);
                        map.put("rfc",rfcc);
                        map.put("especialidad",espeact);
                        mFirestore.collection("users-especialista").document(curpp).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                finish();.
                                startActivity(new Intent(registro.this, mainadmin.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(registro.this, "Registro no exitoso", Toast.LENGTH_LONG).show();
                            }
                        });
                        Toast.makeText(registro.this, "Registro exitoso", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(registro.this, "Registro no exitoso AUTH", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });


        ArrayAdapter<String> aa = new ArrayAdapter<>(
                registro.this, android.R.layout.simple_dropdown_item_1line,opcionesesp);
        especialidad.setAdapter(aa);
        especialidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                espeact = opcionesesp[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        DatePickerDialog.OnDateSetListener date= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(registro.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

   }

    private void updateLabel() {
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        fecha.setText(dateFormat.format(myCalendar.getTime()));
    }
}