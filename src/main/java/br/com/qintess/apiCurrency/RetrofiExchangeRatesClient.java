package br.com.qintess.apiCurrency;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by maiquelknechtel on 12/7/20.
 */
public class RetrofiExchangeRatesClient {
    public static Retrofit getClient(){
        return new Retrofit.Builder()
                .baseUrl("https://api.exchangeratesapi.io/latest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
