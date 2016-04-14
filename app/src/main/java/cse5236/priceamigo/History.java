package cse5236.priceamigo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        DBHelper db = new DBHelper(this);
        final List<Item> list = db.getAllItems();

        TextView name1 = (TextView)findViewById(R.id.name1);
        name1.setVisibility(View.INVISIBLE);
        TextView name2 = (TextView)findViewById(R.id.name2);
        name2.setVisibility(View.INVISIBLE);
        TextView name3 = (TextView)findViewById(R.id.name3);
        name3.setVisibility(View.INVISIBLE);
        TextView name4 = (TextView)findViewById(R.id.name4);
        name4.setVisibility(View.INVISIBLE);
        TextView name5 = (TextView)findViewById(R.id.name5);
        name5.setVisibility(View.INVISIBLE);
        TextView name6 = (TextView)findViewById(R.id.name6);
        name6.setVisibility(View.INVISIBLE);
        TextView name7 = (TextView)findViewById(R.id.name7);
        name7.setVisibility(View.INVISIBLE);
        TextView name8 = (TextView)findViewById(R.id.name8);
        name8.setVisibility(View.INVISIBLE);
        TextView name9 = (TextView)findViewById(R.id.name9);
        name9.setVisibility(View.INVISIBLE);
        TextView name10 = (TextView)findViewById(R.id.name10);
        name10.setVisibility(View.INVISIBLE);

        List<TextView> names = new ArrayList<TextView>();

        names.add(name1);
        names.add(name2);
        names.add(name3);
        names.add(name4);
        names.add(name5);
        names.add(name6);
        names.add(name7);
        names.add(name8);
        names.add(name9);
        names.add(name10);

        Button but1 = (Button)findViewById(R.id.button1);
        if(list.size() >= 1) {
            but1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(History.this, SearchResult.class);
                    i.putExtra("name", list.get(0).getName());
                    i.putExtra("upc", list.get(0).getUpc());
                    i.putExtra("price", list.get(0).getPrice());
                    i.putExtra("store", list.get(0).getSupplier());
                    i.putExtra("historyFlag", true);
                    startActivity(i);
                }
            });
        }
        but1.setVisibility(View.INVISIBLE);
        but1.setEnabled(false);


        Button but2 = (Button)findViewById(R.id.button2);
        if(list.size() >= 2) {
            but2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(History.this, SearchResult.class);
                    i.putExtra("name", list.get(1).getName());
                    i.putExtra("upc", list.get(1).getUpc());
                    i.putExtra("price", list.get(1).getPrice());
                    i.putExtra("store", list.get(1).getSupplier());
                    i.putExtra("historyFlag", true);
                    startActivity(i);
                }
            });
        }
        but2.setVisibility(View.INVISIBLE);
        but2.setEnabled(false);

        Button but3 = (Button)findViewById(R.id.button3);
        if(list.size() >= 3) {
            but3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(History.this, SearchResult.class);
                    i.putExtra("name", list.get(2).getName());
                    i.putExtra("upc", list.get(2).getUpc());
                    i.putExtra("price", list.get(2).getPrice());
                    i.putExtra("store", list.get(2).getSupplier());
                    i.putExtra("historyFlag", true);
                    startActivity(i);
                }
            });
        }
        but3.setVisibility(View.INVISIBLE);
        but3.setEnabled(false);

        Button but4 = (Button)findViewById(R.id.button4);
        if(list.size() >= 4) {
            but4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(History.this, SearchResult.class);
                    i.putExtra("name", list.get(3).getName());
                    i.putExtra("upc", list.get(3).getUpc());
                    i.putExtra("price", list.get(3).getPrice());
                    i.putExtra("store", list.get(3).getSupplier());
                    i.putExtra("historyFlag", true);
                    startActivity(i);
                }
            });
        }
        but4.setVisibility(View.INVISIBLE);
        but4.setEnabled(false);

        Button but5 = (Button)findViewById(R.id.button5);
        if(list.size() >= 5) {
            but5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(History.this, SearchResult.class);
                    i.putExtra("name", list.get(4).getName());
                    i.putExtra("upc", list.get(4).getUpc());
                    i.putExtra("price", list.get(4).getPrice());
                    i.putExtra("store", list.get(4).getSupplier());
                    i.putExtra("historyFlag", true);
                    startActivity(i);
                }
            });
        }
        but5.setVisibility(View.INVISIBLE);
        but5.setEnabled(false);

        Button but6 = (Button)findViewById(R.id.button6);
        if(list.size() >= 6) {
            but6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(History.this, SearchResult.class);
                    i.putExtra("name", list.get(5).getName());
                    i.putExtra("upc", list.get(5).getUpc());
                    i.putExtra("price", list.get(5).getPrice());
                    i.putExtra("store", list.get(5).getSupplier());
                    i.putExtra("historyFlag", true);
                    startActivity(i);
                }
            });
        }
        but6.setVisibility(View.INVISIBLE);
        but6.setEnabled(false);

        Button but7 = (Button)findViewById(R.id.button7);
        if(list.size() >= 7) {
            but7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(History.this, SearchResult.class);
                    i.putExtra("name", list.get(6).getName());
                    i.putExtra("upc", list.get(6).getUpc());
                    i.putExtra("price", list.get(6).getPrice());
                    i.putExtra("store", list.get(6).getSupplier());
                    i.putExtra("historyFlag", true);
                    startActivity(i);
                }
            });
        }
        but7.setVisibility(View.INVISIBLE);
        but7.setEnabled(false);

        Button but8 = (Button)findViewById(R.id.button8);
        if(list.size() >= 8) {
            but8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(History.this, SearchResult.class);
                    i.putExtra("name", list.get(7).getName());
                    i.putExtra("upc", list.get(7).getUpc());
                    i.putExtra("price", list.get(7).getPrice());
                    i.putExtra("store", list.get(7).getSupplier());
                    i.putExtra("historyFlag", true);
                    startActivity(i);
                }
            });
        }
        but8.setVisibility(View.INVISIBLE);
        but8.setEnabled(false);

        Button but9 = (Button)findViewById(R.id.button9);
        if(list.size() >= 9) {
            but8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(History.this, SearchResult.class);
                    i.putExtra("name", list.get(8).getName());
                    i.putExtra("upc", list.get(8).getUpc());
                    i.putExtra("price", list.get(8).getPrice());
                    i.putExtra("store", list.get(8).getSupplier());
                    i.putExtra("historyFlag", true);
                    startActivity(i);
                }
            });
        }
        but9.setVisibility(View.INVISIBLE);
        but9.setEnabled(false);

        Button but10 = (Button)findViewById(R.id.button10);
        if(list.size() >= 10) {
            but8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(History.this, SearchResult.class);
                    i.putExtra("name", list.get(9).getName());
                    i.putExtra("upc", list.get(9).getUpc());
                    i.putExtra("price", list.get(9).getPrice());
                    i.putExtra("store", list.get(9).getSupplier());
                    i.putExtra("historyFlag", true);
                    startActivity(i);
                }
            });
        }
        but10.setVisibility(View.INVISIBLE);
        but10.setEnabled(false);

        List<Button> buttons = new ArrayList<Button>();

        buttons.add(but1);
        buttons.add(but2);
        buttons.add(but3);
        buttons.add(but4);
        buttons.add(but5);
        buttons.add(but6);
        buttons.add(but7);
        buttons.add(but8);
        buttons.add(but9);
        buttons.add(but10);

        Collections.reverse(list);

        for(int i = 0; i < list.size(); i++){
            names.get(i).setVisibility(View.VISIBLE);
            if(list.get(i).getName().length() >= 15){
                names.get(i).setText(list.get(i).getName().substring(0,15) + "...");
            }else {
                names.get(i).setText(list.get(i).getName());
            }
            buttons.get(i).setVisibility(View.VISIBLE);
            buttons.get(i).setEnabled(true);
        }

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(History.this, MainMenu.class);
        startActivity(i);
    }
}
