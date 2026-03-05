package com.nifty.advisor;

public class StockData {
    private String symbol;
    private double lastPrice;
    private double pChange; // Percentage change

    public StockData(String symbol, double lastPrice, double pChange) {
        this.symbol = symbol;
        this.lastPrice = lastPrice;
        this.pChange = pChange;
    }

    @Override
    public String toString() {
        return String.format("%-10s | Price: ₹%-8.2f | Change: %s%.2f%%", 
                             symbol, lastPrice, (pChange >= 0 ? "+" : ""), pChange);
    }
}