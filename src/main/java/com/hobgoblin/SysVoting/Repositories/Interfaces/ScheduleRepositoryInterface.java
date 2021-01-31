package com.hobgoblin.SysVoting.Repositories.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hobgoblin.SysVoting.entities.Schedule;

@Repository
public interface ScheduleRepositoryInterface extends JpaRepository<Schedule, Long>{}
