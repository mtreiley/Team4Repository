import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Todo } from '../models/todo-model';
import { BehaviorSubject, catchError, Observable, of, tap } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class TodoService {

    private http = inject(HttpClient);
    private apiBaseUrl = environment.apiBaseUrl;

    private todoSubject: BehaviorSubject<Todo>;

    todo$: Observable<Todo>;

    private emptyTodo = {
        todoId: 0,
        userId: 0,
        title: '',
        completed: false
    }
    
    constructor(){
        this.todoSubject = new BehaviorSubject<Todo>({
            todoId: 0,
            userId: 0,
            title: '',
            completed: false
        });
        this.todo$ = this.todoSubject.asObservable();
    }


    getTodos(userId: number) {
        this.http.get<Todo>(`${this.apiBaseUrl}/todo`, {
        params: { userId }
        })
        .pipe(
            tap(todoData => this.todoSubject.next(todoData)),
            catchError(error => {
                console.log(`Something went wrong: ${error}`)
                this.todoSubject.next(this.emptyTodo)
                return of(null);
            })
        ).subscribe()
    }

    /*createTodo(todo: Partial<Todo>) {
        return this.http.post<Todo>(`${this.apiBaseUrl}/todo`, todo);
    }

    updateTodo(todo: Todo) {
        return this.http.put<Todo>(`${this.apiBaseUrl}/todo`, todo);
    }

    deleteTodo(todoId: number) {
        return this.http.delete<void>(`${this.apiBaseUrl}/todo`, {
        params: { todoId }
        });
    }*/
}
