package com.teamtetra.todoapp.service;

import org.springframework.stereotype.Service;

import com.teamtetra.todoapp.entity.Todo;
import com.teamtetra.todoapp.exception.AddTodoFailure; //Switch later
import com.teamtetra.todoapp.exception.LoginFailure;
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

    public void deleteTodo(Todo todo){

        todoRepo.delete(todo);
    }

    public void updateTodo(Todo todo){

        //Optional<Todo>

        if (todoRepo.findById(todo.getTodoId()).isPresent())
        {
            todoRepo.save(todo);
        }
        else{
             throw new AddTodoFailure("Could not find matching todo id");
        }

        //todo wasn't found

        //todo was found


    }
}
