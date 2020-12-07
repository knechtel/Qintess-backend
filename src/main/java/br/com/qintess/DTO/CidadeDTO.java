package br.com.qintess.DTO;

import br.com.qintess.domain.Cidade;

/**
 * Created by maiquelknechtel on 12/5/20.
 */
public class CidadeDTO {
    private Integer id;
    private String nome;
    private Integer idEstado;
    private Integer populacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Integer populacao) {
        this.populacao = populacao;
    }

    public Cidade toCidade(CidadeDTO cidadeDTO){
        Cidade c =  new Cidade();
        c.setId(cidadeDTO.getId());
        c.setNome(cidadeDTO.getNome());
        c.setPopulacao(cidadeDTO.getPopulacao());
      return c;
    }

    public CidadeDTO tocidadeDTO(Cidade cidade){
        CidadeDTO c = new CidadeDTO();
        c.setId(cidade.getId());
        c.setIdEstado(cidade.getEstado().getId());
        c.setNome(cidade.getNome());
        c.setPopulacao(cidade.getPopulacao());
        return c;
    }
}
