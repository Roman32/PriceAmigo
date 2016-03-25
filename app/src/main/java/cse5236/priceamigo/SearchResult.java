package cse5236.priceamigo;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.List;

public class SearchResult extends Activity {

    LocationManager locationManager;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        TextView name = (TextView)findViewById(R.id.name);
        TextView upc = (TextView)findViewById(R.id.upc);
        TextView price = (TextView)findViewById(R.id.price);
        TextView store = (TextView)findViewById(R.id.store);

        name.setText(getIntent().getStringExtra("name"));
        upc.setText(getIntent().getStringExtra("upc"));
        price.setText(getIntent().getStringExtra("price"));
        store.setText(getIntent().getStringExtra("store"));

        Button butt = (Button)findViewById(R.id.button);
        if(getIntent().getStringExtra("store").equals("Amazon")){
            butt.setVisibility(View.GONE);
            butt.setEnabled(false);
        }
        butt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                double x, y;
                try {
                    Location location = getLastKnownLocation();
                    x = location.getLatitude();
                    y = location.getLongitude();

                    Display display = getWindowManager().getDefaultDisplay();
                    Point size = new Point();
                    display.getSize(size);
                    int width = size.x;
                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    Integer radius = sharedpreferences.getInt("radiusKey", 4); //= get diameter from settings

                    int zoom = calculateZoomLevel(width, radius);

                    String url = "https://www.google.com/maps/search/" + getIntent().getStringExtra("store") + "/@" + x + "," + y + "," + zoom + "z";

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }catch (NullPointerException e){
                    e.printStackTrace();
                }catch (ActivityNotFoundException e){
                    e.printStackTrace();
                }
            }
        });


    }

    public Location getLocation(){
        // Get the location manager
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, false);
        try {
            Location location = getLastKnownLocation();
            Double lat,lon;
            try {
                return location;
            }
            catch (NullPointerException e){
                Log.d("tag", "ERROR 1");
                e.printStackTrace();
                return null;
            }
        }catch(SecurityException e){
            Log.d("tag", "ERROR 2");
            e.printStackTrace();
            return null;
        }
    }

    private Location getLastKnownLocation() {
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            List<String> providers = locationManager.getProviders(true);
            Location bestLocation = null;
            for (String provider : providers) {
                Location l = locationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    // Found best last known location: %s", l);
                    bestLocation = l;
                }
            }
            return bestLocation;
        }catch (SecurityException e){
            e.printStackTrace();
            return null;
        }
    }


    //given the width of the screen in pixels, and a max driving radius r, this function returns the zoom level in google maps to be used
    private int calculateZoomLevel(int screenWidth, int r) {
        int d = 2*r;
        double equatorLength = 24902; // in miles
        double widthInPixels = screenWidth;
        double milesPerPixel = equatorLength / 256;
        int zoomLevel = 1;
        while ((milesPerPixel * widthInPixels) > d) {
            milesPerPixel /= 2;
            zoomLevel++;
        }
        return zoomLevel;
    }


}
