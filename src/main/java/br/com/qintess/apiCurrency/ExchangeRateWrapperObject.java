package br.com.qintess.apiCurrency;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by maiquelknechtel on 12/7/20.
 */
public class ExchangeRateWrapperObject {
    @SerializedName("base")
    @Expose
    private String base;

    @SerializedName("rates")
    @Expose
    private ExchangeRateObject rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public ExchangeRateObject getRates() {
        return rates;
    }

    public void setRates(ExchangeRateObject rates) {
        this.rates = rates;
    }
}
