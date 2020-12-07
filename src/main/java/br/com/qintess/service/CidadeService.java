package br.com.qintess.service;

import br.com.qintess.DAO.CidadeDAO;
import br.com.qintess.domain.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by maiquelknechtel on 12/5/20.
 */
@Service
public class CidadeService {
    @Autowired
    private CidadeDAO cidadeDAO;

    public Cidade save(Cidade cidade){
        return cidadeDAO.save(cidade);
    }

    public List<Cidade> findAll(){
        return (List<Cidade>) cidadeDAO.findAll();
    }

    public List<Cidade> findByEstado(Integer id){ return (List<Cidade>) cidadeDAO.findByEstado(id);}

    public void delete(Cidade cidade){ cidadeDAO.delete(cidade); }

    public List<Cidade> findByNome(Cidade cidade){return cidadeDAO.findByNome(cidade.getNome(),cidade.getEstado().getId());}
}
