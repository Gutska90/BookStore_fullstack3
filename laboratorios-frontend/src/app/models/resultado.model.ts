export interface ResultadoAnalisis {
  id?: number;
  pacienteId: number;
  laboratorioId: number;
  tipoAnalisis: string;
  resultado: string;
  observaciones?: string;
  fechaAnalisis: string;
  fechaCreacion?: string;
  fechaActualizacion?: string;
}

export interface ResultadoRequest {
  pacienteId: number;
  laboratorioId: number;
  tipoAnalisis: string;
  resultado: string;
  observaciones?: string;
  fechaAnalisis?: string;
}

