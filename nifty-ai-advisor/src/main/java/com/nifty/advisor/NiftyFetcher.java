package com.nifty.advisor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class NiftyFetcher {
    // NSE Nifty 50 Official API URL
    private static final String NSE_URL = "https://www.nseindia.com/api/equity-stockIndices?index=NIFTY%2050";

    public static List<StockData> getLiveNifty50() throws Exception {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(NSE_URL))
                .header("User-Agent", "Mozilla/5.0") // Needed to pretend we are a browser
                .header("Accept", "*/*")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        List<StockData> stocks = new ArrayList<>();
        JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonArray dataArray = jsonObject.getAsJsonArray("data");

        // Loop through all 50 stocks
        for (int i = 1; i < dataArray.size(); i++) { // Index 0 is often the NIFTY index itself
            JsonObject stockJson = dataArray.get(i).getAsJsonObject();
            stocks.add(new StockData(
                stockJson.get("symbol").getAsString(),
                stockJson.get("lastPrice").getAsDouble(),
                stockJson.get("pChange").getAsDouble()
            ));
        }
        return stocks;
    }
}