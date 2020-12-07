package br.com.qintess.controller;

import br.com.qintess.DTO.CotacaoDolarDTO;
import br.com.qintess.apiCurrency.ExchangeApiRatesService;
import br.com.qintess.apiCurrency.ExchangeRateWrapperObject;
import br.com.qintess.apiCurrency.RetrofiExchangeRatesClient;
import br.com.qintess.util.Session;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

/**
 * Created by maiquelknechtel on 12/7/20.
 */
@RestController
@RequestMapping("/api/moeda")
public class CotacaoController {
    @RequestMapping(value = "/getMoeda", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public CotacaoDolarDTO atualizaCotacao() {
        ExchangeApiRatesService service;
        service = RetrofiExchangeRatesClient.getClient().create(ExchangeApiRatesService.class);
        try {
            Response<ExchangeRateWrapperObject> response = service.getExchangeRates("USD").execute();
            Double dolarAmericano = response.body().getRates().getRateFor("BRL");
            Session.setReal(dolarAmericano);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/getCotacao", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public CotacaoDolarDTO getCotacao(){
        return new CotacaoDolarDTO(Session.getReal());
    }
}
