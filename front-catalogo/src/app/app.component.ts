import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { AjaxWaitComponent, NotificationComponent, NotificationModalComponent } from './main';
import { NotificationService, NotificationType } from './common-services';
import { HeaderComponent } from "./main/header/header.component";
import { FooterComponent } from "./main/footer/footer.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NotificationComponent, /*NotificationModalComponent,*/
    AjaxWaitComponent, HeaderComponent, FooterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {


}