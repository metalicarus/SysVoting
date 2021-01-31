package com.hobgoblin.SysVoting.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.hobgoblin.SysVoting.Repositories.Interfaces.VotingRepositoryInterface;
import com.hobgoblin.SysVoting.context.VoteHere;
import com.hobgoblin.SysVoting.entities.Voting;
import com.hobgoblin.SysVoting.services.interfaces.VotingServiceInterface;

@RestController
@RequestMapping(value = "/api/votings")
public class VotingController {

	@Autowired
	private VotingRepositoryInterface votingRepository;
	@Autowired
	private VotingServiceInterface votingService;
	
	@GetMapping
	public ResponseEntity<List<Voting>>findAll() {
		return ResponseEntity.ok(votingRepository.findAll());
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Voting store(@RequestBody Voting voting) {
		return votingRepository.save(voting);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Optional<Voting> getVoting(@PathVariable long id) {
		return votingRepository.findById(id);
	}
	@PostMapping(value = "/voteHere")
	@ResponseStatus(HttpStatus.OK)
	public Voting voteHere(@RequestBody VoteHere voteHere) {
		Voting voting = votingService.voteHere(voteHere.votingId, voteHere.vote);
		if(voting.getErrors().size() > 0) {
			String message = "";
			for(String error : voting.getErrors()) {
				message += error + "\t"; 
			}
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
		}
		return voting;
	}
}
