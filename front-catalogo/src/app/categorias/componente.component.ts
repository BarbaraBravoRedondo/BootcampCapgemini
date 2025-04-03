import { Component, forwardRef, Input, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { CategoriasViewModelService } from './servicio.service';

import { FormsModule } from '@angular/forms';
import { TypeValidator, ErrorMessagePipe } from '@my/core';
import { ActivatedRoute, ParamMap, Router, RouterLink } from '@angular/router';
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-categorias',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./componente.component.css'],
  imports: [
  forwardRef(() => CategoriasAddComponent),
  forwardRef(() => CategoriasEditComponent),
  forwardRef(() => CategoriasViewComponent),
  forwardRef(() => CategoriasListComponent),
  ],
  })
  export class CategoriasComponent implements OnInit, OnDestroy {
   constructor(protected vm: CategoriasViewModelService) { }
   public get VM(): CategoriasViewModelService { return this.vm; }
   ngOnInit(): void { this.vm.list(); }
  ngOnDestroy(): void { this.vm.clear(); }
  }
  @Component({
    selector: 'app-categorias-list',
    templateUrl: './tmpl-list.component.html',
    styleUrls: ['./componente.component.css'],
    imports: [RouterLink]
   })
   export class CategoriasListComponent implements OnInit, OnDestroy {
    constructor(protected vm: CategoriasViewModelService) { }
    public get VM(): CategoriasViewModelService { return this.vm; }
    ngOnInit(): void { this.vm.list(); }
   ngOnDestroy(): void { this.vm.clear(); }
   }
   
   
   @Component({
    selector: 'app-categorias-add',
    templateUrl: './tmpl-form.component.html',
    styleUrls: ['./componente.component.css'],
    imports: [FormsModule, TypeValidator, ErrorMessagePipe],
    })
    export class CategoriasAddComponent implements OnInit {
    constructor(protected vm: CategoriasViewModelService) { }
    public get VM(): CategoriasViewModelService { return this.vm; }
    ngOnInit(): void {
    this.vm.add();
    }
    }
    
    @Component({
      selector: 'app-categorias-edit',
      templateUrl: './tmpl-form.component.html',
      styleUrls: ['./componente.component.css'],
      imports: [FormsModule, TypeValidator, ErrorMessagePipe],
      })
      export class CategoriasEditComponent implements OnInit, OnDestroy {
      private obs$?: Subscription;
      constructor(protected vm: CategoriasViewModelService,
      protected route: ActivatedRoute, protected router: Router) { }
      public get VM(): CategoriasViewModelService { return this.vm; }
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
        selector: 'app-categorias-view',
        templateUrl: './tmpl-view.component.html',
        styleUrls: ['./componente.component.css'],
     
        })
        export class CategoriasViewComponent implements OnChanges {
        @Input() id?: string;
        constructor(protected vm: CategoriasViewModelService, protected router: Router) { }
        public get VM(): CategoriasViewModelService { return this.vm; }
        ngOnChanges(changes: SimpleChanges): void {
        if (this.id) {
        this.vm.view(+this.id);
        } else {
        this.router.navigate(['/404.html']);
        }
        }
        }
   export const CATEGORIAS_COMPONENTES = [
    CategoriasComponent, CategoriasListComponent, CategoriasAddComponent,
    CategoriasEditComponent, CategoriasViewComponent,
   ]