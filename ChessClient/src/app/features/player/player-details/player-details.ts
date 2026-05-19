import { Component, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { environment } from '../../../../environments/environment.development';
import { Player } from '../../../shared/models/player';
import { httpResource } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-player-details',
  imports: [CommonModule],
  templateUrl: './player-details.html',
  styleUrl: './player-details.scss',
})
export class PlayerDetails {
  private readonly apiUrl = environment.apiUrl;
  private readonly route = inject(ActivatedRoute);

  private readonly playerId = this.route.snapshot.paramMap.get('id');

  player = httpResource<Player>(() => {
    const id = this.playerId;

    return id ? `${this.apiUrl}/player/${id}` : undefined;
  });
}