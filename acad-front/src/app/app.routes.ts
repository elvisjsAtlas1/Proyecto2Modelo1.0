import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./category/dashboard/dashboard.component').then((m) => m.DashboardComponent),
    children: [
      {
        path: 'apoderado',
        loadComponent: () =>
          import('./category/apoderado/apoderado').then((m) => m.Apoderado),
      },
      {
        path: 'apoderado/editar/:id',
        loadComponent: () =>
          import('./category/apoderado/edit/edit-apoderado.component')
            .then((m) => m.EditApoderadoComponent),
      },
      {
        path: 'apoderado/nuevo',
        loadComponent: () =>
          import('./category/apoderado/new/new-apoderado.component')
            .then(m => m.NewApoderadoComponent)
      },

      {
        path: 'antecedentes-medicos',
        loadComponent: () =>
          import('./category/antecedenteMedico/antecedenteMedico')
            .then(m => m.AntecedenteMedico),
      },
      {
        path: 'antecedentes-medicos/editar/:id',
        loadComponent: () =>
          import('./category/antecedenteMedico/edit/edit-antecedente-medico.component')
            .then(m => m.EditAntecedenteMedicoComponent),
      },
      {
        path: 'antecedentes-medicos/nuevo',
        loadComponent: () =>
          import('./category/antecedenteMedico/new/new-antecedente-medico.component')
            .then(m => m.NewAntecedenteMedicoComponent),
      },

      {
        path: 'requisitos',
        loadComponent: () =>
          import('./category/requisito/requisito').then(m => m.Requisito),
      },
      {
        path: 'requisitos/editar/:id',
        loadComponent: () =>
          import('./category/requisito/edit/edit-requisito.component')
            .then(m => m.EditRequisitoComponent),
      },
      {
        path: 'requisitos/nuevo',
        loadComponent: () =>
          import('./category/requisito/new/new-requisito.component')
            .then(m => m.NewRequisitoComponent),
      },

      {
        path: 'matriculas',
        loadComponent: () =>
          import('./category/matricula/matricula').then(m => m.Matricula),
      },
      {
        path: 'matriculas/editar/:id',
        loadComponent: () =>
          import('./category/matricula/edit/edit-matricula.component')
            .then(m => m.EditMatriculaComponent),
      },
      {
        path: 'matriculas/nuevo',
        loadComponent: () =>
          import('./category/matricula/new/new-matricula.component')
            .then(m => m.NewMatriculaComponent),
      },

      {
        path: '',
        redirectTo: 'apoderado',
        pathMatch: 'full',
      }
    ]
  }
];
