import { Routes, UrlSegment } from '@angular/router';
import { HomeComponent, PageNotFoundComponent } from './main';


import { ActoresListComponent, ActoresAddComponent, ActoresEditComponent, ActoresViewComponent } from './actores';
import { IdiomasAddComponent, IdiomasEditComponent, IdiomasListComponent, IdiomasViewComponent } from './idiomas';
import { CategoriasAddComponent, CategoriasEditComponent, CategoriasListComponent, CategoriasViewComponent } from './categorias';
import { PeliculasAddComponent, PeliculasEditComponent, PeliculasListComponent, PeliculasViewComponent } from './peliculas';


export function graficoFiles(url: UrlSegment[]) {
  return url.length === 1 && url[0].path.endsWith('.svg') ? ({consumed: url}) : null;
}

export const routes: Routes = [
  {path: '', redirectTo: '/peliculas', pathMatch: 'full' },
  {path: 'inicio', component: HomeComponent, },
  {path: '404.html', component: PageNotFoundComponent},
 { path: 'actores', children: [
    { path: '', component: ActoresListComponent},
    { path: 'add', component: ActoresAddComponent},
    { path: ':id/edit', component: ActoresEditComponent},
    { path: ':id', component: ActoresViewComponent},
    { path: ':id/:kk', component: ActoresViewComponent},
    ]},
    { path: 'idiomas', children: [
      { path: '', component: IdiomasListComponent},
      { path: 'add', component: IdiomasAddComponent},
      { path: ':id/edit', component: IdiomasEditComponent},
      { path: ':id', component: IdiomasViewComponent},
      { path: ':id/:kk', component: IdiomasViewComponent},
      ]},
      { path: 'categorias', children: [
        { path: '', component: CategoriasListComponent},
        { path: 'add', component: CategoriasAddComponent},
        { path: ':id/edit', component: CategoriasEditComponent},
        { path: ':id', component: CategoriasViewComponent},
        { path: ':id/:kk', component: CategoriasViewComponent},
        ]},
        { path: 'peliculas', children: [
          { path: '', component: PeliculasListComponent},
          { path: 'add', component: PeliculasAddComponent},
          { path: ':id/edit', component: PeliculasEditComponent},
          { path: ':id', component: PeliculasViewComponent},
          { path: ':id/:kk', component: PeliculasViewComponent},
          ]},
 {path: '**', component: PageNotFoundComponent},  
];