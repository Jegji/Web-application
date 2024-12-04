import { Component } from '@angular/core';
import {FilamentsListComponent} from '../components/filaments-list/filaments-list.component';
import {MaterialsListComponent} from '../components/materials-list/materials-list.component';

@Component({
  selector: 'app-home',
  imports: [FilamentsListComponent, MaterialsListComponent],
  templateUrl: './home.component.html',
  standalone: true,
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
