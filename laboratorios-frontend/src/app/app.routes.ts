import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistroComponent } from './components/registro/registro.component';
import { RecuperarPasswordComponent } from './components/recuperar-password/recuperar-password.component';
import { PerfilComponent } from './components/perfil/perfil.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LaboratoriosComponent } from './components/laboratorios/laboratorios.component';
import { ResultadosComponent } from './components/resultados/resultados.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'recuperar-password', component: RecuperarPasswordComponent },
  { path: 'dashboard', component: DashboardComponent, canActivate: [authGuard] },
  { path: 'perfil', component: PerfilComponent, canActivate: [authGuard] },
  { path: 'laboratorios', component: LaboratoriosComponent, canActivate: [authGuard] },
  { path: 'resultados', component: ResultadosComponent, canActivate: [authGuard] },
  { path: '**', redirectTo: '/login' }
];
