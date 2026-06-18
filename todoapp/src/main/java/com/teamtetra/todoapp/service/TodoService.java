package com.teamtetra.todoapp.service;

import org.springframework.stereotype.Service;

import com.teamtetra.todoapp.entity.Todo;
import com.teamtetra.todoapp.repo.TodoRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepo todoRepo;

    public void addTodo(Todo todo){

        //Todo validation logic
        todoRepo.save(todo);
    }
}
