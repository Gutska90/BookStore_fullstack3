import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from '../../services/auth.service';
import { UsuarioService } from '../../services/usuario.service';
import { Usuario, Rol } from '../../models/usuario.model';
import { passwordValidator } from '../../validators/password.validator';

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit, OnDestroy {
  perfilForm: FormGroup;
  usuario: Usuario | null = null;
  error: string = '';
  success: string = '';
  loading: boolean = false;
  passwordErrors: any = {};
  showPasswordFields: boolean = false;
  private passwordSubscription?: Subscription;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private usuarioService: UsuarioService,
    private router: Router
  ) {
    this.perfilForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      nombre: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      apellido: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      password: [''],
      confirmPassword: ['']
    });
  }

  ngOnInit(): void {
    this.usuario = this.authService.getCurrentUser();
    if (!this.usuario) {
      this.router.navigate(['/login']);
      return;
    }

    this.perfilForm.patchValue({
      email: this.usuario.email,
      nombre: this.usuario.nombre,
      apellido: this.usuario.apellido
    });

    // Agregar validación de contraseña solo si se muestra
    const passwordControl = this.perfilForm.get('password');
    if (passwordControl) {
      this.passwordSubscription = passwordControl.valueChanges.subscribe(() => {
        if (this.showPasswordFields) {
          passwordControl.setValidators([passwordValidator()]);
          this.passwordErrors = passwordControl.errors || {};
        }
      });
    }
  }

  ngOnDestroy(): void {
    // Desuscribirse para evitar memory leaks
    if (this.passwordSubscription) {
      this.passwordSubscription.unsubscribe();
    }
  }

  togglePasswordFields(): void {
    this.showPasswordFields = !this.showPasswordFields;
    if (this.showPasswordFields) {
      this.perfilForm.get('password')?.setValidators([passwordValidator()]);
      this.perfilForm.get('confirmPassword')?.setValidators([Validators.required]);
    } else {
      this.perfilForm.get('password')?.clearValidators();
      this.perfilForm.get('confirmPassword')?.clearValidators();
      this.perfilForm.get('password')?.setValue('');
      this.perfilForm.get('confirmPassword')?.setValue('');
    }
    this.perfilForm.get('password')?.updateValueAndValidity();
    this.perfilForm.get('confirmPassword')?.updateValueAndValidity();
  }

  onSubmit(): void {
    if (this.perfilForm.valid && this.usuario) {
      this.loading = true;
      this.error = '';
      this.success = '';

      const formValue = this.perfilForm.value;
      const password = this.showPasswordFields && formValue.password ? formValue.password : null;

      if (this.showPasswordFields && formValue.password !== formValue.confirmPassword) {
        this.error = 'Las contraseñas no coinciden';
        this.loading = false;
        return;
      }

      const usuarioRequest = {
        email: formValue.email,
        password: password || '',
        nombre: formValue.nombre,
        apellido: formValue.apellido,
        rol: this.usuario.rol
      };

      this.usuarioService.updateUsuario(this.usuario.id!, usuarioRequest).subscribe({
        next: (usuarioActualizado) => {
          this.loading = false;
          this.success = 'Perfil actualizado exitosamente';
          this.authService.logout();
          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 2000);
        },
        error: (err) => {
          this.loading = false;
          this.error = err.error?.mensaje || 'Error al actualizar el perfil. Intenta nuevamente.';
        }
      });
    }
  }
}
