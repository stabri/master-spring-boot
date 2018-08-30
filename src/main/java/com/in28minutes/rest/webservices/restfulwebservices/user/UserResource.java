package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.in28minutes.rest.webservices.restfulwebservices.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
//		return getMappingJacksonValue(service.findAll(),
//				SimpleBeanPropertyFilter.filterOutAllExcept("id", "name"));
		return service.findAll();
	}

//
//	@GetMapping("/users/filer2")
//	public MappingJacksonValue retrieveFilter2AllUsers() {
//		return getMappingJacksonValue(service.findAll(),
//				SimpleBeanPropertyFilter.filterOutAllExcept("id", "password", "birthDate"));
//	}

//	private MappingJacksonValue getMappingJacksonValue(List<User> all, SimpleBeanPropertyFilter simpleBeanPropertyFilter) {
//		MappingJacksonValue mapping = new MappingJacksonValue(all);
//
//		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", simpleBeanPropertyFilter);
//
//		mapping.setFilters(filters);
//		return mapping;
//	}

	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if(user==null)
			throw new UserNotFoundException("id-"+ id);

		Resource<User> sd = new Resource<>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

		sd.add(linkTo.withRel("all-users"));

		return sd;
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		
		if(user==null)
			throw new UserNotFoundException("id-"+ id);		
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);

		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
}
