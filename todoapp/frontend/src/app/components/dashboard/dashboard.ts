import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AsyncPipe } from '@angular/common';
import { Todo } from '../todo/todo'
import { Subtask } from '../subtask/subtask';
import { AuthService } from '../../auth/auth.service';
import { TodoService } from '../../services/todo-service';
import { TodoModel } from '../../models/todo-model';

@Component({
  selector: 'app-dashboard',
  imports: [RouterLink, AsyncPipe, Todo, Subtask],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
  private authService = inject(AuthService);
  todoService = inject(TodoService);

  username: string = 'User';

  ngOnInit(): void {
    const token = this.authService.token();
    if (token) {
      try {
        const payload = JSON.parse(atob(token.split('.')[1]));
        this.username = payload.username ?? 'User';
        const userId = Number(payload.sub);
        this.todoService.getTodos();
      } catch {
        this.username = 'User';
      }
    }
  }

  onEdit(todo: TodoModel): void {
    console.log('Edit todo:', todo);
  }

  onDelete(todo: TodoModel): void {
    console.log('Delete todo:', todo);
  }
}
