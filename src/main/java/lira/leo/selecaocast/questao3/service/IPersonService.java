package lira.leo.selecaocast.questao3.service;

import java.util.List;

import lira.leo.selecaocast.questao3.domain.Person;

public interface IPersonService {

	long save(Person person);
	
	Person findById(long id);
	
	void delete(long id);
	
	List<Person> findAll();
}
