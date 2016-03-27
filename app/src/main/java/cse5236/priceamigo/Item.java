package cse5236.priceamigo;

/**
 * Created by Roman on 3/27/2016.
 */
public class Item {
    //Instance variables
    String itemName;
    String price;
    String upc;

    //Default Constructor
    public Item(){

    }

    //Normal Constructor
    public Item(String name,String price,String upc){
        this.itemName = name;
        this.price = price;
        this.upc = upc;
    }

    /**
     * Probably won't need the setters but just in case.
     */

    //Sets name
    public void setName(String name){
        this.itemName = name;
    }
    //returns name
    public String getName(){
        return this.itemName;
    }
    //sets the price
    public void setPrice(String price){
        this.price = price;
    }
    //returns the price
    public String getPrice(){
        return this.price;
    }
    //set upc
    public void setUpc(String upc){
        this.upc = upc;
    }

    public String getUpc(){
        return this.upc;
    }
}
