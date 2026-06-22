package com.teamtetra.todoapp.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.teamtetra.todoapp.entity.Subtask;
import com.teamtetra.todoapp.entity.Subtask;
import com.teamtetra.todoapp.entity.Todo;
import com.teamtetra.todoapp.exception.AddSubtaskFailure;
import com.teamtetra.todoapp.exception.AddTodoFailure;
import com.teamtetra.todoapp.exception.RegistrationFailure;
import com.teamtetra.todoapp.repo.SubtaskRepo;
import com.teamtetra.todoapp.repo.TodoRepo;
import com.teamtetra.todoapp.repo.SubtaskRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubtaskService {
    private final SubtaskRepo subtaskRepo;
    private final TodoRepo todoRepo;

        public void addSubtask(Subtask subtask){
        //check for existing user
        if (todoRepo.findByTodoId(subtask.getTodoId()).isPresent())
        {
            subtaskRepo.save(subtask);
        }
        else{
            throw new AddSubtaskFailure("Could not find matching todo id");
        }
    }

    public void deleteSubtask(Subtask subtask){

        if (subtaskRepo.findById(subtask.getSubtaskId()).isPresent())
        {
            subtaskRepo.delete(subtask);
        }
        else{
            throw new AddSubtaskFailure("Could not find matching subtask id");
        }
    }

    public void updateSubtask(Subtask subtask){

        //Optional<Subtask>

        if (subtaskRepo.findById(subtask.getSubtaskId()).isPresent())
        {
            subtaskRepo.save(subtask);
        }
        else{
            throw new AddSubtaskFailure("Could not find matching subtask id");
        }

    }

    public List<Subtask> getSubtasks(Todo todo){

        //check for existing user
        if (todoRepo.findByTodoId(todo.getTodoId()).isPresent())
        {
            List<Subtask> subtaskList = subtaskRepo.findByTodoId(todo.getTodoId());

            if (subtaskList.isEmpty())
            {
                throw new AddSubtaskFailure("Could not find any subtasks for matching todo id");
            }
            else{
                return subtaskList;
            }
        }
        else{
            throw new AddSubtaskFailure("Could not find matching todo id");
        }

    }

}
