package lira.leo.selecaocast.questao3.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lira.leo.selecaocast.questao3.domain.Person;

/**
 * Classe responsável pelo acesso os dados da pessoa.
 * 
 * 
 * @author leonardo.lira
 *
 */
@Repository
public interface IPersonDao  extends JpaRepository<Person, Serializable>{
	/**
	 * Pesquisa uma pessoa no banco pelo ID
	 * 
	 * @param id
	 * @return
	 */
	Person findById(long id);
}
