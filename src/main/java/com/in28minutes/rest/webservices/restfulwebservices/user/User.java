
package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

// /*second approach: */@JsonIgnoreProperties(value = "password")
@JsonFilter("UserFilter")
@ApiModel(description = "All details about user. ")
public class User {

	private Integer id;

	@ApiModelProperty(notes = "Name should have atleast 2 characters")
	@Size(min=2, message="Name should have atleast 2 characters")
	private String name;

	@Past
	@ApiModelProperty(notes = " can't be in the past")
	private Date birthDate;

	//filtering value of password to not expose in json REST
	@JsonIgnore
	private String password;

	protected User() {

	}

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.password = "default";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, birthDate=%s]", id, name, birthDate);
	}

}
