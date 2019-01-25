package lira.leo.selecaocast.questao3.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

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
}
