import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { firstValueFrom } from 'rxjs';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    MatFormField,
    MatLabel,
    RouterOutlet,
    FormsModule,
    MatInputModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService) {}

  async onLogin() {
    try {
      const response = await firstValueFrom(this.authService.login(this.username, this.password));
      console.log('Login successful', response)
    } catch(error) {
      console.error('Login failed.', error);
    }
  }
}
