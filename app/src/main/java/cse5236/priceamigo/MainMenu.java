package cse5236.priceamigo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;


public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainMenu", "In on Create");
        setContentView(R.layout.activity_main_menu);
        Button scanButt = (Button)findViewById(R.id.scan_button);
        scanButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isInternetAvailable()) {
                    startActivity(new Intent(MainMenu.this, Scan.class));
                }else{
                    Toast.makeText(MainMenu.this, "Must be connected to internet to scan",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        Button settButt = (Button)findViewById(R.id.settings_button);
        settButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this,Settings.class));
            }
        });
        Button histButt = (Button)findViewById(R.id.history_button);
        histButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this,History.class));
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("MainMenu", "In on Start");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("MainMenu", "In on Resume");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("MainMenu", "In on Stop");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("MainMenu", "In on Restart");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("MainMenu", "In on Destroy");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("MainMenu", "In on Pause");
    }

    public static boolean isInternetAvailable() {

        Runtime runtime = Runtime.getRuntime();
        try {

            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);

        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }
}
