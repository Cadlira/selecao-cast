package lira.leo.selecaocast.questao3.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe de dominio referente a pessoa
 * 
 * @author leonardo.lira
 *
 */
@Entity
@Table(schema="cast", name="person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Size(max = 255)
	@Column(nullable = false)
	private String name;
	
	@Size(max = 30)
	@Column
	private String phone;
	
	@Size(max = 30)
	@Column
	private String cellphone;
	
	@Size(max = 255)
	@Column
	private String street;
	
	@Size(max = 10)
	@Column
	private String number;
	
	@Size(max = 255)
	@Column
	private String neighborhood;
	
	@Size(max = 255)
	@Column
	private String city;
	
	@Size(max = 255)
	@Column
	private String state;

	public Person(){}
	
	public Person(long id, String name, String phone, String cellphone,
			String street, String number, String neighborhood,
			String city, String state) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.cellphone = cellphone;
		this.street = street;
		this.number = number;
		this.neighborhood = neighborhood;
		this.city = city;
		this.state = state;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}
		
		return super.toString();
	}
}
