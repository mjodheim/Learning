import { Component, inject } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Tournament } from '../../../shared/models/tournament';
import { httpResource } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-tournament-details',
  imports: [],
  templateUrl: './tournament-details.html',
  styleUrl: './tournament-details.scss',
})
export class TournamentDetails {
  private readonly apiUrl = environment.apiUrl;
  private readonly route = inject(ActivatedRoute);

  readonly tournamentId = this.route.snapshot.paramMap.get('id');

  tournament = httpResource<Tournament>(() => {
    const id = this.tournamentId;
    return id ? `${this.apiUrl}/tournament/${id}` : undefined  ;
  })
}
