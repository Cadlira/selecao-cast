package lira.leo.selecaocast.questao3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lira.leo.selecaocast.questao3.domain.Person;
import lira.leo.selecaocast.questao3.service.IPersonService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@AutoConfigureMockMvc
public class Questao3ApplicationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private IPersonService service;

	@After
	public void tearDown() {
		List<Person> pessoas = service.findAll();
		if (!CollectionUtils.isEmpty(pessoas)) {
			for (Person person : pessoas) {
				service.delete(person.getId());
			}
		}
	}

	@Test
	public void save_statusOkTest() throws Exception {
		Person p1 = new Person(-1, "Teste Save", "1111", "2222", "RUA", "SN", "Bairro", "Cidade", "Estado");

		MvcResult result = mvc
				.perform(post("/rest/pessoa/save").contentType(MediaType.APPLICATION_JSON).content(p1.toString()))
				.andDo(print()).andExpect(status().isOk()).andReturn();

		assertThat(result.getResponse().getContentAsString()).isGreaterThan("0");
	}

	@Test
	public void delete_statusOkTest() throws Exception {
		Person p1 = new Person(-1, "Teste Delete", "1111", "2222", "RUA", "SN", "Bairro", "Cidade", "Estado");

		MvcResult result = mvc
				.perform(post("/rest/pessoa/save").contentType(MediaType.APPLICATION_JSON).content(p1.toString()))
				.andExpect(status().isOk()).andReturn();

		mvc.perform(get("/rest/pessoa/" + result.getResponse().getContentAsString())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		mvc.perform(delete("/rest/pessoa/remove/" + result.getResponse().getContentAsString())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		mvc.perform(get("/rest/pessoa/" + result.getResponse().getContentAsString())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}

	@Test
	public void findById_statusOkTest() throws Exception {
		Person p1 = new Person(-1, "Teste FindById 200", "1111", "2222", "RUA", "SN", "Bairro", "Cidade", "Estado");

		MvcResult result = mvc
				.perform(post("/rest/pessoa/save").contentType(MediaType.APPLICATION_JSON).content(p1.toString()))
				.andExpect(status().isOk()).andReturn();

		MvcResult result2 = mvc.perform(get("/rest/pessoa/" + result.getResponse().getContentAsString())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

		assertThat(result2.getResponse().getContentAsString()).contains("Teste FindById 200");
	}

	@Test
	public void findById_statusNoContentTest() throws Exception {

		mvc.perform(get("/rest/pessoa/99999999999").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());

	}

	@Test
	public void findAll_statusokTest() throws Exception {
		Person p1 = new Person(-1, "Teste findAll 1", "1111", "2222", "RUA", "SN", "Bairro", "Cidade", "Estado");
		Person p2 = new Person(-1, "Teste findAll 2", "1111", "2222", "RUA", "SN", "Bairro", "Cidade", "Estado");
		Person p3 = new Person(-1, "Teste findAll 3", "1111", "2222", "RUA", "SN", "Bairro", "Cidade", "Estado");

		mvc.perform(post("/rest/pessoa/save").contentType(MediaType.APPLICATION_JSON).content(p1.toString()))
				.andExpect(status().isOk());
		mvc.perform(post("/rest/pessoa/save").contentType(MediaType.APPLICATION_JSON).content(p2.toString()))
				.andExpect(status().isOk());
		mvc.perform(post("/rest/pessoa/save").contentType(MediaType.APPLICATION_JSON).content(p3.toString()))
				.andExpect(status().isOk());
		
		MvcResult result = mvc.perform(get("/rest/pessoas")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andReturn();
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<Person> pessoas = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Person>>(){});
		
		assertThat(pessoas.size()).isEqualTo(3);
		
	}
}
