import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Task } from '../../models/task.model';

@Component({
  selector: 'app-task-item',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="task-item glass animate-fade-in" [class.completed]="task.completed">
      <div class="task-content">
        <label class="checkbox-container">
          <input type="checkbox" [checked]="task.completed" (change)="onToggle.emit(task)">
          <span class="checkmark"></span>
        </label>
        <span class="task-title">{{ task.title }}</span>
      </div>
      <div class="task-actions">
        <button class="btn-icon delete" (click)="onDelete.emit(task.id)" title="Supprimer">
          <svg viewBox="0 0 24 24" width="18" height="18" stroke="currentColor" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round"><polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path><line x1="10" y1="11" x2="10" y2="17"></line><line x1="14" y1="11" x2="14" y2="17"></line></svg>
        </button>
      </div>
    </div>
  `,
  styles: [`
    .task-item {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 1rem 1.25rem;
      margin-bottom: 0.75rem;
      transition: all 0.2s;
    }
    .task-item:hover {
      border-color: var(--primary);
      transform: translateX(4px);
    }
    .task-content {
      display: flex;
      align-items: center;
      gap: 1rem;
      flex: 1;
    }
    .task-title {
      font-size: 1rem;
      color: var(--text-main);
      transition: all 0.2s;
    }
    .completed .task-title {
      text-decoration: line-through;
      color: var(--text-muted);
    }
    
    /* Custom Checkbox */
    .checkbox-container {
      display: block;
      position: relative;
      padding-left: 25px;
      cursor: pointer;
      user-select: none;
    }
    .checkbox-container input {
      position: absolute;
      opacity: 0;
      cursor: pointer;
      height: 0;
      width: 0;
    }
    .checkmark {
      position: absolute;
      top: -10px;
      left: 0;
      height: 20px;
      width: 20px;
      background-color: rgba(255, 255, 255, 0.1);
      border: 1px solid var(--glass-border);
      border-radius: 4px;
    }
    .checkbox-container:hover input ~ .checkmark {
      background-color: rgba(255, 255, 255, 0.2);
    }
    .checkbox-container input:checked ~ .checkmark {
      background-color: var(--success);
      border-color: var(--success);
    }
    .checkmark:after {
      content: "";
      position: absolute;
      display: none;
    }
    .checkbox-container input:checked ~ .checkmark:after {
      display: block;
    }
    .checkbox-container .checkmark:after {
      left: 6px;
      top: 2px;
      width: 5px;
      height: 10px;
      border: solid white;
      border-width: 0 2px 2px 0;
      transform: rotate(45deg);
    }

    .btn-icon {
      background: transparent;
      color: var(--text-muted);
      padding: 0.5rem;
      border-radius: 0.4rem;
      display: flex;
      align-items: center;
      justify-content: center;
    }
    .btn-icon:hover {
      background: rgba(239, 68, 68, 0.1);
      color: var(--danger);
    }
  `]
})
export class TaskItemComponent {
  @Input({ required: true }) task!: Task;
  @Output() onToggle = new EventEmitter<Task>();
  @Output() onDelete = new EventEmitter<number>();
}
