package br.com.qintess.DAO;

import br.com.qintess.domain.Estado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by maiquelknechtel on 12/5/20.
 */
@Repository
public interface EstadoDAO extends CrudRepository<Estado, Integer> {
}
