package com.nifty.advisor;

import org.ta4j.core.BaseBarSeries;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;
import java.util.Properties;

public class App {
    public static void main(String[] args) {
    System.out.println("--- FETCHING LIVE NIFTY 50 DATA ---");
    try {
        List<StockData> niftyList = NiftyFetcher.getLiveNifty50();
        
        System.out.println("SYMBOL     | PRICE        | CHANGE");
        System.out.println("-----------------------------------");
        
        for (StockData stock : niftyList) {
            System.out.println(stock);
        }
        
    } catch (Exception e) {
        System.err.println("Error fetching data: " + e.getMessage());
        System.out.println("Tip: Check your internet connection or NSE server status.");
    }
}
}