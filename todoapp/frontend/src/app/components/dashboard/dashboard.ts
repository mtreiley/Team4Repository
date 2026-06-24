import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { Router, RouterLink, RouterLinkWithHref, RouterOutlet } from '@angular/router';
import { TodoService } from '../../services/todo-service';
import { AsyncPipe } from '@angular/common';
import { Subtask } from '../subtask/subtask';
import { Todo } from '../todo/todo';

@Component({
  selector: 'app-dashboard',
  imports: [Todo, Subtask, RouterOutlet, RouterLinkWithHref],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {

  

  
}
