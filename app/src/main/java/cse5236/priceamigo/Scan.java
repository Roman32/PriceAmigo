package cse5236.priceamigo;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Scan extends AppCompatActivity {
    String upc;
    //SharedPreferences sharedPreferences = getSharedPreferences(Settings.MyPREFERENCES, MODE_PRIVATE);
    boolean walmart;
    boolean bestbuy;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        integrator.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        WebScrape scraper = new WebScrape();
        SharedPreferences sharedPreferences = getSharedPreferences(Settings.MyPREFERENCES, MODE_PRIVATE);
        walmart = sharedPreferences.getBoolean("walmartKey", false);
        bestbuy = sharedPreferences.getBoolean("bestbuyKey", false);
        DBHelper db = new DBHelper(this);
        if(result != null) {
            if(result.getContents() == null) {
                //If scan is cancelled displays cancelled toast
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                //Displays scan result in a toast
                Log.d("MainActivity", "Scanned");
                upc = result.getContents();
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                final MediaPlayer beep = MediaPlayer.create(this, R.raw.beep);
                beep.start();
                //TODO
                /*
                    in order to send the results to the results page, add this
                    Intent i = new Intent(Scan.this,SearchResult.class);
                    i.putExtra("name", <item name here as String>);
                    i.putExtra("upc", <upc here as String>);
                    i.putExtra("price", <price here as String>);
                    i.putExtra("store", <store here as String>);
                    startActivity(i);
                 */
                String name = scraper.getNameFromWally(upc);
                String name2 = scraper.getItemName(upc);

                if(name.equals("Item not found") && name2.equals("Item not found")){
                    name = name;
                }else if(name.equals("Item not found") && !name2.equals("Item not found")){
                    name = name2;
                }

                if(walmart) {
                    String price = scraper.scrapeWallyWorld(name);
                    if((name.equals("Item not found"))){
                        price = "No price found";
                    }
                    Intent i = new Intent(Scan.this, SearchResult.class);
                    i.putExtra("name", name);
                    i.putExtra("upc", upc);
                    i.putExtra("price", price);
                    i.putExtra("store", "Walmart");
                    Item newItem = new Item(name,price,upc);
                    if(!name.equals("Item not found")) {
                        db.addResult(upc,name,price,"Walmart");
                    }
                    //db.addResult(upc,name,price,"Walmart");
                    startActivity(i);
                }
                if(bestbuy) {
                    String price = scraper.scrapeBB(name);
                    if((name.equals("Item not found"))){
                        price = "No price found";
                    }
                    Intent i = new Intent(Scan.this, SearchResult.class);
                    i.putExtra("name", name);
                    i.putExtra("upc", upc);
                    i.putExtra("price", price);
                    i.putExtra("store", "Best Buy");
                    Item newItem = new Item(name,price,upc);
                    if(!name.equals("Item not found")) {
                        db.addResult(upc,name,price,"Best Buy");
                    }
                    //db.addResult(upc,name,price,"Best Buy");
                    startActivity(i);
                }

                //hard coded testing lines
                //TODO delete later
                /*
                Intent i = new Intent(Scan.this,SearchResult.class);
                i.putExtra("name", "Android Programming: The Big Nerd Ranch Guide");
                i.putExtra("upc", "9780134171456");
                i.putExtra("price", "$27.30");
                i.putExtra("store", "Walmart");
                startActivity(i);
                */
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
