package com.hobgoblin.SysVoting.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

 
@Entity
@Table(name = "schedules")
public class Schedule implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@OneToOne
	@JoinColumn(name = "voting_id", nullable = true)
	private Voting voting;
	
	public Schedule() {}
	public Schedule(Long id, String name, Voting voting) {
		this.name = name;
		this.id = id;
		this.voting = voting;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Voting getVoting() {
		return voting;
	}
	public void setVoting(Voting voting) {
		this.voting = voting;
	}
	
}
