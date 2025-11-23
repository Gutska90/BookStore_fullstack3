import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { forkJoin } from 'rxjs';
import { ResultadoService } from '../../services/resultado.service';
import { LaboratorioService } from '../../services/laboratorio.service';
import { ResultadoAnalisis, ResultadoRequest } from '../../models/resultado.model';
import { Laboratorio } from '../../models/laboratorio.model';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-resultados',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './resultados.component.html',
  styleUrls: ['./resultados.component.css']
})
export class ResultadosComponent implements OnInit {
  resultados: ResultadoAnalisis[] = []; // Array para manipulación
  laboratorios: Laboratorio[] = [];
  resultadoForm: FormGroup;
  editingId: number | null = null;
  error: string = '';
  success: string = '';
  loading: boolean = false;
  showForm: boolean = false;

  constructor(
    private resultadoService: ResultadoService,
    private laboratorioService: LaboratorioService,
    private authService: AuthService,
    private fb: FormBuilder
  ) {
    this.resultadoForm = this.fb.group({
      pacienteId: ['', [Validators.required]],
      laboratorioId: ['', [Validators.required]],
      tipoAnalisis: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
      resultado: ['', [Validators.required]],
      observaciones: [''],
      fechaAnalisis: ['']
    });
  }

  ngOnInit(): void {
    this.cargarDatos();
    const usuario = this.authService.getCurrentUser();
    if (usuario && usuario.rol === 'PACIENTE') {
      this.resultadoForm.patchValue({ pacienteId: usuario.id });
    }
  }

  cargarDatos(): void {
    this.loading = true;
    // Usar arrays locales (datos inmediatos)
    this.resultados = this.resultadoService.getResultadosLocal();
    this.laboratorios = this.laboratorioService.getLaboratoriosLocal();
    
    // Recargar desde backend y esperar a que todas las operaciones terminen
    forkJoin({
      resultados: this.resultadoService.cargarResultados(),
      laboratorios: this.laboratorioService.cargarLaboratorios()
    }).subscribe({
      next: (data) => {
        this.resultados = data.resultados;
        this.laboratorios = data.laboratorios;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error al cargar datos:', err);
        this.loading = false;
      }
    });
  }

  toggleForm(): void {
    this.showForm = !this.showForm;
    if (!this.showForm) {
      this.editingId = null;
      this.resultadoForm.reset();
    }
  }

  editarResultado(resultado: ResultadoAnalisis): void {
    this.editingId = resultado.id!;
    this.showForm = true;
    this.resultadoForm.patchValue({
      pacienteId: resultado.pacienteId,
      laboratorioId: resultado.laboratorioId,
      tipoAnalisis: resultado.tipoAnalisis,
      resultado: resultado.resultado,
      observaciones: resultado.observaciones,
      fechaAnalisis: resultado.fechaAnalisis
    });
  }

  eliminarResultado(id: number): void {
    const resultado = this.resultados.find(r => r.id === id);
    const tipo = resultado?.tipoAnalisis || 'este resultado';
    
    if (confirm(`¿Estás seguro de que deseas eliminar el resultado "${tipo}"?\n\nEsta acción no se puede deshacer.`)) {
      this.loading = true;
      this.error = '';
      this.success = '';
      
      this.resultadoService.deleteResultado(id).subscribe({
        next: () => {
          this.loading = false;
          this.cargarDatos();
          this.success = `Resultado "${tipo}" eliminado exitosamente`;
          setTimeout(() => this.success = '', 5000);
        },
        error: (err) => {
          this.loading = false;
          this.error = err.error?.mensaje || 'Error al eliminar el resultado. Intenta nuevamente.';
          setTimeout(() => this.error = '', 5000);
        }
      });
    }
  }

  onSubmit(): void {
    if (this.resultadoForm.valid) {
      this.loading = true;
      this.error = '';
      this.success = '';

      const request: ResultadoRequest = this.resultadoForm.value;

      if (this.editingId) {
        this.resultadoService.updateResultado(this.editingId, request).subscribe({
          next: () => {
            this.loading = false;
            this.success = 'Resultado actualizado exitosamente';
            setTimeout(() => {
              this.toggleForm();
              this.cargarDatos();
            }, 1500);
          },
          error: (err) => {
            this.loading = false;
            this.error = err.error?.mensaje || 'Error al actualizar el resultado. Verifica los datos e intenta nuevamente.';
            setTimeout(() => this.error = '', 5000);
          }
        });
      } else {
        this.resultadoService.createResultado(request).subscribe({
          next: () => {
            this.loading = false;
            this.success = 'Resultado creado exitosamente';
            setTimeout(() => {
              this.toggleForm();
              this.cargarDatos();
            }, 1500);
          },
          error: (err) => {
            this.loading = false;
            this.error = err.error?.mensaje || 'Error al crear el resultado. Verifica los datos e intenta nuevamente.';
            setTimeout(() => this.error = '', 5000);
          }
        });
      }
    }
  }

  isAdmin(): boolean {
    return this.authService.hasRole('ADMINISTRADOR');
  }

  isTecnico(): boolean {
    return this.authService.hasRole('TECNICO_LABORATORIO');
  }

  getResultadosFiltrados(): ResultadoAnalisis[] {
    const usuario = this.authService.getCurrentUser();
    if (usuario?.rol === 'PACIENTE') {
      return this.resultados.filter(r => r.pacienteId === usuario.id);
    }
    return this.resultados;
  }
}
