package dk.kudishin.simpletasklist.controllers;

import dk.kudishin.simpletasklist.domain.Task;
import dk.kudishin.simpletasklist.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping ("/")
    @ResponseBody
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @RequestMapping("/task")
    @ResponseBody
    public Task loadFirst(@RequestParam("id") int id) {
        Task task = taskService.getTaskById(id);
        return task;
    }

    @RequestMapping("/taskui")
    public String loadFirstUi(@RequestParam("id") int id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "task";
    }

    @RequestMapping("tasks/{id}")
    public String loadSingleViaPathVariable(@PathVariable int id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "task";
    }
}