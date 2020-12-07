package br.com.qintess.DTO;

/**
 * Created by maiquelknechtel on 12/7/20.
 */
public class CotacaoDolarDTO {
    private Double real;
    public CotacaoDolarDTO(Double real){
        this.real=real;
    }
    public Double getReal() {
        return real;
    }
    public void setReal(Double real) {
        this.real = real;
    }
}
