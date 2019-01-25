package lira.leo.selecaocast.questao3.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lira.leo.selecaocast.questao3.domain.Person;
import lira.leo.selecaocast.questao3.service.IPersonService;

@RestController
@RequestMapping("/rest")
public class PersonController {

	@Autowired
	private IPersonService service;
	
	@PostMapping(value={"/pessoa/save","/pessoa/save/"})
	public ResponseEntity<Long> save(@RequestBody @Valid Person person, BindingResult result,
			HttpServletResponse response){
		
		return ResponseEntity.ok(this.service.save(person));
	}
	
	@GetMapping("/pessoa/{id}")
	public ResponseEntity<Person> findById(@PathVariable("id") long id){
		Person ret = this.service.findById(id);
		if (Objects.isNull(ret)) {
			return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(ret);
		}
	}
	
	@GetMapping("/pessoas")
	public ResponseEntity<List<Person>> findAll(){
		List<Person> ret = this.service.findAll();
		if (CollectionUtils.isEmpty(ret)) {
			return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(ret);
		}
	}
	
	@DeleteMapping("/pessoa/remove/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id){
		this.service.delete(id);
		return ResponseEntity.ok("");
	}
	
}
