package br.com.qintess.DAO;

import br.com.qintess.domain.Cidade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by maiquelknechtel on 12/5/20.
 */
@Repository
public interface CidadeDAO extends CrudRepository<Cidade, Integer> {

    @Query("select c from cidade as c where c.estado.id = :id")
    public List<Cidade> findByEstado(@Param("id")Integer id);

    @Query("select c from cidade as c where c.nome = :nome and c.estado.id = :idEstado")
    public List<Cidade> findByNome(@Param("nome")String nome,@Param("idEstado")Integer idEstado);
}
