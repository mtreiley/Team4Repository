package com.teamtetra.todoapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teamtetra.todoapp.entity.Todo;
import com.teamtetra.todoapp.entity.User;
import com.teamtetra.todoapp.exception.AddTodoFailure; //Switch later
import com.teamtetra.todoapp.exception.LoginFailure;
import com.teamtetra.todoapp.repo.TodoRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepo todoRepo;

    // public void addTodo(Todo todo){

    //     if (todoRepo.findById(todo.getTodoId()).isPresent())
    //     {
    //         throw new AddTodoFailure("Duplicate found");
    //     }
    //     else{
    //         todoRepo.save(todo);
    //     }
    // }

    public void addTodo(Todo todo){
        todoRepo.save(todo);
    }

    public void deleteTodo(Todo todo){

        if (todoRepo.findById(todo.getTodoId()).isPresent())
        {
            todoRepo.delete(todo);
        }
        else{
            throw new AddTodoFailure("Could not find matching todo id");
        }
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

    }

    public List<Todo> getTodos(User user){

        List<Todo> todoList = todoRepo.findByUserId(user.getId());

        if (todoList.isEmpty())
        {
            throw new AddTodoFailure("Could not find matching todo id");
        }
        else{
            return todoList;
        }

    }


}
