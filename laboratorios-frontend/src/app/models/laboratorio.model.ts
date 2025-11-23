export interface Laboratorio {
  id?: number;
  nombre: string;
  direccion: string;
  telefono: string;
  tipo: string;
  activo?: boolean;
  fechaCreacion?: string;
  fechaActualizacion?: string;
}

export interface LaboratorioRequest {
  nombre: string;
  direccion: string;
  telefono: string;
  tipo: string;
}

