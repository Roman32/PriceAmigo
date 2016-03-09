package cse5236.priceamigo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


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
                startActivity(new Intent(MainMenu.this, Scan.class));
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
                startActivity(new Intent(MainMenu.this,Scan_History.class));
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
}
