import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Laboratorio, LaboratorioRequest } from '../models/laboratorio.model';

@Injectable({
  providedIn: 'root'
})
export class LaboratorioService {
  private apiUrl = 'http://localhost:8082/api/laboratorios';
  private laboratorios: Laboratorio[] = []; // Array para manipulación local

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {
    // Cargar laboratorios al inicializar el servicio
    this.cargarLaboratorios().subscribe({
      error: (err) => {
        console.error('Error al cargar laboratorios en constructor:', err);
      }
    });
  }

  // Cargar laboratorios desde el backend
  cargarLaboratorios(): Observable<Laboratorio[]> {
    return this.getAllLaboratorios().pipe(
      tap((laboratorios) => {
        this.laboratorios = laboratorios; // Almacenar en array local
      })
    );
  }

  // Obtener array local
  getLaboratoriosLocal(): Laboratorio[] {
    return this.laboratorios;
  }

  // GET: Obtener todos los laboratorios
  getAllLaboratorios(): Observable<Laboratorio[]> {
    return this.http.get<Laboratorio[]>(this.apiUrl);
  }

  // GET: Obtener laboratorios activos
  getLaboratoriosActivos(): Observable<Laboratorio[]> {
    return this.http.get<Laboratorio[]>(`${this.apiUrl}/activos`);
  }

  // GET: Obtener laboratorio por ID
  getLaboratorioById(id: number): Observable<Laboratorio> {
    return this.http.get<Laboratorio>(`${this.apiUrl}/${id}`);
  }

  // POST: Crear laboratorio
  createLaboratorio(laboratorio: LaboratorioRequest): Observable<Laboratorio> {
    return this.http.post<Laboratorio>(this.apiUrl, laboratorio, this.httpOptions).pipe(
      tap(() => {
        // Suscribirse para ejecutar la recarga
        this.cargarLaboratorios().subscribe({
          error: (err) => console.error('Error al recargar laboratorios:', err)
        });
      })
    );
  }

  // PUT: Actualizar laboratorio
  updateLaboratorio(id: number, laboratorio: LaboratorioRequest): Observable<Laboratorio> {
    return this.http.put<Laboratorio>(`${this.apiUrl}/${id}`, laboratorio, this.httpOptions).pipe(
      tap(() => {
        // Suscribirse para ejecutar la recarga
        this.cargarLaboratorios().subscribe({
          error: (err) => console.error('Error al recargar laboratorios:', err)
        });
      })
    );
  }

  // DELETE: Eliminar laboratorio
  deleteLaboratorio(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      tap(() => {
        // Suscribirse para ejecutar la recarga
        this.cargarLaboratorios().subscribe({
          error: (err) => console.error('Error al recargar laboratorios:', err)
        });
      })
    );
  }
}

