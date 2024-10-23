import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { firstValueFrom } from 'rxjs';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { RouterOutlet, RouterModule, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    MatFormField,
    MatLabel,
    RouterOutlet,
    FormsModule,
    MatInputModule,
    MatCardModule,
    MatButtonModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  async onLogin() {
    try {
      const response = await firstValueFrom(this.authService.login(this.username, this.password));

      if (response) {
        this.authService.setToken(response.token);
        this.router.navigate(['/chat']);
      } else {
        console.log('Login failed: Invalid credentials.');
      }

    } catch(error) {
      alert('Login failed.');
    }
  }
}
