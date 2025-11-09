export interface Book {
  id?: number;
  titulo: string;
  autor: string;
  anioPublicacion: number;
  genero: string;
  fechaCreacion?: string;
  fechaActualizacion?: string;
  version?: number;
}

export interface PageResponse<T> {
  content: T[];
  pageable: any;
  totalPages: number;
  totalElements: number;
  last: boolean;
  first: boolean;
  numberOfElements: number;
  size: number;
  number: number;
  empty: boolean;
}

