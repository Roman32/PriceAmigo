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


        if(list.size() >= 1) {
            Button but1 = (Button)findViewById(R.id.button1);
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
            but1.setVisibility(View.VISIBLE);
            but1.setEnabled(true);
        }


        if(list.size() >= 2) {
            Button but2 = (Button)findViewById(R.id.button2);
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
            but2.setVisibility(View.VISIBLE);
            but2.setEnabled(true);
        }



        if(list.size() >= 3) {
            Button but3 = (Button)findViewById(R.id.button3);
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
            but3.setVisibility(View.VISIBLE);
            but3.setEnabled(true);
        }



        if(list.size() >= 4) {
            Button but4 = (Button)findViewById(R.id.button4);
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
            but4.setVisibility(View.VISIBLE);
            but4.setEnabled(true);
        }



        if(list.size() >= 5) {
            Button but5 = (Button)findViewById(R.id.button5);
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
            but5.setVisibility(View.VISIBLE);
            but5.setEnabled(true);
        }



        if(list.size() >= 6) {
            Button but6 = (Button)findViewById(R.id.button6);
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
            but6.setVisibility(View.VISIBLE);
            but6.setEnabled(true);
        }



        if(list.size() >= 7) {
            Button but7 = (Button)findViewById(R.id.button7);
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
            but7.setVisibility(View.VISIBLE);
            but7.setEnabled(true);
        }



        if(list.size() >= 8) {
            Button but8 = (Button)findViewById(R.id.button8);
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
            but8.setVisibility(View.VISIBLE);
            but8.setEnabled(true);
        }



        if(list.size() >= 9) {
            Button but9 = (Button)findViewById(R.id.button9);
            but9.setOnClickListener(new View.OnClickListener() {
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
            but9.setVisibility(View.VISIBLE);
            but9.setEnabled(true);
        }



        if(list.size() >= 10) {
            Button but10 = (Button)findViewById(R.id.button10);
            but10.setOnClickListener(new View.OnClickListener() {
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
            but10.setVisibility(View.VISIBLE);
            but10.setEnabled(true);
        }

        Collections.reverse(list);

        for(int i = 0; i < list.size(); i++){
            names.get(i).setVisibility(View.VISIBLE);
            if(list.get(i).getName().length() >= 15){
                names.get(i).setText(list.get(i).getName().substring(0,15) + "...");
            }else {
                names.get(i).setText(list.get(i).getName());
            }
        }

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(History.this, MainMenu.class);
        startActivity(i);
    }
}
