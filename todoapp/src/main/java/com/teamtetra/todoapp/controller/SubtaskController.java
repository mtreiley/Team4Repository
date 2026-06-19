package com.teamtetra.todoapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teamtetra.todoapp.entity.Subtask;
import com.teamtetra.todoapp.exception.RegistrationFailure;
import com.teamtetra.todoapp.service.SubtaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SubtaskController {

    private final SubtaskService subtaskService;

    @PostMapping("/subtask")
    public ResponseEntity<Void> createSubtask(@RequestBody Subtask subtask) {

        subtaskService.createSubtask(subtask);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @ExceptionHandler(RegistrationFailure.class)
    public ResponseEntity<String> handleRegistrationFailure(
            RegistrationFailure exception) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}