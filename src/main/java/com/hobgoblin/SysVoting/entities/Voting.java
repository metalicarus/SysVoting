package com.hobgoblin.SysVoting.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hobgoblin.SysVoting.enums.Option;
 
 
@Entity
@Table(name= "votings")
public class Voting implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name = "VOTATION - " + UUID.randomUUID().toString();
    private Date end = defaultDate();
    private Boolean open = true;
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
    private Option choise = null;
	
	@OneToMany()
	private List<Vote> votes;
	
	@Transient
	private List<String> _errors = new ArrayList<String>();

	public Voting() {}
	public Voting(Long id, Date end, Boolean open, Option choise, String name, List<Vote> votes) {
		this.id = id;
		this.end = end;
		this.open = open;
		this.choise = choise;
		this.name = name;
		this.votes = votes;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	public Option getChoise() {
		return choise;
	}
	public void setChoise(Option choise) {
		this.choise = choise;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void addVote(Vote vote) {
		this.votes.add(vote);
	}
	public void addError(String error) {
		this._errors.add(error);
	}
	public List<Vote> getVotes() {
		return votes;
	}
	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}
	public List<String> getErrors() {
		return _errors;
	}
	public void setErrors(List<String> _errors) {
		this._errors = _errors;
	}
	private Date defaultDate() {
	    final long ONE_MINUTE_IN_MILLIS = 60000;
	    long curTimeInMs = new Date().getTime();
	    Date afterAddingMins = new Date(curTimeInMs + (ONE_MINUTE_IN_MILLIS));
	    return afterAddingMins;
	}
	public boolean compareDates(Date date) {
		return (date.getTime() > end.getTime()) ? false : true;
	}
	public boolean checkAssociatedVote(Long associateId) {
		for(Vote vote: votes) {
			if(vote.getAssociate().getId() == associateId) return false;
		}
		return true;
	}
	public void finished() {
		this.open = false;
		this.choise = this.countVotes();
	}
	private Option countVotes() {
		int yes = 0;
		int not = 0;
		for(Vote vote : votes) {
			if(vote.getChoise() == Option.YES) yes++;
			if(vote.getChoise() == Option.NOT) not++;
		}
		if(yes > not) return Option.YES;
		if(not > yes) return Option.NOT;
		return Option.DRAW;
	}
}
