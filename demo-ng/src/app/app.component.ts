import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoggerService } from '@my/core';
import {  NotificationModalComponent } from './main';
import { DemosComponent } from './demos/demos.component';

@Component({
  selector: 'app-root',
  imports: [CommonModule, RouterOutlet, NotificationModalComponent, DemosComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title : string = 'world'

/*  constructor(out:LoggerService){
    out.error('Es un error');
    out.warn('Es un warn')
    out.info('Es una info')
    out.log('Es un log')


  }*/

}