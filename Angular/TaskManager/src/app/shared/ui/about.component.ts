import { Component } from '@angular/core';

@Component({
  selector: 'app-about',
  standalone: true,
  template: `
    <div class="glass animate-fade-in about-container">
      <h1>À Propos</h1>
      <p>Cette application est un exercice Angular conçu pour démontrer :</p>
      <ul>
        <li>L'utilisation de composants Standalone</li>
        <li>La consommation d'une API REST (JSONPlaceholder)</li>
        <li>La gestion d'état simple avec RxJS</li>
        <li>Un design moderne avec Vanilla CSS</li>
      </ul>
      <p>Réalisé avec ❤️ par Antigravity.</p>
    </div>
  `,
  styles: [`
    .about-container {
      padding: 3rem;
      line-height: 1.6;
    }
    h1 {
      margin-bottom: 1.5rem;
      color: var(--primary);
    }
    ul {
      margin: 1.5rem 0;
      padding-left: 1.5rem;
    }
    li {
      margin-bottom: 0.5rem;
    }
  `]
})
export class AboutComponent {}
