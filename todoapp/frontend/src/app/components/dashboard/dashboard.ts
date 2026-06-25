import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AsyncPipe } from '@angular/common';
import { AuthService } from '../../auth/auth.service';
import { TodoService } from '../../services/todo-service';
import { Todo } from '../../models/todo-model';

@Component({
  selector: 'app-dashboard',
  imports: [RouterLink, AsyncPipe],
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
        this.todoService.getTodos(userId);
      } catch {
        this.username = 'User';
      }
    }
  }

  onEdit(todo: Todo): void {
    console.log('Edit todo:', todo);
  }

  onDelete(todo: Todo): void {
    console.log('Delete todo:', todo);
  }
}
