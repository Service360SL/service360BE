package com.service360.group50.service;

import com.service360.group50.entity.*;
import com.service360.group50.repo.CServiceProviderRepo;
import com.service360.group50.repo.TodoListDetailsRepo;
import com.service360.group50.repo.TodoListRepository;
import com.service360.group50.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoListDetailsService {
    private final TodoListDetailsRepo todoListDetailsRepo;

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired

    public TodoListDetailsService(TodoListDetailsRepo todoListDetailsRepo) {
        this.todoListDetailsRepo = todoListDetailsRepo;
    }

    public TodoListDetails createTodoListDetails (TodoListDetails newTodoListDetails) {

        return this.todoListDetailsRepo.save(newTodoListDetails);
    }

    public TodoList updatePaymentTodoList (TodoList newTodoListDetails) {

        return this.todoListRepository.save(newTodoListDetails);
    }

    public List<TodoListDetails> viewTodoListDetails() {
        return this.todoListDetailsRepo.findAll();
    }

    public List<TodoListDetails> getAllDetails() {
        try {
            List<TodoListDetails> todoListDetails = todoListDetailsRepo.findAll();
            return todoListDetails;
        } catch (Exception e) {
            // You can log the exception or handle it in a way that makes sense for your application
            throw new RuntimeException("Failed to fetch service provider details", e);
        }
    }

    public TodoListDetails getTodoListDetailsById(Long todoListDetailsid) {
        Optional<TodoListDetails> todoListDetails = todoListDetailsRepo.findById(todoListDetailsid);
        return todoListDetailsRepo.findById(todoListDetailsid).orElse(null);
    }


}

