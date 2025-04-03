
import { I18nSelectPipe } from '@angular/common';
import { Component } from '@angular/core';
import { NotificationService } from '../../common-services/notification.service';

@Component({
  selector: 'app-notification',
  imports: [I18nSelectPipe],
  templateUrl: './notification.component.html',
  styleUrl: './notification.component.css'
  
})
export class NotificationComponent {

  constructor(private vm: NotificationService) { }

  public get VM() { return this.vm; }

}
