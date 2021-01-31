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

import com.hobgoblin.SysVoting.Repositories.Interfaces.ScheduleRepositoryInterface;
import com.hobgoblin.SysVoting.entities.Schedule;
  
@RestController
@RequestMapping(value = "/api/schedules")
public class ScheduleController {
	
	@Autowired
	private ScheduleRepositoryInterface scheduleRepository;
 
	
	@GetMapping
	public ResponseEntity<List<Schedule>>findAll() {
		return ResponseEntity.ok(scheduleRepository.findAll());
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Schedule store(@RequestBody Schedule schedule) {
		return scheduleRepository.save(schedule);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Optional<Schedule> getSchedule(@PathVariable long id) {
		return scheduleRepository.findById(id);
	}
}
