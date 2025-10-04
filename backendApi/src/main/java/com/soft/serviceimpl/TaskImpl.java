package com.soft.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft.entity.Task;
import com.soft.entity.User;
import com.soft.repository.TaskRepository;
import com.soft.repository.UserRepository;
import com.soft.service.ItaskService;

@Service
public class TaskImpl implements ItaskService {
	
	@Autowired
	private TaskRepository taskRepo;
	@Autowired
	private UserRepository userRepo;

	@Override
	public Task create(Task task , int userId) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User not found")); 
		task.setUser(user);
		return taskRepo.save(task);
	}

	@Override
	public Task update(int id, Task task) {
		// TODO Auto-generated method stub
		Optional<Task> existing=taskRepo.findById(id);
		if(existing.isPresent()) {
			Task updatTask=existing.get();
			updatTask.setTitle(task.getTitle());
			updatTask.setDescription(task.getDescription());
			updatTask.setCompleated(task.isCompleated());
			return taskRepo.save(updatTask);
		}
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		taskRepo.deleteById(id);
	}

	@Override
	public Task getById(int id) {
		// TODO Auto-generated method stub
		Optional<Task> task=taskRepo.findById(id); 
		if(task.isPresent()) {
			return task.get();
		}
		return null;
	}

	@Override
	public List<Task> getAll() {
		// TODO Auto-generated method stub
		return taskRepo.findAll();
	}

	@Override
	public List<Task> getByUser(User user) {
		// TODO Auto-generated method stub
		return taskRepo.findByUser(user);
	}

}
