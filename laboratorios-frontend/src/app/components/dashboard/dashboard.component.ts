import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { forkJoin } from 'rxjs';
import { AuthService } from '../../services/auth.service';
import { UsuarioService } from '../../services/usuario.service';
import { LaboratorioService } from '../../services/laboratorio.service';
import { ResultadoService } from '../../services/resultado.service';
import { Usuario } from '../../models/usuario.model';
import { Laboratorio } from '../../models/laboratorio.model';
import { ResultadoAnalisis } from '../../models/resultado.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  usuario: Usuario | null = null;
  usuarios: Usuario[] = [];
  laboratorios: Laboratorio[] = [];
  resultados: ResultadoAnalisis[] = [];
  loading: boolean = false;

  constructor(
    private authService: AuthService,
    private usuarioService: UsuarioService,
    private laboratorioService: LaboratorioService,
    private resultadoService: ResultadoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.usuario = this.authService.getCurrentUser();
    this.cargarDatos();
  }

  cargarDatos(): void {
    this.loading = true;
    
    // Cargar usando arrays/listas (datos locales inmediatos)
    this.usuarios = this.usuarioService.getUsuariosLocal();
    this.laboratorios = this.laboratorioService.getLaboratoriosLocal();
    this.resultados = this.resultadoService.getResultadosLocal();
    
    // Recargar desde backend y esperar a que todas las operaciones terminen
    forkJoin({
      usuarios: this.usuarioService.cargarUsuarios(),
      laboratorios: this.laboratorioService.cargarLaboratorios(),
      resultados: this.resultadoService.cargarResultados()
    }).subscribe({
      next: (data) => {
        this.usuarios = data.usuarios;
        this.laboratorios = data.laboratorios;
        this.resultados = data.resultados;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error al cargar datos:', err);
        this.loading = false;
      }
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
