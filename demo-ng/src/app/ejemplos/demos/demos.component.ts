import { Component, computed, OnDestroy, OnInit, signal } from '@angular/core';
import { NotificationService, NotificationType } from '../../common-services';
import { Unsubscribable } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CapitalizePipe, ElipsisPipe, SizerComponent } from '@my/core';
import { FormButtonsComponent } from '../../common-components';

@Component({
  selector: 'app-demos',
  imports: [ FormsModule, CommonModule, ElipsisPipe, CapitalizePipe, SizerComponent,  FormButtonsComponent],
  templateUrl: './demos.component.html',
  styleUrl: './demos.component.css'
})
export class DemosComponent implements OnInit, OnDestroy {
 constructor(public vm :NotificationService){} 
 
private date= new Date('2025-03-31')
public readonly nombre= signal<string>('mundo')
public readonly fontSize= signal<number>(25)
public readonly list= signal([{id:1, nombre:'Madrid'},{id:2, nombre:'Oviedo'},{id:3, nombre:'Barcelona'},{id:4, nombre:'Alicante'}])

public resultado=signal<string>('')
public visible=signal<boolean>(true)
public invisible=computed<boolean>(()=>!this.visible())
public readonly estetica = signal({ importante: true, urgente: true, error: false })
public readonly idProvincia=signal<number>(2)

public get fecha():string{return this.date.toISOString();}
public set fecha(value:string){
this.date = new Date (value);
}

 
saluda(){
  this.resultado.set(`Hola ${this.nombre()}`)
}
despide(){
  this.resultado.set(`Adios ${this.nombre()}`)
}
di(algo: string){
  this.resultado.set(`Di ${algo}`)
}
cambia() {
  this.visible.update(valor => !valor);
  this.estetica.update(valor => ({ ...valor, importante: !valor.importante }));
  this.estetica.update(valor => ({ ...valor, error: !valor.error }));
}

add(provincia:string){
  const id=this.list()[this.list().length-1].id+1;
  this.list.update(valor=>[...valor,{id, nombre:provincia}]);
  this.idProvincia.set(id)
}
calcula(a:number, b:number){ return a+b}

 private suscriptor?:Unsubscribable;

 ngOnInit(): void {
  this.suscriptor = this.vm.Notificacion.subscribe({
    next: n => {
      if (n.Type !== NotificationType.error) { return; }
  
    },
    complete: () => this.suscriptor?.unsubscribe()
  });
}
    ngOnDestroy(): void {
      if (this.suscriptor) {
      this.suscriptor.unsubscribe();
      }
      }



}
