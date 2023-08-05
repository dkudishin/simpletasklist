package dk.kudishin.simpletasklist.data;

import dk.kudishin.simpletasklist.domain.Task;

import java.util.List;

public interface TaskDao {

    List<Task> getAllTasks();

    Task getTaskById(int id);

    int insertTask(Task t);

    int updateTask(Task t);

    int deleteTaskById(int id);
}