import { Component } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { httpResource } from '@angular/common/http';
import { Tournament } from '../../../shared/models/tournament';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-tournaments-list',
  imports: [TableModule],
  templateUrl: './tournaments-list.html',
  styleUrl: './tournaments-list.scss',
})
export class TournamentsList {
  private readonly apiUrl = environment.apiUrl;
  tournaments = httpResource<Tournament[]>(() => `${this.apiUrl}/tournament`);
}
