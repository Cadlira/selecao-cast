package lira.leo.selecaocast.questao1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import lira.leo.selecaocast.questao1.service.IQuestao1Service;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Questao1ApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private IQuestao1Service service;

	@Test
	public void sendLeftFile_thenInserted_status200() throws Exception {
		File file1 = new File(getClass().getClassLoader().getResource("file1.txt").toURI());
		String encFile1 = encodeFile(file1.getAbsolutePath());

		MvcResult result = mvc.perform(post("/v1/diff/web_teste01/left").contentType(MediaType.APPLICATION_JSON).content(encFile1))
				.andDo(print())
				.andExpect(status().isOk()).andDo(print()).andReturn();
		
		assertThat(result.getResponse().getContentAsString()).contains("inserted");

		service.deleteDataFiles("web_teste01");
	}
	
	@Test
	public void sendLeftFile_thenUpdated_status200() throws Exception {
		File file1 = new File(getClass().getClassLoader().getResource("file1.txt").toURI());
		String encFile1 = encodeFile(file1.getAbsolutePath());

		mvc.perform(post("/v1/diff/web_teste02/left").contentType(MediaType.APPLICATION_JSON).content(encFile1));
		
		MvcResult result = mvc.perform(post("/v1/diff/web_teste02/left").contentType(MediaType.APPLICATION_JSON).content(encFile1))
				.andExpect(status().isOk()).andDo(print()).andReturn();
		
		assertThat(result.getResponse().getContentAsString()).contains("updated");

		service.deleteDataFiles("web_teste02");
	}
	
	@Test
	public void sendRightFile_thenInserted_status200() throws Exception {
		File file1 = new File(getClass().getClassLoader().getResource("file1.txt").toURI());
		String encFile1 = encodeFile(file1.getAbsolutePath());

		MvcResult result = mvc.perform(post("/v1/diff/web_teste03/right").contentType(MediaType.APPLICATION_JSON).content(encFile1))
				.andExpect(status().isOk()).andDo(print()).andReturn();
		
		assertThat(result.getResponse().getContentAsString()).contains("inserted");

		service.deleteDataFiles("web_teste03");
	}
	
	@Test
	public void sendRightFile_thenUpdated_status200() throws Exception {
		File file1 = new File(getClass().getClassLoader().getResource("file1.txt").toURI());
		String encFile1 = encodeFile(file1.getAbsolutePath());

		mvc.perform(post("/v1/diff/web_teste04/right").contentType(MediaType.APPLICATION_JSON).content(encFile1));
		
		MvcResult result = mvc.perform(post("/v1/diff/web_teste04/right").contentType(MediaType.APPLICATION_JSON).content(encFile1))
				.andExpect(status().isOk()).andDo(print()).andReturn();
		
		assertThat(result.getResponse().getContentAsString()).contains("updated");

		service.deleteDataFiles("web_teste04");
	}


	@Test
	public void sendLeftFile_thenFileNotInformed_status500() throws Exception {

		MvcResult result = mvc.perform(post("/v1/diff/web_teste05/left").contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isExpectationFailed()).andDo(print()).andReturn();
		
		assertThat(result.getResponse().getContentAsString()).contains(":417").contains("Base64");

		service.deleteDataFiles("web_teste05");
	}
	
	private String encodeFile(String filePath) {
		String base64Image = "";
		File file = new File(filePath);
		try (FileInputStream is = new FileInputStream(file)) {
			byte fileDate[] = new byte[(int) file.length()];
			is.read(fileDate);
			base64Image = Base64.getEncoder().encodeToString(fileDate);
		} catch (FileNotFoundException e) {
			System.out.println(String.format("File \"%s\" not found", filePath) + e);
		} catch (IOException ioe) {
			System.out.println(String.format("Exception while reading the file \"%s\"", filePath) + ioe);
		}
		return base64Image;
	}

}
