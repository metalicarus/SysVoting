package com.hobgoblin.SysVoting.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hobgoblin.SysVoting.Repositories.Interfaces.VoteRepositoryInterface;
import com.hobgoblin.SysVoting.Repositories.Interfaces.VotingRepositoryInterface;
import com.hobgoblin.SysVoting.entities.Vote;
import com.hobgoblin.SysVoting.entities.Voting;
import com.hobgoblin.SysVoting.services.interfaces.VotingServiceInterface;

@Service
public class VotingService implements VotingServiceInterface{
	
	@Autowired
	private VotingRepositoryInterface votingRepository;
	@Autowired
	private VoteRepositoryInterface voteRepository;

	@Override
	public Voting voteHere(Long votingId, Vote vote) {
		Voting voting = votingRepository.getOne(votingId);
		if(voting.compareDates(new Date())) {
			if (voting.checkAssociatedVote(vote.getAssociate().getId()) == true) {
				voting.addVote(voteRepository.save(vote));
			}
			else {
				voting.addError("Already voted!"); 
			}
		}
		else {
			voting.addError("Voting ended!");
		}
		voting.finished();
		return votingRepository.save(voting);
	}
}
