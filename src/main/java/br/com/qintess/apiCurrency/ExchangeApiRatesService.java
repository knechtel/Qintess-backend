package br.com.qintess.apiCurrency;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by maiquelknechtel on 12/7/20.
 */
public interface ExchangeApiRatesService {
    @GET("/latest")
    public Call<ExchangeRateWrapperObject> getExchangeRates(@Query("base")String base);
}
