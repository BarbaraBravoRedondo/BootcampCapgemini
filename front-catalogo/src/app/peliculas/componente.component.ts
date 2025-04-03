import { Component, forwardRef, Input, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { PeliculasViewModelService } from './servicio.service';
import { FormsModule } from '@angular/forms';
import { TypeValidator, ErrorMessagePipe } from '@my/core';
import { ActivatedRoute, ParamMap, Router, RouterLink } from '@angular/router';
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-peliculas',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./componente.component.css'],
  imports: [
  forwardRef(() => PeliculasAddComponent),
  forwardRef(() => PeliculasEditComponent),
  forwardRef(() => PeliculasViewComponent),
  forwardRef(() => PeliculasListComponent),
  ],
  })
  export class PeliculasComponent implements OnInit, OnDestroy {
   constructor(protected vm: PeliculasViewModelService) { }
   public get VM(): PeliculasViewModelService { return this.vm; }
   ngOnInit(): void { this.vm.list(); }
  ngOnDestroy(): void { this.vm.clear(); }
  }
  @Component({
    selector: 'app-peliculas-list',
    templateUrl: './tmpl-list.component.html',
    styleUrls: ['./componente.component.css'],
    imports: [RouterLink]
   })
   export class PeliculasListComponent implements OnInit, OnDestroy {
    constructor(protected vm: PeliculasViewModelService) { }
    public get VM(): PeliculasViewModelService { return this.vm; }
    ngOnInit(): void { this.vm.list(); }
   ngOnDestroy(): void { this.vm.clear(); }
   }
   
   
   @Component({
    selector: 'app-peliculas-add',
    templateUrl: './tmpl-form.component.html',
    styleUrls: ['./componente.component.css'],
    imports: [FormsModule, TypeValidator, ErrorMessagePipe],
    })
    export class PeliculasAddComponent implements OnInit {
    constructor(protected vm: PeliculasViewModelService) { }
    public get VM(): PeliculasViewModelService { return this.vm; }
    ngOnInit(): void {
    this.vm.add();
    }
    }
    
    @Component({
      selector: 'app-peliculas-edit',
      templateUrl: './tmpl-form.component.html',
      styleUrls: ['./componente.component.css'],
      imports: [FormsModule, TypeValidator, ErrorMessagePipe],
      })
      export class PeliculasEditComponent implements OnInit, OnDestroy {
      private obs$?: Subscription;
      constructor(protected vm: PeliculasViewModelService,
      protected route: ActivatedRoute, protected router: Router) { }
      public get VM(): PeliculasViewModelService { return this.vm; }
      ngOnInit(): void {
      this.obs$ = this.route.paramMap.subscribe(
      (params: ParamMap) => {
      const id = parseInt(params?.get('id') ?? '');
      if (id) {
      this.vm.edit(id);
      } else {
      this.router.navigate(['/404.html']);
      }
      });
      }
      ngOnDestroy(): void {
      this.obs$!.unsubscribe();
      }
      }
      
      @Component({
        selector: 'app-peliculas-view',
        templateUrl: './tmpl-view.component.html',
        styleUrls: ['./componente.component.css'],
  
        })
        export class PeliculasViewComponent implements OnChanges {
        @Input() id?: string;
        constructor(protected vm: PeliculasViewModelService, protected router: Router) { }
        public get VM(): PeliculasViewModelService { return this.vm; }
        ngOnChanges(changes: SimpleChanges): void {
        if (this.id) {
        this.vm.view(+this.id);
        } else {
        this.router.navigate(['/404.html']);
        }
        }
        }
   export const PELICULAS_COMPONENTES = [
    PeliculasComponent, PeliculasListComponent, PeliculasAddComponent,
    PeliculasEditComponent, PeliculasViewComponent,
   ]