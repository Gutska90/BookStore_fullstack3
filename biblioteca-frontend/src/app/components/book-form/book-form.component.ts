import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { BookService } from '../../services/book.service';
import { Book } from '../../models/book.model';

@Component({
  selector: 'app-book-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css']
})
export class BookFormComponent implements OnInit {
  book: Book = {
    titulo: '',
    autor: '',
    anioPublicacion: new Date().getFullYear(),
    genero: ''
  };

  isEditMode: boolean = false;
  bookId: number | null = null;
  loading: boolean = false;
  error: string = '';

  constructor(
    private bookService: BookService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.bookId = +id;
      this.loadBook(this.bookId);
    }
  }

  loadBook(id: number): void {
    this.loading = true;
    this.bookService.getBookById(id).subscribe({
      next: (data) => {
        this.book = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error al cargar libro:', err);
        this.error = 'Error al cargar el libro';
        this.loading = false;
      }
    });
  }

  onSubmit(): void {
    if (!this.validateForm()) {
      return;
    }

    this.loading = true;
    this.error = '';

    if (this.isEditMode && this.bookId) {
      // Actualizar libro existente
      this.bookService.updateBook(this.bookId, this.book).subscribe({
        next: () => {
          this.router.navigate(['/']);
        },
        error: (err) => {
          console.error('Error al actualizar libro:', err);
          this.error = 'Error al actualizar el libro';
          this.loading = false;
        }
      });
    } else {
      // Crear nuevo libro
      this.bookService.createBook(this.book).subscribe({
        next: () => {
          this.router.navigate(['/']);
        },
        error: (err) => {
          console.error('Error al crear libro:', err);
          this.error = 'Error al crear el libro';
          this.loading = false;
        }
      });
    }
  }

  validateForm(): boolean {
    if (!this.book.titulo || this.book.titulo.trim() === '') {
      this.error = 'El título es obligatorio';
      return false;
    }
    if (!this.book.autor || this.book.autor.trim() === '') {
      this.error = 'El autor es obligatorio';
      return false;
    }
    if (!this.book.anioPublicacion || this.book.anioPublicacion < 1000) {
      this.error = 'El año de publicación debe ser mayor a 1000';
      return false;
    }
    if (!this.book.genero || this.book.genero.trim() === '') {
      this.error = 'El género es obligatorio';
      return false;
    }
    return true;
  }

  cancel(): void {
    this.router.navigate(['/']);
  }
}

