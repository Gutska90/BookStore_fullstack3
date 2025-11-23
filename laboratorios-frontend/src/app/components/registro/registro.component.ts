import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';
import { Rol } from '../../models/usuario.model';
import { passwordValidator } from '../../validators/password.validator';

@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent {
  registroForm: FormGroup;
  error: string = '';
  success: string = '';
  loading: boolean = false;
  roles = Object.values(Rol);
  passwordErrors: any = {};

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private router: Router
  ) {
    this.registroForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, passwordValidator()]],
      confirmPassword: ['', [Validators.required]],
      nombre: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      apellido: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      rol: [Rol.PACIENTE, [Validators.required]]
    }, { validators: this.passwordMatchValidator });
  }

  passwordMatchValidator(group: FormGroup) {
    const password = group.get('password')?.value;
    const confirmPassword = group.get('confirmPassword')?.value;
    return password === confirmPassword ? null : { passwordMismatch: true };
  }

  ngOnInit(): void {
    // Observar cambios en la contraseña para mostrar errores
    this.registroForm.get('password')?.valueChanges.subscribe(() => {
      const passwordControl = this.registroForm.get('password');
      if (passwordControl) {
        this.passwordErrors = passwordControl.errors || {};
      }
    });
  }

  onSubmit(): void {
    if (this.registroForm.valid) {
      this.loading = true;
      this.error = '';
      this.success = '';

      const formValue = this.registroForm.value;
      const usuarioRequest = {
        email: formValue.email,
        password: formValue.password,
        nombre: formValue.nombre,
        apellido: formValue.apellido,
        rol: formValue.rol
      };

      this.usuarioService.createUsuario(usuarioRequest).subscribe({
        next: () => {
          this.loading = false;
          this.success = 'Usuario registrado exitosamente. Redirigiendo al login...';
          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 2000);
        },
        error: (err) => {
          this.loading = false;
          this.error = err.error?.mensaje || 'Error al registrar usuario. Intenta nuevamente.';
        }
      });
    }
  }
}
