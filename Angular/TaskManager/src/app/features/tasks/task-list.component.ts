import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TaskService } from '../../services/task.service';
import { Task } from '../../models/task.model';
import { TaskItemComponent } from './task-item.component';
import { BehaviorSubject, combineLatest, map } from 'rxjs';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [CommonModule, FormsModule, TaskItemComponent],
  template: `
    <div class="task-manager-container animate-fade-in">
      <header class="list-header">
        <h1>Mes Tâches</h1>
        <p class="subtitle">Gérez vos priorités avec élégance</p>
      </header>

      <!-- Barre d'ajout -->
      <div class="glass add-task-section">
        <div class="input-group">
          <input type="text" [(ngModel)]="newTaskTitle" (keyup.enter)="addTask()" placeholder="Ajouter une nouvelle tâche...">
          <button class="btn-primary" [disabled]="!newTaskTitle.trim()" (click)="addTask()">Ajouter</button>
        </div>
      </div>

      <!-- Filtres et Recherche -->
      <div class="controls-section">
        <div class="search-box glass">
          <svg viewBox="0 0 24 24" width="20" height="20" stroke="currentColor" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
          <input type="text" [(ngModel)]="searchQuery" (input)="updateFilters()" placeholder="Rechercher...">
        </div>

        <div class="filter-chips">
          <button [class.active]="currentFilter === 'all'" (click)="setFilter('all')">Toutes</button>
          <button [class.active]="currentFilter === 'todo'" (click)="setFilter('todo')">À faire</button>
          <button [class.active]="currentFilter === 'done'" (click)="setFilter('done')">Terminées</button>
        </div>
      </div>

      <!-- Liste des tâches -->
      <div class="tasks-container">
        @if (loading) {
          <div class="status-message">Chargement des tâches...</div>
        } @else if (filteredTasks.length === 0) {
          <div class="status-message glass">Aucune tâche trouvée. ✨</div>
        } @else {
          @for (task of filteredTasks; track task.id) {
            <app-task-item 
              [task]="task" 
              (onToggle)="toggleTask($event)"
              (onDelete)="deleteTask($event)">
            </app-task-item>
          }
        }
      </div>

      <!-- Messages de succès/erreur -->
      @if (message) {
        <div class="toast animate-fade-in" [class.error]="message.type === 'error'">
          {{ message.text }}
        </div>
      }
    </div>
  `,
  styles: [`
    .task-manager-container {
      display: flex;
      flex-direction: column;
      gap: 1.5rem;
    }
    .list-header h1 {
      font-size: 2.5rem;
      font-weight: 700;
      margin-bottom: 0.25rem;
    }
    .subtitle {
      color: var(--text-muted);
      font-size: 1.1rem;
    }
    .add-task-section {
      padding: 1.5rem;
    }
    .add-task-section .input-group {
      margin-bottom: 0;
    }
    .controls-section {
      display: flex;
      justify-content: space-between;
      align-items: center;
      gap: 1rem;
      flex-wrap: wrap;
    }
    .search-box {
      display: flex;
      align-items: center;
      padding: 0 1rem;
      flex: 1;
      min-width: 250px;
      height: 45px;
    }
    .search-box svg {
      color: var(--text-muted);
      margin-right: 0.75rem;
    }
    .search-box input {
      background: transparent;
      border: none;
      color: white;
      width: 100%;
      height: 100%;
      outline: none;
    }
    .filter-chips {
      display: flex;
      gap: 0.5rem;
      background: rgba(255, 255, 255, 0.05);
      padding: 0.3rem;
      border-radius: 0.75rem;
      border: 1px solid var(--glass-border);
    }
    .filter-chips button {
      padding: 0.4rem 1rem;
      border-radius: 0.5rem;
      background: transparent;
      color: var(--text-muted);
      font-weight: 600;
      font-size: 0.9rem;
    }
    .filter-chips button.active {
      background: var(--primary);
      color: white;
    }
    .status-message {
      text-align: center;
      padding: 3rem;
      color: var(--text-muted);
      font-style: italic;
    }
    .toast {
      position: fixed;
      bottom: 2rem;
      right: 2rem;
      background: var(--success);
      color: white;
      padding: 1rem 2rem;
      border-radius: 0.75rem;
      box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.3);
      z-index: 100;
    }
    .toast.error {
      background: var(--danger);
    }
  `]
})
export class TaskListComponent implements OnInit {
  private taskService = inject(TaskService);
  
  tasks: Task[] = [];
  filteredTasks: Task[] = [];
  loading = true;
  newTaskTitle = '';
  searchQuery = '';
  currentFilter: 'all' | 'todo' | 'done' = 'all';
  message: { text: string; type: 'success' | 'error' } | null = null;

  ngOnInit() {
    this.loadTasks();
    this.taskService.tasks$.subscribe(tasks => {
      this.tasks = tasks;
      this.updateFilters();
    });
  }

  loadTasks() {
    this.loading = true;
    this.taskService.getTodos().subscribe({
      next: () => {
        this.loading = false;
      },
      error: () => {
        this.showToast('Erreur lors du chargement des tâches', 'error');
        this.loading = false;
      }
    });
  }

  addTask() {
    if (!this.newTaskTitle.trim()) return;
    
    this.taskService.addTask(this.newTaskTitle).subscribe({
      next: () => {
        this.newTaskTitle = '';
        this.showToast('Tâche ajoutée avec succès !');
      },
      error: () => this.showToast('Erreur lors de l\'ajout', 'error')
    });
  }

  toggleTask(task: Task) {
    this.taskService.toggleTask(task).subscribe({
      next: () => this.showToast('Tâche mise à jour'),
      error: () => this.showToast('Erreur lors de la mise à jour', 'error')
    });
  }

  deleteTask(id: number) {
    this.taskService.deleteTask(id).subscribe({
      next: () => this.showToast('Tâche supprimée'),
      error: () => this.showToast('Erreur lors de la suppression', 'error')
    });
  }

  setFilter(filter: 'all' | 'todo' | 'done') {
    this.currentFilter = filter;
    this.updateFilters();
  }

  updateFilters() {
    this.filteredTasks = this.tasks.filter(task => {
      const matchesSearch = task.title.toLowerCase().includes(this.searchQuery.toLowerCase());
      const matchesFilter = 
        this.currentFilter === 'all' ? true :
        this.currentFilter === 'todo' ? !task.completed :
        task.completed;
      
      return matchesSearch && matchesFilter;
    });
  }

  showToast(text: string, type: 'success' | 'error' = 'success') {
    this.message = { text, type };
    setTimeout(() => {
      this.message = null;
    }, 3000);
  }
}
