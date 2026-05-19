import { Component } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { httpResource } from '@angular/common/http';
import { Player } from '../../../shared/models/player';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-players-list',
  imports: [TableModule],
  templateUrl: './players-list.html',
  styleUrl: './players-list.scss',
})
export class PlayersList {
  private readonly apiUrl = environment.apiUrl;
  
  players = httpResource<Player[]>(() => `${this.apiUrl}/player`);  
}
