package com.soft.service;

import java.util.List;

import com.soft.entity.Task;
import com.soft.entity.User;

public interface ItaskService {

	Task create(Task task,int userId);
    Task update(int id, Task task);
    void delete(int id);
    Task getById(int id);
    List<Task> getAll();
    List<Task> getByUser(User user);
}
