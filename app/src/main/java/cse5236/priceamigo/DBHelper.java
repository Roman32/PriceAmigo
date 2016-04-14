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

    public void addItem(Item item) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_UPC, item.getUpc());
        values.put(KEY_NAME, item.getName());
        values.put(KEY_SUPPLIER, item.getSupplier());
        values.put(KEY_PRICE, item.getPrice());

        int numberOfResults = getItemCount();

        //Delete last row in DB when there are more than 10 results
        if (numberOfResults > 9) {
            db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE id = (SELECT MIN(id) FROM "+TABLE_NAME+")");
            //db.delete(TABLE_NAME,"id = MIN(id)",null);
        }

        // Inserting Row
        db.insert(TABLE_NAME,null, values);
        db.close(); // Closing database connection
    }

    // Getting single result
    public Item getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
                        KEY_UPC, KEY_NAME, KEY_SUPPLIER, KEY_PRICE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Item item = new Item(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        // return contact
        return item;
    }

    // Getting results count
    public int getItemCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    // Getting All Items
    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<Item>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setID(Integer.parseInt(cursor.getString(0)));
                item.setUpc(cursor.getString(1));
                item.setName(cursor.getString(2));
                item.setSupplier(cursor.getString(3));
                item.setPrice(cursor.getString(4));
                // Adding item to list
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();

        // return contact list
        return itemList;
    }

    public void deleteAll() {
        this.db.delete(TABLE_NAME, null, null);
    }
}
