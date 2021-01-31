package com.hobgoblin.SysVoting.services.interfaces;

import com.hobgoblin.SysVoting.entities.Vote;
import com.hobgoblin.SysVoting.entities.Voting;

public interface VotingServiceInterface {
	public Voting voteHere(Long votingId, Vote vote);
}
