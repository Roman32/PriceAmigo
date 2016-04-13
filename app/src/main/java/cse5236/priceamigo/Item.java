package cse5236.priceamigo;

/**
 * Created by Roman on 3/27/2016.
 */
public class Item {
    //Instance variables
    int id;
    String upc;
    String itemName;
    String supplier;
    String price;

    //Default Constructor
    public Item() {
        this.upc = "";
        this.itemName = "";
        this.supplier = "";
        this.price = "";
    }

    //Normal Constructor
    public Item(int id, String upc, String name, String supplier, String price) {
        this.id = id;
        this.upc = upc;
        this.itemName = name;
        this.supplier = supplier;
        this.price = price;
    }

    public Item(String upc, String name, String supplier, String price) {
        this.upc = upc;
        this.itemName = name;
        this.supplier = supplier;
        this.price = price;
    }

    /**
     * Probably won't need the setters but just in case.
     */

    // setting id
    public void setID(int id) {
        this.id = id;
    }

    // returns the ID
    public int getID() {
        return this.id;
    }

    //set upc
    public void setUpc(String upc) {
        this.upc = upc;
    }

    //returns the upc
    public String getUpc() {
        return this.upc;
    }

    //Sets name
    public void setName(String name) {
        this.itemName = name;
    }

    //returns name
    public String getName() {
        return this.itemName;
    }

    //sets the supplier
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    //returns the supplier
    public String getSupplier() {
        return this.supplier;
    }

    //sets the price
    public void setPrice(String price) {
        this.price = price;
    }

    //returns the price
    public String getPrice() {
        return this.price;
    }
}