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
    private static final String KEY_ID = "id";
    private static final String KEY_UPC = "upc";
    private static final String KEY_NAME = "name";
    private static final String KEY_SUPPLIER = "supplier";
    private static final String KEY_PRICE = "price";

    private Context context;
    private SQLiteDatabase db;
    private SQLiteStatement insertStmt;
    private static final String INSERT = "insert into " +
            TABLE_NAME + "(upc, name, supplier, price) values (?, ?, ?, ?)" ;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
        this.db = super.getReadableDatabase();
    }

    //Creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RESULTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_UPC + " TEXT,"
                + KEY_NAME + " TEXT," + KEY_SUPPLIER + " TEXT," + KEY_PRICE + " TEXT," + "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(CREATE_RESULTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drops older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //Creates table again
        onCreate(db);
    }

    public void addResult(String upc, String name, String supplier, String price) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("upc", upc);
        values.put("name", name);
        values.put("supplier", supplier);
        values.put("price", price);

        int numberOfResults = getResultsCount();

        //Delete last row in DB when there are more than 10 results
        if(numberOfResults > 10){
            //db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE id = min(id)");
            db.delete(TABLE_NAME,"id = MIN(id)",null);
        }

        // Inserting Row
        db.insert(TABLE_NAME,null, values);
        db.close(); // Closing database connection
    }

    // Getting single result
    public List<String> getResult(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
                        KEY_UPC, KEY_NAME, KEY_SUPPLIER, KEY_PRICE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        List<String> result = new ArrayList<String>();
        result.add(cursor.getString(0));
        result.add(cursor.getString(1));
        result.add(cursor.getString(2));
        result.add(cursor.getString(3));
        result.add(cursor.getString(4));

        // return result
        return result;
    }

    // Getting results count
    public int getResultsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
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
