import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  error: string = '';
  loading: boolean = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      this.loading = true;
      this.error = '';
      
      this.authService.login(this.loginForm.value).subscribe({
        next: (response) => {
          this.loading = false;
          // Redirigir según el rol
          const rol = response.usuario.rol;
          if (rol === 'ADMINISTRADOR') {
            this.router.navigate(['/dashboard']);
          } else if (rol === 'PACIENTE') {
            this.router.navigate(['/resultados']);
          } else {
            this.router.navigate(['/resultados']);
          }
        },
        error: (err) => {
          this.loading = false;
          this.error = err.error?.mensaje || 'Error al iniciar sesión. Verifica tus credenciales.';
        }
      });
    }
  }
}
