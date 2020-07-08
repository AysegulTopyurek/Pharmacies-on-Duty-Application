package com.example.haritaolay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;


import com.example.haritaolay.Adapter.IlIlceAdapter;
import com.example.haritaolay.Model.Il;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends AppCompatActivity {

    private Button butn;
    ProgressDialog progressDialog;
    ArrayList<Il> tempIl;


    ListView liste_iller;
    ListView liste_ilceler;
    Context context,context1;

    //ListAdapter myAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tempIl);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button butn=(Button)findViewById(R.id.deneme);
        liste_iller = (ListView)findViewById(R.id.listeiller);
        liste_ilceler = (ListView)findViewById(R.id.listeilceler);
        ArrayList<String> temp1= new ArrayList<>();
        Intent intent = getIntent();
        tempIl = (ArrayList<Il>) intent.getSerializableExtra("MyObject");
        Log.d("TAG","NEREDE PATLADIN");
        for(Il i: tempIl){
            temp1.add(i.getIlAdi());
        }

        ListAdapter myAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,temp1);
        liste_iller.setAdapter(myAdapter);

        liste_iller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String secilenİl =(String) liste_iller.getItemAtPosition(position);
                Log.d("TAG",secilenİl);
                ilceBul(secilenİl);
            }
        });



        butn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GoogleMapsActivity.class));

            }
        });

    }






    String gecici1;
    public void ilceBul(String il){

        for(Il i: tempIl){
            if(i.getIlAdi().equals(il)){
                gecici1 = i.getIlAdi();
                ListAdapter myAdapter1=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,i.getIlceler());
                liste_ilceler.setAdapter(myAdapter1);

                liste_ilceler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        String secilenİlce =(String) liste_ilceler.getItemAtPosition(position);
                        Intent intent1 = new Intent(getApplicationContext(), Liste.class);
                        intent1.putExtra("AT",gecici1);
                        intent1.putExtra("AT1",secilenİlce);

                        startActivity(intent1);
                    }
                });
            }
           // Log.d("TAG", i.getIlceler().toString());
        }

    }




















































/*


       Il ililce1 = new Il(
                "il"
        );
        new getCity().execute();






    private class getCity extends AsyncTask<Void,Void,Void>{
        ArrayList<Il>ililceler=new ArrayList<>();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Lütfen Bekleyiniz");
            progressDialog.setCancelable(false);
            progressDialog.show();


        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
          ilIlceAdapter =new IlIlceAdapter(MainActivity.this,ililceler);
            listemain.setAdapter(ilIlceAdapter);
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
        @Override
        protected Void doInBackground(Void... params) {
            httpHandler =new HttpHandler();
            String jsonString=httpHandler.makeServiceCall(URL);
            Log.d("JSSON RESPONSE",jsonString);
         if(jsonString != null){

             try{
                 JSONObject jsonObject=new JSONObject(jsonString);
                 JSONArray ilveilceler=jsonObject.getJSONArray("ilveilceler");
                 JSONArray ilceler=jsonObject.getJSONArray("ilceleri");


                 for(int i=0;i<ilveilceler.length();i++) {
                     JSONObject il = ilveilceler.getJSONObject(i);
                     String ilAdi = il.getString("il");
                     for (int j = 0; j < ilceler.length(); j++) {
                         JSONObject ilce = ilveilceler.getJSONObject(j);
                         String ilceAdi = il.getString("ilceleri");


                         Il ililce1 = new Il(
                                 ilAdi

                         );
                         ililceler.add(ililce1);
                     }

                 }
             }catch(Exception e){

             }
         }else{
             Log.d("JSON RESPONSE ","Sayfa Kaynağı boş");
         }
            return null;
        }
    }*/
    public void logout(View view) {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

}
