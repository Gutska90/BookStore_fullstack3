import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { LaboratorioService } from '../../services/laboratorio.service';
import { Laboratorio, LaboratorioRequest } from '../../models/laboratorio.model';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-laboratorios',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './laboratorios.component.html',
  styleUrls: ['./laboratorios.component.css']
})
export class LaboratoriosComponent implements OnInit {
  laboratorios: Laboratorio[] = []; // Array para manipulación
  laboratorioForm: FormGroup;
  editingId: number | null = null;
  error: string = '';
  success: string = '';
  loading: boolean = false;
  showForm: boolean = false;

  constructor(
    private laboratorioService: LaboratorioService,
    private authService: AuthService,
    private fb: FormBuilder
  ) {
    this.laboratorioForm = this.fb.group({
      nombre: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
      direccion: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(200)]],
      telefono: ['', [Validators.required, Validators.pattern(/^[0-9+\-() ]+$/)]],
      tipo: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.cargarLaboratorios();
  }

  cargarLaboratorios(): void {
    this.loading = true;
    // Usar array local (datos inmediatos)
    this.laboratorios = this.laboratorioService.getLaboratoriosLocal();
    // Recargar desde backend y esperar a que termine
    this.laboratorioService.cargarLaboratorios().subscribe({
      next: (laboratorios) => {
        this.laboratorios = laboratorios;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error al cargar laboratorios:', err);
        this.loading = false;
      }
    });
  }

  toggleForm(): void {
    this.showForm = !this.showForm;
    if (!this.showForm) {
      this.editingId = null;
      this.laboratorioForm.reset();
    }
  }

  editarLaboratorio(laboratorio: Laboratorio): void {
    this.editingId = laboratorio.id!;
    this.showForm = true;
    this.laboratorioForm.patchValue(laboratorio);
  }

  eliminarLaboratorio(id: number): void {
    const laboratorio = this.laboratorios.find(l => l.id === id);
    const nombre = laboratorio?.nombre || 'este laboratorio';
    
    if (confirm(`¿Estás seguro de que deseas eliminar "${nombre}"?\n\nEsta acción no se puede deshacer.`)) {
      this.loading = true;
      this.error = '';
      this.success = '';
      
      this.laboratorioService.deleteLaboratorio(id).subscribe({
        next: () => {
          this.loading = false;
          this.cargarLaboratorios();
          this.success = `Laboratorio "${nombre}" eliminado exitosamente`;
          setTimeout(() => this.success = '', 5000);
        },
        error: (err) => {
          this.loading = false;
          this.error = err.error?.mensaje || 'Error al eliminar el laboratorio. Intenta nuevamente.';
          setTimeout(() => this.error = '', 5000);
        }
      });
    }
  }

  onSubmit(): void {
    if (this.laboratorioForm.valid) {
      this.loading = true;
      this.error = '';
      this.success = '';

      const request: LaboratorioRequest = this.laboratorioForm.value;

      if (this.editingId) {
        this.laboratorioService.updateLaboratorio(this.editingId, request).subscribe({
          next: () => {
            this.loading = false;
            this.success = 'Laboratorio actualizado exitosamente';
            setTimeout(() => {
              this.toggleForm();
              this.cargarLaboratorios();
            }, 1500);
          },
          error: (err) => {
            this.loading = false;
            this.error = err.error?.mensaje || 'Error al actualizar el laboratorio. Verifica los datos e intenta nuevamente.';
            setTimeout(() => this.error = '', 5000);
          }
        });
      } else {
        this.laboratorioService.createLaboratorio(request).subscribe({
          next: () => {
            this.loading = false;
            this.success = 'Laboratorio creado exitosamente';
            setTimeout(() => {
              this.toggleForm();
              this.cargarLaboratorios();
            }, 1500);
          },
          error: (err) => {
            this.loading = false;
            this.error = err.error?.mensaje || 'Error al crear el laboratorio. Verifica los datos e intenta nuevamente.';
            setTimeout(() => this.error = '', 5000);
          }
        });
      }
    }
  }

  isAdmin(): boolean {
    return this.authService.hasRole('ADMINISTRADOR');
  }
}
