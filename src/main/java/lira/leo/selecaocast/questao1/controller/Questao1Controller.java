package lira.leo.selecaocast.questao1.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lira.leo.selecaocast.questao1.service.IQuestao1Service;
import lira.leo.selecaocast.questao1.service.util.FileSide;

/**
 * Classe responsável por prover os endpoints solictados pela questão 01 do processo de seleção da Cast  
 * 
 * @author leonardo.lira
 */
@RestController
@RequestMapping("/v1")
public class Questao1Controller {

	@Autowired
	private IQuestao1Service questao1Service;

	@PostMapping("/diff/{id}/left")
	public ResponseEntity<String> leftData(@PathVariable(name = "id") String id,
			@RequestBody(required = false) String data) throws IOException {

		boolean inserted = questao1Service.saveDataFile(data, id, FileSide.LEFT);

		return ResponseEntity.ok(String.format("Left data id = %s %s!", id, inserted ? "inserted" : "updated"));
	}

	@PostMapping("/diff/{id}/right")
	public ResponseEntity<String> rightData(@PathVariable(name = "id") String id, @RequestBody String data)
			throws IOException {

		boolean inserted = questao1Service.saveDataFile(data, id, FileSide.RIGHT);

		return ResponseEntity.ok(String.format("Right data id = %s %s!", id, inserted ? "inserted" : "updated"));
	}

	@GetMapping("/diff/{id}")
	public ResponseEntity<Object> compareData(@PathVariable(name = "id") String id) throws IOException {

		return ResponseEntity.ok(questao1Service.compareDataFiles(id));
	}
}
