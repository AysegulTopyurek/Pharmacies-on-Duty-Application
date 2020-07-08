package com.example.haritaolay;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PharmacyTask extends AsyncTask<Void, Void, Void> {

    private ProgressDialog pd;
    private Context context;
    private List<PharmacyModel> pharmacyItems;
    public static String pharmacy;
    public String url;
    public String ilce;

    public PharmacyTask(Context context , String url, String ilce) {
        this.context = context;
        this.url = url;
        this.ilce = ilce;
        Log.d("TAG", ilce);
        pharmacyItems = new ArrayList<>();
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("Lütfen bekleyiniz...");
        pd.setIndeterminate(false);
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        // TODO Auto-generated method stub
        String address, phone;//,baslık;

        Document doc;



        try {
            pharmacyItems.remove(pharmacyItems);
            doc = Jsoup.connect(url).ignoreContentType(true).get();
            for (Element row : doc.select("div.col")) {
                Elements strong = row.select("strong");
                Elements header = row.select("div.card-header");
                Elements body = row.select("div.card-body");
                address = body.text().substring(0, body.text().indexOf("/"));
                phone = body.text().substring(body.text().indexOf("Tel : "));
               // baslık=strong.text();

                PharmacyModel item = new PharmacyModel(header.text(), address, phone);//, baslık);
                if(strong.text().equals(ilce)) {
                    pharmacyItems.add(item);
               }
                pharmacy = header.text();//strong.text();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        pd.dismiss();
        PharmacyAdapter adapter = new PharmacyAdapter(context, pharmacyItems);
        Liste.lvPharmacy.setAdapter(adapter);
    }

}
