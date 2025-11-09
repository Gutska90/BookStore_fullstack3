import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book, PageResponse } from '../models/book.model';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private apiUrl = 'http://localhost:8080/api/libros';

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  /**
   * GET: Obtener todos los libros (con paginación)
   */
  getAllBooks(): Observable<PageResponse<Book>> {
    return this.http.get<PageResponse<Book>>(this.apiUrl);
  }

  /**
   * GET: Obtener todos los libros sin paginación
   */
  getAllBooksSimple(): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.apiUrl}/all`);
  }

  /**
   * GET: Obtener un libro por ID
   */
  getBookById(id: number): Observable<Book> {
    return this.http.get<Book>(`${this.apiUrl}/${id}`);
  }

  /**
   * POST: Crear un nuevo libro
   */
  createBook(book: Book): Observable<Book> {
    return this.http.post<Book>(this.apiUrl, book, this.httpOptions);
  }

  /**
   * PUT: Actualizar un libro existente
   */
  updateBook(id: number, book: Book): Observable<Book> {
    return this.http.put<Book>(`${this.apiUrl}/${id}`, book, this.httpOptions);
  }

  /**
   * DELETE: Eliminar un libro por ID
   */
  deleteBook(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  /**
   * GET: Buscar libros por autor
   */
  searchByAutor(autor: string): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.apiUrl}/autor/${autor}/all`);
  }

  /**
   * GET: Buscar libros por género
   */
  searchByGenero(genero: string): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.apiUrl}/genero/${genero}/all`);
  }
}

