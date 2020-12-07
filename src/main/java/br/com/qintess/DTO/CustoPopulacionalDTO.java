package br.com.qintess.DTO;



/**
 * Created by maiquelknechtel on 12/7/20.
 */
public class CustoPopulacionalDTO {

    private Integer idEstado;
    private Double custo;

    public CustoPopulacionalDTO(Integer idEstado,Double custo){
        this.idEstado=idEstado;
        this.custo=custo;
    }
    public CustoPopulacionalDTO(){

    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

}
