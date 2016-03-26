package cse5236.priceamigo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by Roman on 2/26/2016.
 */
//TODO TEST the database
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PriceAmigo.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Results";
    private Context context;
    private SQLiteDatabase db;
    private SQLiteStatement insertStmt;
    private static final String INSERT = "insert into " +
            TABLE_NAME + "(upc, name, supplier, price) values (?, ?, ?, ?)" ;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE" + TABLE_NAME + "(id INTEGER AUTOINCREMENT PRIMARY KEY NOT NULL,upc TEXT NOT NULL,name TEXT,supplier TEXT,price TEXT NOT NULL,timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insert(String upc, String name, String supplier, String price) {
        File f = context.getDatabasePath(DATABASE_NAME);
        long dbSize = f.length();

        //Delete last row in DB when there are more than 10 results
        if(dbSize > 10){
            db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE id = min(id)");
        }

        this.insertStmt.bindString(1, upc);
        this.insertStmt.bindString(2, name);
        this.insertStmt.bindString(3, supplier);
        this.insertStmt.bindString(4, price);

        return this.insertStmt.executeInsert();
    }

    public void deleteAll() {
        this.db.delete(TABLE_NAME, null, null);
    }

    //We can modify this to pull results
    public List<List<String>> getResults() {
        List<List<String>> list = new ArrayList<List<String>>();
        Cursor cursor =
                this.db.query(TABLE_NAME,
                        new String[] { "upc","timestamp"},
                        "upc != '0'",
                        null, null, null, "timestamp desc");

        if (cursor.moveToFirst()) do {
            List<String> result = new ArrayList<String>();
            result.add(cursor.getString(0));
            result.add(cursor.getString(1));
            result.add(cursor.getString(2));
            result.add(cursor.getString(3));
            list.add(result);

        } while (cursor.moveToNext());
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return list;
    }
}
