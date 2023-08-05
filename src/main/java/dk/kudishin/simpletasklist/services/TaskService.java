package dk.kudishin.services;

import dk.kudishin.domain.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();
}