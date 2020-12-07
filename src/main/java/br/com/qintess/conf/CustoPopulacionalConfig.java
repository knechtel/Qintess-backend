package br.com.qintess.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by maiquelknechtel on 12/7/20.
 */

@Configuration
public class CustoPopulacionalConfig {
    @Value(value = "${valor.corte}")
    private Integer valorDeCorte;
    @Value(value = "${valor.pessoa}")
    private Double valorPessoa;
    @Value(value = "${valor.desconto}")
    private Double valorDesconto;
    @Bean
    public Integer getValorDeCorte(){
        return valorDeCorte;
    }
    @Bean
    public Double getValorPessoa(){
        return valorPessoa;
    }
    @Bean
    public Double getValorDesconto(){
        return valorDesconto;
    }
}
