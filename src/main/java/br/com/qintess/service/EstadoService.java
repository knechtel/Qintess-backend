package br.com.qintess.service;

import br.com.qintess.DAO.EstadoDAO;
import br.com.qintess.domain.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by maiquelknechtel on 12/5/20.
 */
@Service
public class EstadoService {
    @Autowired
    private EstadoDAO estadoDAO;

    public Estado save(Estado estado){
        return estadoDAO.save(estado);
    }

    public Estado findByID(Integer id){
        return estadoDAO.findById(id).orElse(null);
    }
}
