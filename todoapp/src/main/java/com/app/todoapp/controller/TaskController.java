package com.app.todoapp.controller;

import com.app.todoapp.models.Task;
import com.app.todoapp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;  // <-- needed

import java.util.List;

@Controller
@RequestMapping("/tasks")  // optional, uncomment if you want /tasks endpoint
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String home(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";  // returns the "tasks.html" Thymeleaf template
    }

    @PostMapping
    public String createTasks(@RequestParam String title) {
        taskService.createTask(title);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
       taskService.deleteTask(id);
        return "redirect:/tasks";  // returns the "tasks.html" Thymeleaf template
    }

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id) {
        taskService.toggleTask(id);
        return "redirect:/tasks";  // returns the "tasks.html" Thymeleaf template
    }

}
