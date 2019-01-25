package lira.leo.selecaocast.questao3.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lira.leo.selecaocast.questao3.domain.Person;

@Repository
public interface IPersonDao  extends JpaRepository<Person, Serializable>{
	Person findById(long id);
}
