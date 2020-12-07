package br.com.qintess.apiCurrency;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by maiquelknechtel on 12/7/20.
 */
public class ExchangeRateObject {
    @SerializedName("EUR")
    @Expose
    private double EUR;
    @SerializedName("USD")
    @Expose
    private double USD;
    @SerializedName("BRL")
    @Expose
    private double BRL;

    public double getRateFor(String currency){
        if(currency.equals("EUR")) return EUR;
        if(currency.equals("USD")) return USD;
        if(currency.equals("BRL")) return BRL;
        return 1;
    }
}
