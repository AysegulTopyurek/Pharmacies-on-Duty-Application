package com.example.haritaolay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haritaolay.Model.Il;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity {


    EditText mEmail,mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    ArrayList<String> ilceler ;
    ArrayList<Il> iller = new ArrayList<Il>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail= findViewById(R.id.Email);
        mPassword= findViewById(R.id.Password);
        progressBar= findViewById(R.id.progressBar);
        fAuth= FirebaseAuth.getInstance();
        mLoginBtn= findViewById(R.id.loginBtn);
        mCreateBtn= findViewById(R.id.createText);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();//"aso@hotmail.com";
                String password =mPassword.getText().toString().trim();//"123456";
                //String email = mEmail.getText().toString().trim();
                //String password = mPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email boş olamaz!");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Şifre boş olamaz!");
                    return;
                }
                if(password.length()<6){
                    mPassword.setError("Pasaport 6 karakterden az olamaz!");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Başarılı Giriş!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("MyObject",iller);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Login.this, "Error!"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });


            }
        });
        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ilveilceler");

        // Read from the database

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String il_isim = ds.child("il").getValue(String.class);
                    //Log.d("TAG","yOKSA BURDAMI"+il_isim);
                    //tempIl.add(il_isim);
                    ilceler = new ArrayList<String>();
                    for(DataSnapshot child : ds.child("ilceleri").getChildren()) {
                        String ilce = child.getValue(String.class);
                        ilceler.add(ilce);
                    }
                    Il il = new Il(il_isim, ilceler);
                    iller.add(il);
                }


                /*
                for(Il i: iller){
                    Log.d("TAG", i.getIlceler().toString());
                }
                */
                //liste_iller.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("database1", "Failed to read value.", error.toException());
            }
        });
    }
}
