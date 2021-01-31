package com.hobgoblin.SysVoting.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hobgoblin.SysVoting.enums.Option;

@Entity
@Table(name = "votes")
public class Vote implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "associate_id")
	private Associate associate;
	
	@Enumerated(EnumType.STRING)
    private Option choise;
	
	public Vote() {}
	public Vote(Long id, Associate associate, Option choise) {
		this.id = id;
		this.associate = associate;
		this.choise = choise;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Associate getAssociate() {
		return associate;
	}
	public void setAssociate(Associate associate) {
		this.associate = associate;
	}
	public Option getChoise() {
		return choise;
	}
	public void setChoise(Option choise) {
		this.choise = choise;
	}	
}
