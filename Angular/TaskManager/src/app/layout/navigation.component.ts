import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-navigation',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  template: `
    <nav class="glass nav-header animate-fade-in">
      <div class="logo">
        <span class="icon">🚀</span>
        <span class="text">TaskMaster</span>
      </div>
      <div class="nav-links">
        <a routerLink="/tasks" routerLinkActive="active">Mes Tâches</a>
        <a routerLink="/about" routerLinkActive="active">À Propos</a>
      </div>
    </nav>
  `,
  styles: [`
    .nav-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 1rem 2rem;
      margin-bottom: 2rem;
      border-radius: 1rem;
    }
    .logo {
      display: flex;
      align-items: center;
      gap: 0.75rem;
      font-weight: 700;
      font-size: 1.25rem;
      color: var(--primary);
    }
    .nav-links {
      display: flex;
      gap: 1.5rem;
    }
    .nav-links a {
      color: var(--text-muted);
      text-decoration: none;
      font-weight: 600;
      transition: color 0.2s;
      position: relative;
    }
    .nav-links a.active {
      color: var(--text-main);
    }
    .nav-links a.active::after {
      content: '';
      position: absolute;
      bottom: -4px;
      left: 0;
      width: 100%;
      height: 2px;
      background: var(--primary);
      border-radius: 2px;
    }
    .nav-links a:hover {
      color: var(--text-main);
    }
  `]
})
export class NavigationComponent {}
