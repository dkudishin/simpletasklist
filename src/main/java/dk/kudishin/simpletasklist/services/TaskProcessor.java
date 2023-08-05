package dk.kudishin.simpletasklist.services;

import dk.kudishin.simpletasklist.data.TaskDao;
import dk.kudishin.simpletasklist.exceptions.TaskNotFoundException;
import dk.kudishin.simpletasklist.domain.Task;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TaskProcessor implements TaskService {

    private final TaskDao dao;


    public TaskProcessor(@Qualifier("JDBC") TaskDao dao) {
        this.dao = dao;
    }

    public List<Task> getAllTasks() {
        return dao.getAllTasks();
    }

    @Override
    public Task getTaskById(int id) {
        Task task = dao.getTaskById(id);
        if(task == null) {
            throw new TaskNotFoundException();
        }
        return task;
    }

    @Override
    public void createTask(Task t) {
        dao.insertTask(t);
    }

    @Override
    public void updateTask(int id, Task t) {
        t.setId(id);
        dao.updateTask(t);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteTaskById(id);
    }
}