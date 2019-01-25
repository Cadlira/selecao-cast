package lira.leo.selecaocast.questao3;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.CollectionUtils;

import lira.leo.selecaocast.questao3.domain.Person;
import lira.leo.selecaocast.questao3.service.IPersonService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureMockMvc
public class Questao3ApplicationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private IPersonService service;
	
	@After
	public void tearDown(){
		List<Person> pessoas = service.findAll();
		if (!CollectionUtils.isEmpty(pessoas)) {
			for (Person person : pessoas) {
				service.delete(person.getId());
			}
		}
	}
	
	@Test
	public void save_(){
		
	}
}
