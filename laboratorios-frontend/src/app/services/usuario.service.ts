import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Usuario, UsuarioRequest } from '../models/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private apiUrl = 'http://localhost:8081/api/usuarios';
  private usuarios: Usuario[] = []; // Array para manipulación local

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {
    // Cargar usuarios al inicializar el servicio
    this.cargarUsuarios().subscribe({
      error: (err) => {
        console.error('Error al cargar usuarios en constructor:', err);
      }
    });
  }

  // Cargar usuarios desde el backend
  cargarUsuarios(): Observable<Usuario[]> {
    return this.getAllUsuarios().pipe(
      tap((usuarios) => {
        this.usuarios = usuarios; // Almacenar en array local
      })
    );
  }

  // Obtener array local
  getUsuariosLocal(): Usuario[] {
    return this.usuarios;
  }

  // GET: Obtener todos los usuarios
  getAllUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(this.apiUrl);
  }

  // GET: Obtener usuario por ID
  getUsuarioById(id: number): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.apiUrl}/${id}`);
  }

  // POST: Crear usuario
  createUsuario(usuario: UsuarioRequest): Observable<Usuario> {
    return this.http.post<Usuario>(this.apiUrl, usuario, this.httpOptions).pipe(
      tap(() => {
        // Suscribirse para ejecutar la recarga
        this.cargarUsuarios().subscribe({
          error: (err) => console.error('Error al recargar usuarios:', err)
        });
      })
    );
  }

  // PUT: Actualizar usuario
  updateUsuario(id: number, usuario: UsuarioRequest): Observable<Usuario> {
    return this.http.put<Usuario>(`${this.apiUrl}/${id}`, usuario, this.httpOptions).pipe(
      tap(() => {
        // Suscribirse para ejecutar la recarga
        this.cargarUsuarios().subscribe({
          error: (err) => console.error('Error al recargar usuarios:', err)
        });
      })
    );
  }

  // DELETE: Eliminar usuario
  deleteUsuario(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      tap(() => {
        // Suscribirse para ejecutar la recarga
        this.cargarUsuarios().subscribe({
          error: (err) => console.error('Error al recargar usuarios:', err)
        });
      })
    );
  }
}

