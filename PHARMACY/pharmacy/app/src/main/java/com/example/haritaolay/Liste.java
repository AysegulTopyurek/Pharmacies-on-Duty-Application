package com.example.haritaolay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.haritaolay.Model.Il;

import java.util.ArrayList;
import java.util.List;

public class Liste extends AppCompatActivity {
    public static ListView lvPharmacy;
    private Context context;
    public String url;
    String str1,str2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

       lvPharmacy = findViewById(R.id.lvPharmacy);
        Intent intent = getIntent();
        str1 =  intent.getStringExtra("AT");
        str2 =  intent.getStringExtra("AT1");
        Log.d("TAG", str1+" "+str2);
        String url = "https://www.nobetcieczanebul.com/"+str1+"-nobetci-eczane";
        Log.d("TAG", url);
       new PharmacyTask(this,url,str2).execute();

    }
}
