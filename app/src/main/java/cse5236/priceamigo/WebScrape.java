package cse5236.priceamigo;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Roman on 3/23/2016.
 */
public class WebScrape extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] params) {
        return null;
    }

    public String getItemName(String item){
        String lookURL = "http://www.upcitemdb.com/upc/"+item;
        String nameS = "Item not found";
        Document doc;
        Element body;
        try {
            doc = Jsoup.connect(lookURL).get();
            body = doc.select("body").first();
            Element des = body.select("div.content-box-content").first();
            Element name = des.select("span.destxt").first();
            nameS = name.text();
        }catch(Exception e){
            nameS = "Item not found";
        }
        return nameS;
    }

    public String getNameFromWally(String item){
        String walmartURL = "http://www.walmart.com/search/?query="+item;
        String nameS = "Item not found";
        try{
            Document docWally = Jsoup.connect(walmartURL).get();
            Element body = docWally.select("body").first();
            Element name = docWally.select("div.product-details").select("p").first();
            nameS = name.text();
        }catch(Exception e){
            e.printStackTrace();
            nameS = "Item not found";
        }
        return nameS;

    }

    public String scrapeBB(String item){
        String bbURL = "http://www.bestbuy.com/site/searchpage.jsp?st="+ item+"&_dyncharset=UTF-8&id=pcat17071&type=page&sc=Global&cp=1&nrp=&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&keys=keys";
        String priceS = "No price found!";
        String nameS = "Item not found";
        try {
            Document docBB = Jsoup.connect(bbURL).get();
            Element price = docBB.select("div.medium-item-price").first();
            priceS = price.text();
        }catch (Exception e){
            e.printStackTrace();
            priceS = "price not FOUND!";
        }
        return priceS;
    }

    public String scrapeWallyWorld(String item){
        String walmartURL = "http://www.walmart.com/search/?query="+item;
        String priceS = "No price found!";
        //String nameS = "Item not found";
        try{
            Document docWally = Jsoup.connect(walmartURL).get();
            Element body = docWally.select("body").first();
            Element prices = body.select("div.product-price").first();
            priceS = prices.text();
        }catch(Exception e){
            e.printStackTrace();
        }
        return priceS;
    }

    public String scrapeTarget(String item){
        String targetURL = "http://www.target.com/s/"+item;
        String priceS = "No price found!";
        try {
            Document docTar = Jsoup.connect(targetURL).get();
            Element body = docTar.select("body.components-test newLayout tsa dlp").first();
            Element product = body.select("p.price.price-label").first();
        }catch (Exception e){
            e.printStackTrace();
            priceS = "price not FOUND!";
        }
        return priceS;

    }

    public String scrapeAmazon(String item){
        String amazonURL = "http://www.amazon.com/s/ref=nb_sb_noss_1?url=search-alias%3Daps&field-keywords="+item;
        String priceS = "No price found!";
        try {
            Document docBB = Jsoup.connect(amazonURL).get();
            Element price = docBB.select("div.medium-item-price").first();
            priceS = price.text();
        }catch (Exception e){
            e.printStackTrace();
            priceS = "price not FOUND!";
        }
        return priceS;

    }
}
