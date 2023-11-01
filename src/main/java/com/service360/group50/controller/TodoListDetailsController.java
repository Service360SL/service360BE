package com.service360.group50.controller;

import com.service360.group50.entity.TodoList;
import com.service360.group50.entity.TodoListDetails;
import com.service360.group50.repo.TodoListRepository;
import com.service360.group50.service.TodoListDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class TodoListDetailsController {
    private final TodoListDetailsService todoListDetailsService;
    private final TodoListRepository todoListRepository;

    @PostMapping("/createTodoListDetails/{todoListdetailsid}")
    public TodoListDetails createTodoListDetails(@PathVariable Long todoListdetailsid, @RequestBody TodoListDetails newTodoListDetails) {
        TodoListDetails task = new TodoListDetails();

        TodoList todolist = todoListRepository.findById(todoListdetailsid).orElse(null);

        task.setTask(newTodoListDetails.getTask());
        task.setTodolist(todolist);
        task.setWorkedHours(newTodoListDetails.getWorkedHours());
        task.setCompleted(newTodoListDetails.isCompleted());
        task.setAmount(newTodoListDetails.getAmount());
        task.setCustomercompleted(newTodoListDetails.isCustomercompleted());

        System.out.println(newTodoListDetails);
        return todoListDetailsService.createTodoListDetails(task);
    }

    @GetMapping("/viewTodoListDetails")
    public List<TodoListDetails> viewTodoListDetails() {
        return todoListDetailsService.viewTodoListDetails();
    }
    @PutMapping("/viewTodoListDetails/{todoListdetailsid}")
    public TodoListDetails toggleTaskCompletion(@PathVariable Long todoListdetailsid, @RequestParam("completed") Boolean completed,@RequestParam("hours") Double hours) {

        // Retrieve the task by ID
        TodoListDetails task = todoListDetailsService.getTodoListDetailsById(todoListdetailsid);

        // Toggle the completion status
        task.setCompleted(!completed);
        task.setWorkedHours(hours);
//        task.setCustomercompleted(completed);
        // Save the updated task
        return todoListDetailsService.createTodoListDetails(task);
    }

    @PutMapping("/ConfirmTodoListDetails/{todoListdetailsid}")
    public TodoListDetails toggleTaskCompletion(@PathVariable Long todoListdetailsid, @RequestParam("ccompleted") Boolean ccompleted) {
        // Retrieve the task by ID
        TodoListDetails task = todoListDetailsService.getTodoListDetailsById(todoListdetailsid);
        // Toggle the completion status
        task.setCustomercompleted(ccompleted);
        // Save the updated task
        return todoListDetailsService.createTodoListDetails(task);
    }

    @PutMapping("updatetoDoListPaymetStatus/{todoListdetailsid}")
    public TodoList updatePaymentStatus(@PathVariable Long todoListdetailsid) {
        // Retrieve the task by ID
        TodoList todolist = todoListRepository.findById(todoListdetailsid).orElse(null);
        // Toggle the completion status
        todolist.setStatus("payment pending");
        // Save the updated task
        return todoListDetailsService.updatePaymentTodoList(todolist);

    }

    @GetMapping("getTodoListPaymentStatus/{todoListid}")
    public String getPaymentStatus(@PathVariable Long todoListid) {
        // Retrieve the task by ID
        TodoList todolist = todoListRepository.findById(todoListid).orElse(null);
        // Toggle the completion status
        return todolist.getStatus();

    }

}