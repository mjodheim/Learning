import { Routes } from '@angular/router';
import { TaskListComponent } from './features/tasks/task-list.component';

export const routes: Routes = [
  { path: 'tasks', component: TaskListComponent },
  { path: '', redirectTo: 'tasks', pathMatch: 'full' },
  { 
    path: 'about', 
    loadComponent: () => import('./shared/ui/about.component').then(m => m.AboutComponent) 
  }
];
