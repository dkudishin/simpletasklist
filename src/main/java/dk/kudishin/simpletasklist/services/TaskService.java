package dk.kudishin.simpletasklist.services;

import dk.kudishin.simpletasklist.domain.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAll();

    Task getById(int id);

    void create(Task t);

    void update(Task t);

    void deleteById(int id);
}