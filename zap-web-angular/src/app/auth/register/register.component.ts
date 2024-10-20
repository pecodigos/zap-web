import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { firstValueFrom } from 'rxjs';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-register',
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
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  name: string = '';
  username: string = '';
  email: string = '';
  password: string = '';

  constructor(private authService: AuthService) {}

  async onRegister() {
    try {
      // Call the registration service method
      const response = await firstValueFrom(
        this.authService.register(this.name, this.username, this.email, this.password)
      );
      console.log('Registration successful', response);
    } catch (error) {
      console.error('Registration failed', error);
    }
  }
}
