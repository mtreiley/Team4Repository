package com.teamtetra.todoapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teamtetra.todoapp.entity.Todo;
import com.teamtetra.todoapp.entity.User;
import com.teamtetra.todoapp.exception.AddTodoFailure; //Switch later
import com.teamtetra.todoapp.repo.TodoRepo;
import com.teamtetra.todoapp.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepo todoRepo;
    private final UserRepo userRepo;

    public void addTodo(Todo todo){
        //check for existing user
        if (userRepo.findByUserId(todo.getUserId()).isPresent())
        {
            todoRepo.save(todo);
        }
        else{
            throw new AddTodoFailure("Could not find matching user id");
        }
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

        //check for existing user
        if (userRepo.findByUserId(user.getUserId()).isPresent())
        {
            List<Todo> todoList = todoRepo.findByUserId(user.getUserId());

            if (todoList.isEmpty())
            {
                throw new AddTodoFailure("Could not find any todos for matching user id");
            }
            else{
                return todoList;
            }
        }
        else{
            throw new AddTodoFailure("Could not find matching user id");
        }

    }


}
