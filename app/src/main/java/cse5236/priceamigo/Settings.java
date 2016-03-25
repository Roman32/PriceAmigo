package cse5236.priceamigo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String walmart = "walmartKey";
    public static final String amazon = "amazonKey";
    public static final String target = "targetKey";
    public static final String bestbuy = "bestbuyKey";
    public static final String radius = "radiusKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final CheckBox walmartButton = (CheckBox) findViewById(R.id.walmart);
        final CheckBox amazonButton = (CheckBox) findViewById(R.id.amazon);
        final CheckBox targetButton = (CheckBox) findViewById(R.id.target);
        final CheckBox bestbuyButton = (CheckBox) findViewById(R.id.bestbuy);
        final EditText radiusButton = (EditText) findViewById(R.id.radius);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        boolean walmartState = sharedpreferences.getBoolean(walmart, false);
        boolean amazonState = sharedpreferences.getBoolean(amazon,false);
        boolean targetState = sharedpreferences.getBoolean(target, false);
        boolean bestbuyState = sharedpreferences.getBoolean(bestbuy, false);
        Integer radiusValue = sharedpreferences.getInt(radius, -1);

        if((walmartState||amazonState||targetState||bestbuyState) && radiusValue != -1){
            walmartButton.setChecked(walmartState);
            amazonButton.setChecked(amazonState);
            targetButton.setChecked(targetState);
            bestbuyButton.setChecked(bestbuyState);
            radiusButton.setText(radiusValue.toString());
        }
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean walmartState = walmartButton.isChecked();
                boolean amazonState = amazonButton.isChecked();
                boolean targetState = targetButton.isChecked();
                boolean bestbuyState = bestbuyButton.isChecked();
                String radiusValue = radiusButton.getText().toString();

                if(!(walmartState||amazonState||targetState||bestbuyState)){
                    Toast.makeText(Settings.this, "Choose at least one store",
                            Toast.LENGTH_LONG).show();
                }else if(radiusValue.equals("")){
                    Toast.makeText(Settings.this, "Put in a value for radius",
                            Toast.LENGTH_LONG).show();
                }else{
                    int radiusValueInt = Integer.parseInt(radiusValue);

                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putBoolean(walmart, walmartState);
                    editor.putBoolean(amazon, amazonState);
                    editor.putBoolean(target, targetState);
                    editor.putBoolean(bestbuy, bestbuyState);
                    editor.putInt(radius, radiusValueInt);
                    editor.commit();

                    Toast.makeText(Settings.this, "Settings Saved",
                            Toast.LENGTH_LONG).show();

                    startActivity(new Intent(Settings.this, MainMenu.class));
                }

            }
        });

    }
}
