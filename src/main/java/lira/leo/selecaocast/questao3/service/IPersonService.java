package lira.leo.selecaocast.questao3.service;

import java.util.List;

import lira.leo.selecaocast.questao3.domain.Person;

/**
 * @author leonardo.lira
 *
 */
public interface IPersonService {

	/**
	 * Método responsável por inserir / autalizar uma pessoa
	 * @param person -  pessoa a ser inserida / atualizada
	 * @return Retorna o id da pessoa inserida / atualizada
	 */
	long save(Person person);
	
	/**
	 * Pesquisa uma pessoa pelo id
	 * 
	 * @param id - identificador unico da pessoa
	 * @return Pessoa correspondente ao id, caso encontrado. Ou null, caso não haja correspondência ao id
	 */
	Person findById(long id);
	
	/**
	 * Deleta uma pessoa do banco de dados
	 * @param id - Id da pessoa a ser deletada
	 */
	void delete(long id);
	
	/**
	 * Lista todas as pessoas cadastradas no banco de dados
	 * 
	 * @return
	 */
	List<Person> findAll();
}
