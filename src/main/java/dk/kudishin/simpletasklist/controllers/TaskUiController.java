package dk.kudishin.simpletasklist.controllers;

import dk.kudishin.simpletasklist.domain.Task;
import dk.kudishin.simpletasklist.services.TaskService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
@RequestMapping("/ui")
public class TaskUiController {

    private final TaskService taskService;

    public TaskUiController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String loadAll(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/task")
    public String loadFirstUi(@RequestParam("id") int id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "task";
    }

    @GetMapping("/tasks/{id}")
    public String loadSingleViaPathVariable(@PathVariable int id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "task";
    }

    @GetMapping("/new")
    public String newForm(@ModelAttribute("task") Task task) {
        return "new";
    }

    @PostMapping("/tasks")
    public String create(@ModelAttribute Task t) {
        taskService.createTask(t);
        return "redirect:/ui/tasks";
    }

    @GetMapping("/tasks/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "edit";
    }

    @PutMapping("/tasks/{id}")
    public String doEdit(@ModelAttribute Task t, @PathVariable int id) {
        taskService.updateTask(t.getId(), t);
        return "redirect:/ui/tasks";
    }

    @DeleteMapping("/tasks/{id}")
    public String delete(@PathVariable int id) {
        taskService.deleteById(id);
        return "redirect:/ui/tasks";
    }

}