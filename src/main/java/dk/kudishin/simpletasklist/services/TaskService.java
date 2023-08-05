package dk.kudishin.simpletasklist.services;

import dk.kudishin.simpletasklist.domain.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task getTaskById(int id);

    void createTask(Task t);

    void updateTask(int id, Task t);

    void deleteById(int id);
}