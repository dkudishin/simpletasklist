package dk.kudishin.simpletasklist.controllers;

import dk.kudishin.simpletasklist.domain.Task;
import dk.kudishin.simpletasklist.services.TaskService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class TaskRestController {

    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping ("/tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @RequestMapping("/tasks/{id}")
    public Task loadOneByPathVariable(@PathVariable int id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNew(@RequestBody Task t) {
        taskService.createTask(t);
    }

    @PutMapping("/tasks/{id}")
    public void update(@RequestBody Task t) {
        taskService.updateTask(t);
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable int id) {
        taskService.deleteById(id);
    }

}