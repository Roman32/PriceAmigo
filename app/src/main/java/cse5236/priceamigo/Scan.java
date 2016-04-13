package cse5236.priceamigo;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

import java.io.IOException;
import java.net.InetAddress;

public class Scan extends CaptureActivity {
    String upc;
    //SharedPreferences sharedPreferences = getSharedPreferences(Settings.MyPREFERENCES, MODE_PRIVATE);
    boolean walmart;
    boolean bestbuy;
    DBHelper db;
    IntentIntegrator integrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        integrator.setBeepEnabled(true);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(checkInternetConnection()) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            WebScrape scraper = new WebScrape();
            SharedPreferences sharedPreferences = getSharedPreferences(Settings.MyPREFERENCES, MODE_PRIVATE);
            walmart = sharedPreferences.getBoolean("walmartKey", false);
            bestbuy = sharedPreferences.getBoolean("bestbuyKey", false);
            DBHelper db = new DBHelper(this);
            if (result != null && resultCode == RESULT_OK) {
                if (result.getContents() == null) {
                    //If scan is cancelled displays cancelled toast
                    Log.d("MainActivity", "Cancelled scan");
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    //Displays scan result in a toast
                    Log.d("MainActivity", "Scanned");
                    upc = result.getContents();
                    Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                    //final MediaPlayer beep = MediaPlayer.create(this, R.raw.beep);
                    //beep.start();
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

                    if (name.equals("Item not found") && name2.equals("Item not found")) {
                        name = name;
                    } else if (name.equals("Item not found") && !name2.equals("Item not found")) {
                        name = name2;
                    }

                    Item walmartItem = null;
                    Item bestbuyItem = null;
                    Intent wally = null;
                    Intent bestb = null;

                    if (walmart) {
                        String price = scraper.scrapeWallyWorld(name);
                        if ((name.equals("Item not found"))) {
                            price = "No price found";
                        }
                        wally = new Intent(Scan.this, SearchResult.class);
                        wally.putExtra("name", name);
                        wally.putExtra("upc", upc);
                        wally.putExtra("price", price);
                        wally.putExtra("store", "Walmart");
                        walmartItem = new Item(upc, name, "Walmart", price);

                        if (!name.equals("Item not found")) {
                            db.addItem(walmartItem);
                        }
                        //db.addResult(upc,name,price,"Walmart");
                        startActivity(wally);

                    }
                    if (bestbuy) {
                        String price = scraper.scrapeBB(name);
                        if ((name.equals("Item not found"))) {
                            price = "No price found";
                        }
                        bestb = new Intent(Scan.this, SearchResult.class);
                        bestb.putExtra("name", name);
                        bestb.putExtra("upc", upc);
                        bestb.putExtra("price", price);
                        bestb.putExtra("store", "Best Buy");
                        bestbuyItem = new Item(upc, name, "Best Buy", price);

                        if (!name.equals("Item not found")) {
                            db.addItem(bestbuyItem);
                        }
                        //db.addResult(upc,name,price,"Best Buy");
                        startActivity(bestb);

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
                //super.onActivityResult(requestCode, resultCode, data);
                Intent goBacktoMain = new Intent(Scan.this, MainMenu.class);
                startActivity(goBacktoMain);
            }
        }else {
            Toast.makeText(Scan.this, "Must be connected to internet to scan",
                    Toast.LENGTH_LONG).show();
            Intent goBacktoMain = new Intent(Scan.this, MainMenu.class);
            startActivity(goBacktoMain);
        }
    }

    public boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
            return true;

        } else {
            return false;
        }
    }

}
