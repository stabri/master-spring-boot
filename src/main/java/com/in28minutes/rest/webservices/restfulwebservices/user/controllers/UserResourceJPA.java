package com.in28minutes.rest.webservices.restfulwebservices.user.controllers;

import com.in28minutes.rest.webservices.restfulwebservices.user.entities.Post;
import com.in28minutes.rest.webservices.restfulwebservices.user.exceptions.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.user.repositories.PostRepository;
import com.in28minutes.rest.webservices.restfulwebservices.user.repositories.UserRepository;
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

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
public class UserResourceJPA {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return repository.findAll();
    }


    @GetMapping("/jpa/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = repository.findById(id);

        if (!user.isPresent())
            throw new UserNotFoundException("id-" + id);

        Resource<User> sd = new Resource<>(user.get());
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        sd.add(linkTo.withRel("all-users"));

        return sd;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        repository.deleteById(id);
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = repository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping(value = "/jpa/users/{id}/posts")
    public List<Post> retrieveAllUserPosts(@PathVariable int id) {
        Optional<User> user = repository.findById(id);

        if(!user.isPresent()){
            throw new UserNotFoundException("id "+ id);
        }
        return user.get().getPostList();
    }
}
