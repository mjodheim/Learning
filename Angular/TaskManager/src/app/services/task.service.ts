import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Task } from '../models/task.model';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private http = inject(HttpClient);
  private apiUrl = 'https://jsonplaceholder.typicode.com/todos';
  
  private tasksSubject = new BehaviorSubject<Task[]>([]);
  tasks$ = this.tasksSubject.asObservable();

  /**
   * Récupère la liste des tâches depuis l'API
   */
  getTodos(): Observable<Task[]> {
    return this.http.get<Task[]>(`${this.apiUrl}?_limit=10`).pipe(
      tap(tasks => this.tasksSubject.next(tasks))
    );
  }

  /**
   * Ajoute une nouvelle tâche (simulation)
   */
  addTask(title: string): Observable<Task> {
    const newTask = { title, completed: false, userId: 1 };
    return this.http.post<Task>(this.apiUrl, newTask).pipe(
      tap(task => {
        // Simuler l'ID puisque l'API renvoie toujours 201
        const currentTasks = this.tasksSubject.value;
        const taskWithId = { ...task, id: Math.max(...currentTasks.map(t => t.id), 0) + 1 };
        this.tasksSubject.next([taskWithId, ...currentTasks]);
      })
    );
  }

  /**
   * Met à jour une tâche (simulation)
   */
  updateTask(task: Task): Observable<Task> {
    return this.http.put<Task>(`${this.apiUrl}/${task.id}`, task).pipe(
      tap(updatedTask => {
        const currentTasks = this.tasksSubject.value;
        const index = currentTasks.findIndex(t => t.id === task.id);
        if (index !== -1) {
          currentTasks[index] = task; // On utilise l'objet passé car l'API peut ne pas renvoyer l'objet complet
          this.tasksSubject.next([...currentTasks]);
        }
      })
    );
  }

  /**
   * Supprime une tâche (simulation)
   */
  deleteTask(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`).pipe(
      tap(() => {
        const currentTasks = this.tasksSubject.value.filter(t => t.id !== id);
        this.tasksSubject.next(currentTasks);
      })
    );
  }

  /**
   * Alterne l'état complété d'une tâche
   */
  toggleTask(task: Task): Observable<Task> {
    const updatedTask = { ...task, completed: !task.completed };
    return this.updateTask(updatedTask);
  }
}
