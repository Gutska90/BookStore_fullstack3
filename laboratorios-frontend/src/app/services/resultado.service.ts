import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { ResultadoAnalisis, ResultadoRequest } from '../models/resultado.model';

@Injectable({
  providedIn: 'root'
})
export class ResultadoService {
  private apiUrl = 'http://localhost:8083/api/resultados';
  private resultados: ResultadoAnalisis[] = []; // Array para manipulación local

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {
    // Cargar resultados al inicializar el servicio
    this.cargarResultados().subscribe({
      error: (err) => {
        console.error('Error al cargar resultados en constructor:', err);
      }
    });
  }

  // Cargar resultados desde el backend
  cargarResultados(): Observable<ResultadoAnalisis[]> {
    return this.getAllResultados().pipe(
      tap((resultados) => {
        this.resultados = resultados; // Almacenar en array local
      })
    );
  }

  // Obtener array local
  getResultadosLocal(): ResultadoAnalisis[] {
    return this.resultados;
  }

  // GET: Obtener todos los resultados
  getAllResultados(): Observable<ResultadoAnalisis[]> {
    return this.http.get<ResultadoAnalisis[]>(this.apiUrl);
  }

  // GET: Obtener resultado por ID
  getResultadoById(id: number): Observable<ResultadoAnalisis> {
    return this.http.get<ResultadoAnalisis>(`${this.apiUrl}/${id}`);
  }

  // GET: Obtener resultados por paciente
  getResultadosPorPaciente(pacienteId: number): Observable<ResultadoAnalisis[]> {
    return this.http.get<ResultadoAnalisis[]>(`${this.apiUrl}/paciente/${pacienteId}`);
  }

  // GET: Obtener resultados por laboratorio
  getResultadosPorLaboratorio(laboratorioId: number): Observable<ResultadoAnalisis[]> {
    return this.http.get<ResultadoAnalisis[]>(`${this.apiUrl}/laboratorio/${laboratorioId}`);
  }

  // POST: Crear resultado
  createResultado(resultado: ResultadoRequest): Observable<ResultadoAnalisis> {
    return this.http.post<ResultadoAnalisis>(this.apiUrl, resultado, this.httpOptions).pipe(
      tap(() => {
        // Suscribirse para ejecutar la recarga
        this.cargarResultados().subscribe({
          error: (err) => console.error('Error al recargar resultados:', err)
        });
      })
    );
  }

  // PUT: Actualizar resultado
  updateResultado(id: number, resultado: ResultadoRequest): Observable<ResultadoAnalisis> {
    return this.http.put<ResultadoAnalisis>(`${this.apiUrl}/${id}`, resultado, this.httpOptions).pipe(
      tap(() => {
        // Suscribirse para ejecutar la recarga
        this.cargarResultados().subscribe({
          error: (err) => console.error('Error al recargar resultados:', err)
        });
      })
    );
  }

  // DELETE: Eliminar resultado
  deleteResultado(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      tap(() => {
        // Suscribirse para ejecutar la recarga
        this.cargarResultados().subscribe({
          error: (err) => console.error('Error al recargar resultados:', err)
        });
      })
    );
  }
}

