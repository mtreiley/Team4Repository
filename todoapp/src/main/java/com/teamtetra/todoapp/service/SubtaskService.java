package com.teamtetra.todoapp.service;



import org.springframework.stereotype.Service;

import com.teamtetra.todoapp.entity.Subtask;
import com.teamtetra.todoapp.exception.RegistrationFailure;
import com.teamtetra.todoapp.repo.SubtaskRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubtaskService {
    private final SubtaskRepo subtaskRepo;


    public void createSubtask(Subtask subtask){
        // Title cannot be null or blank
        if (subtask.getTitle() == null || subtask.getTitle().isBlank()) {
            throw new RegistrationFailure("Title cannot be empty");
        }

        // Title must contain at least one letter
        if (!subtask.getTitle().matches(".*[a-zA-Z].*")) {
            throw new RegistrationFailure(
                "Title must contain at least one letter and cannot be only numbers or special characters"
            );
        }


        subtaskRepo.save(subtask);
    }
    
    

    
}
