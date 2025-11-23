export enum Rol {
  ADMINISTRADOR = 'ADMINISTRADOR',
  PACIENTE = 'PACIENTE',
  TECNICO_LABORATORIO = 'TECNICO_LABORATORIO'
}

export interface Usuario {
  id?: number;
  email: string;
  nombre: string;
  apellido: string;
  rol: Rol;
  activo?: boolean;
  fechaCreacion?: string;
  fechaActualizacion?: string;
}

export interface UsuarioRequest {
  email: string;
  password: string;
  nombre: string;
  apellido: string;
  rol: Rol;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  usuario: Usuario;
}

