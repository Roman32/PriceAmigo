package cse5236.priceamigo;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.UiThreadTest;
import android.util.Log;

import java.util.List;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testInternetIsAvailable() throws Exception {
        assertEquals(MainMenu.isInternetAvailable(), true);
    }

    //Testing max zoom out
    public void testCalculateZoomLevel(){
        assertEquals(SearchResult.calculateZoomLevel(500, 24902), 1);
    }

    //Testing adding item
    public void testDB1(){
        DBHelper d = new DBHelper(this.getContext());
        d.deleteAll();
        Item i = new Item(1, "test", "101", "walmart", "1.00");
        d.addItem(i);
        assertEquals(d.getItemCount(), 1);
        List<Item> results = d.getAllItems();
        assertEquals(results.size(), 1);
        assertEquals(results.get(0).getID(), i.getID());
        assertEquals(results.get(0).getName().equals(i.getName()),true);
        assertEquals(results.get(0).getUpc().equals(i.getUpc()), true);
        assertEquals(results.get(0).getSupplier().equals(i.getSupplier()), true);
        assertEquals(results.get(0).getPrice().equals(i.getPrice()), true);

    }

    //Testing that ID correctly increments
    public void testDB2(){
        DBHelper d = new DBHelper(this.getContext());
        d.deleteAll();
        Item i = new Item("test1", "101", "walmart", "1.00");
        Item j = new Item("test2", "1023", "besybuy", "1.00");
        d.addItem(i);
        d.addItem(j);
        assertEquals(d.getItemCount(), 2);
        List<Item> results = d.getAllItems();
        assertEquals(results.size(), 2);
        assertEquals(results.get(0).getID(), 1);
        assertEquals(results.get(0).getName().equals(i.getName()), true);
        assertEquals(results.get(0).getUpc().equals(i.getUpc()), true);
        assertEquals(results.get(0).getSupplier().equals(i.getSupplier()), true);
        assertEquals(results.get(0).getPrice().equals(i.getPrice()), true);
        assertEquals(results.get(1).getID(), 2);
        assertEquals(results.get(1).getName().equals(j.getName()),true);
        assertEquals(results.get(1).getUpc().equals(j.getUpc()), true);
        assertEquals(results.get(1).getSupplier().equals(j.getSupplier()), true);
        assertEquals(results.get(1).getPrice().equals(j.getPrice()), true);
    }

    //Testing case where 11 items are added and that only 10 are kept
    public void testDB3(){
        DBHelper d = new DBHelper(this.getContext());
        d.deleteAll();
        Item i = new Item("test1", "101", "walmart", "1.00");
        Item j = new Item("test2", "1023", "besybuy", "1.00");
        Item k = new Item("test3", "101", "walmart", "1.00");
        Item l = new Item("test4", "1023", "besybuy", "1.00");
        Item n = new Item("test5", "101", "walmart", "1.00");
        Item m = new Item("test6", "1023", "besybuy", "1.00");
        Item o = new Item("test7", "101", "walmart", "1.00");
        Item p = new Item("test8", "1023", "besybuy", "1.00");
        Item q = new Item("test9", "101", "walmart", "1.00");
        Item r = new Item("test10", "1023", "besybuy", "1.00");
        Item s = new Item("test11", "101", "walmart", "1.00");
        d.addItem(i);
        d.addItem(j);
        d.addItem(k);
        d.addItem(l);
        d.addItem(n);
        d.addItem(m);
        d.addItem(o);
        d.addItem(p);
        d.addItem(q);
        d.addItem(r);
        d.addItem(s);
        assertEquals(d.getItemCount(), 10);
        List<Item> results = d.getAllItems();
        assertEquals(results.size(), 10);
    }

    //Testing case where 15 items are added and that only 10 are kept
    public void testDB4(){
        DBHelper d = new DBHelper(this.getContext());
        d.deleteAll();
        Item i = new Item("test1", "101", "walmart", "1.00");
        Item j = new Item("test2", "1023", "besybuy", "1.00");
        Item k = new Item("test3", "101", "walmart", "1.00");
        Item l = new Item("test4", "1023", "besybuy", "1.00");
        Item n = new Item("test5", "101", "walmart", "1.00");
        Item m = new Item("test6", "1023", "besybuy", "1.00");
        Item o = new Item("test7", "101", "walmart", "1.00");
        Item p = new Item("test8", "1023", "besybuy", "1.00");
        Item q = new Item("test9", "101", "walmart", "1.00");
        Item r = new Item("test10", "1023", "besybuy", "1.00");
        Item s = new Item("test11", "101", "walmart", "1.00");
        Item t = new Item("test12", "101", "walmart", "1.00");
        Item u = new Item("test13", "1023", "besybuy", "1.00");
        Item v = new Item("test14", "101", "walmart", "1.00");
        Item w = new Item("test15", "1023", "besybuy", "1.00");
        d.addItem(i);
        d.addItem(j);
        d.addItem(k);
        d.addItem(l);
        d.addItem(n);
        d.addItem(m);
        d.addItem(o);
        d.addItem(p);
        d.addItem(q);
        d.addItem(r);
        d.addItem(s);
        d.addItem(t);
        d.addItem(u);
        d.addItem(v);
        d.addItem(w);
        assertEquals(d.getItemCount(), 10);
        List<Item> results = d.getAllItems();
        assertEquals(results.size(), 10);
    }

    //Testing that last item in DB is j
    public void testDB5(){
        DBHelper d = new DBHelper(this.getContext());
        d.deleteAll();
        Item i = new Item("test1", "101", "walmart", "1.00");
        Item j = new Item("test2", "1023", "besybuy", "1.00");
        Item k = new Item("test3", "101", "walmart", "1.00");
        Item l = new Item("test4", "1023", "besybuy", "1.00");
        Item n = new Item("test5", "101", "walmart", "1.00");
        Item m = new Item("test6", "1023", "besybuy", "1.00");
        Item o = new Item("test7", "101", "walmart", "1.00");
        Item p = new Item("test8", "1023", "besybuy", "1.00");
        Item q = new Item("test9", "101", "walmart", "1.00");
        Item r = new Item("test10", "1023", "besybuy", "1.00");
        Item s = new Item("test11", "101", "walmart", "1.00");
        d.addItem(i);
        d.addItem(j);
        d.addItem(k);
        d.addItem(l);
        d.addItem(n);
        d.addItem(m);
        d.addItem(o);
        d.addItem(p);
        d.addItem(q);
        d.addItem(r);
        d.addItem(s);
        assertEquals(d.getItemCount(), 10);
        List<Item> results = d.getAllItems();
        assertEquals(results.size(), 10);
        assertEquals(results.get(0).getID(), 2);
        assertEquals(results.get(0).getName().equals(j.getName()), true);
        assertEquals(results.get(0).getUpc().equals(j.getUpc()), true);
        assertEquals(results.get(0).getSupplier().equals(j.getSupplier()), true);
        assertEquals(results.get(0).getPrice().equals(j.getPrice()), true);
    }

    //Testing clearing DB
    public void testDB6(){
        DBHelper d = new DBHelper(this.getContext());
        d.deleteAll();
        List<Item> results = d.getAllItems();
        assertEquals(results.size(), 0);
    }


}