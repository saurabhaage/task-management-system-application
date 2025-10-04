package com.soft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soft.entity.Task;
import com.soft.entity.User;
import com.soft.service.ItaskService;
import com.soft.service.IuserService;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private ItaskService taskService;
	
	@Autowired
	private IuserService userService;
	
	@PostMapping("/create/{userId}")
	public ResponseEntity<Task> creatTask(@RequestBody Task task,@PathVariable int userId) {
		
		return ResponseEntity.ok(taskService.create(task , userId));
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Task>> getAllTask(){
		return ResponseEntity.ok(taskService.getAll());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Task> getTask(@PathVariable int id){
		return ResponseEntity.ok(taskService.getById(id));
	}
	
	@PutMapping("/updat/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task task){
		return ResponseEntity.ok(taskService.update(id, task));
	}
	
	@DeleteMapping("/delet/{id}")
	public ResponseEntity<String> deletTask(@PathVariable int id){
		taskService.delete(id);
		return ResponseEntity.ok(" Task Delet successfully ");
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Task>> getTaskByUser(@PathVariable int userId){
		User user = userService.getUserById(userId);
		List<Task> tasks = taskService.getByUser(user);
		return ResponseEntity.ok(tasks);
	}
}
