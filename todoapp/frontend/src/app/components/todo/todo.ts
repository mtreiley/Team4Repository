import { Component, inject } from '@angular/core';
import { TodoService } from '../../services/todo-service';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-todo',
  imports: [AsyncPipe],
  templateUrl: './todo.html',
  styleUrl: './todo.css',
})
export class Todo {

  todoService = inject(TodoService)

  getTodos(){
    this.todoService.getTodos(1);
  }
  

}
