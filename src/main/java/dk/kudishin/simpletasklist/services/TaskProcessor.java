package dk.kudishin.services;

import dk.kudishin.data.TaskDao;
import dk.kudishin.domain.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskProcessor implements TaskService {

    private final TaskDao dao;


    public TaskProcessor(TaskDao dao) {
        this.dao = dao;
    }

    public List<Task> getAllTasks() {
        return dao.getAllTasks();
    }
}