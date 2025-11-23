import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-recuperar-password',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './recuperar-password.component.html',
  styleUrls: ['./recuperar-password.component.css']
})
export class RecuperarPasswordComponent {
  recuperarForm: FormGroup;
  message: string = '';
  success: boolean = false;

  constructor(private fb: FormBuilder) {
    this.recuperarForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]]
    });
  }

  onSubmit(): void {
    if (this.recuperarForm.valid) {
      // Simulación de recuperación de contraseña
      // En producción, esto llamaría al backend
      this.success = true;
      this.message = 'Se ha enviado un enlace de recuperación a tu email.';
    }
  }
}
