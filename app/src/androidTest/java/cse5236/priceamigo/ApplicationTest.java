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

    public void testCalculateZoomLevel(){
        assertEquals(SearchResult.calculateZoomLevel(500, 24902), 1);
    }

    public void testDB(){
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


}