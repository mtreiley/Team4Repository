import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { Register } from './components/register/register';
import { Dashboard } from './components/dashboard/dashboard';
import { Subtask } from './components/subtask/subtask';
import { Todo } from './components/todo/todo';

export const routes: Routes = [
    {
        path:'',
        redirectTo: 'dashboard',
        pathMatch:'full'
    },
    {
        path: 'dashboard',
        component: Dashboard,
        children: [
            {
                path: 'todo',
                component: Todo
            },
            {
                path: 'subtask',
                component: Subtask
            }
        ]
    }
];
