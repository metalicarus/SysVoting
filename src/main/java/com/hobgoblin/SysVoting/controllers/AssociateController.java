package com.hobgoblin.SysVoting.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hobgoblin.SysVoting.Repositories.Interfaces.AssociateRepositoryInterface;
import com.hobgoblin.SysVoting.entities.Associate;

@RestController
@RequestMapping(value = "/api/associates")
public class AssociateController {
	@Autowired
	private AssociateRepositoryInterface repository;
 
	@GetMapping
	public ResponseEntity<List<Associate>>findAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	@PutMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Associate store(@RequestBody Associate associate) {
		return repository.save(associate);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Optional<Associate> getAssociate(@PathVariable long id) {
		return repository.findById(id);
	}
}
