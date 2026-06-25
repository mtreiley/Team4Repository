import { HttpClient } from '@angular/common/http';
import { inject, Service } from '@angular/core';
import { BehaviorSubject, catchError, Observable, of, tap } from 'rxjs';
import { environment } from '../../environments/environment';
import { SubtaskModel } from '../models/subtask-model';

@Service()
export class SubtaskService {

    private http = inject(HttpClient);
    private apiBaseUrl = environment.apiBaseUrl;

    private subtaskSubject: BehaviorSubject<SubtaskModel[]>;

    subtask$: Observable<SubtaskModel[]>;

    private emptySubtasks: SubtaskModel[] = [];
    
    constructor(){
        this.subtaskSubject = new BehaviorSubject<SubtaskModel[]>([]);
        this.subtask$ = this.subtaskSubject.asObservable();
    }


    getSubtasks() {
        this.http.get<SubtaskModel[]>(`${this.apiBaseUrl}/subtask`,)
        .pipe(
            tap(subtaskData => this.subtaskSubject.next(subtaskData)),
            catchError(error => {
                console.log(`Something went wrong: ${error}`)
                this.subtaskSubject.next(this.emptySubtasks)
                return of(null);
            })
        ).subscribe()
    }

    addSubtask(subtask: Partial<SubtaskModel>) {
        return this.http.post<SubtaskModel>(`${this.apiBaseUrl}/subtask`, subtask);
    }

    deleteSubtask(subtask: SubtaskModel){
        return this.http.delete<void>(`${this.apiBaseUrl}/subtask`, {
            body: subtask
        });
    }
    updateSubtask(subtask: SubtaskModel){
        return this.http.put<void>(`${this.apiBaseUrl}/subtask`, subtask);
    }
}
