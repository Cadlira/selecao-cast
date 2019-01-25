package lira.leo.selecaocast.questao3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lira.leo.selecaocast.questao3.dao.IPersonDao;
import lira.leo.selecaocast.questao3.domain.Person;

/**
 * Classe responsável pela regra de negócio do CRUD de pessoa
 * 
 * @author leonardo.lira
 *
 */
@Service
@Transactional
public class PersonService implements IPersonService {

	@Autowired
	private IPersonDao dao;
	
	@Override
	public long save(Person person) {		
		return dao.save(person).getId();
	}

	@Override
	public Person findById(long id) {
		return dao.findById(id);
	}

	@Override
	public void delete(long id) {
		dao.deleteById(id);
	}

	@Override
	public List<Person> findAll() {
		return dao.findAll();
	}

}
