package com.soft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soft.entity.Task;
import com.soft.entity.User;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	List<Task> findByUser(User user); //get task from specific user
}
