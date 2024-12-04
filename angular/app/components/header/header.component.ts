import {Component, signal} from '@angular/core';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.component.html',
  standalone: true,
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  title = signal('Internet Services Architecture');
  subtitle = signal('Materials and Filaments');
}
