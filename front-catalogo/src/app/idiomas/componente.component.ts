import { Component, forwardRef, Input, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { IdiomasViewModelService } from './servicio.service';

import { FormsModule } from '@angular/forms';
import { TypeValidator, ErrorMessagePipe } from '@my/core';
import { ActivatedRoute, ParamMap, Router, RouterLink } from '@angular/router';
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-idiomas',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./componente.component.css'],
  imports: [
  forwardRef(() => IdiomasAddComponent),
  forwardRef(() => IdiomasEditComponent),
  forwardRef(() => IdiomasViewComponent),
  forwardRef(() => IdiomasListComponent),
  ],
  })
  export class IdiomasComponent implements OnInit, OnDestroy {
   constructor(protected vm: IdiomasViewModelService) { }
   public get VM(): IdiomasViewModelService { return this.vm; }
   ngOnInit(): void { this.vm.list(); }
  ngOnDestroy(): void { this.vm.clear(); }
  }
  @Component({
    selector: 'app-idiomas-list',
    templateUrl: './tmpl-list.component.html',
    styleUrls: ['./componente.component.css'],
    imports: [RouterLink]
   })
   export class IdiomasListComponent implements OnInit, OnDestroy {
    constructor(protected vm: IdiomasViewModelService) { }
    public get VM(): IdiomasViewModelService { return this.vm; }
    ngOnInit(): void { this.vm.list(); }
   ngOnDestroy(): void { this.vm.clear(); }
   }
   
   
   @Component({
    selector: 'app-idiomas-add',
    templateUrl: './tmpl-form.component.html',
    styleUrls: ['./componente.component.css'],
    imports: [FormsModule, TypeValidator, ErrorMessagePipe],
    })
    export class IdiomasAddComponent implements OnInit {
    constructor(protected vm: IdiomasViewModelService) { }
    public get VM(): IdiomasViewModelService { return this.vm; }
    ngOnInit(): void {
    this.vm.add();
    }
    }
    
    @Component({
      selector: 'app-idiomas-edit',
      templateUrl: './tmpl-form.component.html',
      styleUrls: ['./componente.component.css'],
      imports: [FormsModule, TypeValidator, ErrorMessagePipe],
      })
      export class IdiomasEditComponent implements OnInit, OnDestroy {
      private obs$?: Subscription;
      constructor(protected vm: IdiomasViewModelService,
      protected route: ActivatedRoute, protected router: Router) { }
      public get VM(): IdiomasViewModelService { return this.vm; }
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
        selector: 'app-idiomas-view',
        templateUrl: './tmpl-view.component.html',
        styleUrls: ['./componente.component.css'],
        
        })
        export class IdiomasViewComponent implements OnChanges {
        @Input() id?: string;
        constructor(protected vm: IdiomasViewModelService, protected router: Router) { }
        public get VM(): IdiomasViewModelService { return this.vm; }
        ngOnChanges(changes: SimpleChanges): void {
        if (this.id) {
        this.vm.view(+this.id);
        } else {
        this.router.navigate(['/404.html']);
        }
        }
        }
   export const IDIOMAS_COMPONENTES = [
    IdiomasComponent, IdiomasListComponent, IdiomasAddComponent,
    IdiomasEditComponent, IdiomasViewComponent,
   ]