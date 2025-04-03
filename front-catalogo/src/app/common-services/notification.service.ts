import { Injectable, OnDestroy } from '@angular/core';
import { LoggerService } from '@my/core';
import { Subject } from 'rxjs';

export enum NotificationType { error = 'error', warn = 'warn', info = 'info', log
  = 'log' }
  export class Notification {
    constructor(private id: number, private message: string,
    private type: NotificationType) {}
    public get Id() { return this.id; }
    public get Message() { return this.message; }
    public get Type() { return this.type; }
   }

   

@Injectable({
  providedIn: 'root'
})
export class NotificationService implements OnDestroy{
  private notificacion$ = new Subject<Notification>();
  public get Notificacion() { return this.notificacion$; }

  

  public readonly NotificationType = NotificationType;
  private list: Notification[] = [];

  constructor(private out: LoggerService) { }

  //Show all notifications
  public get List(): Notification[]
 { return Object.assign([], this.list); }
 public get ThereIsNotifications() { return this.list.length > 0; }
 public add(msg: string, type: NotificationType = NotificationType.error) {
  if (!msg || msg === '') {
  this.out.error('Falta el mensaje de notificación.');
  return;
  }
  const id = this.ThereIsNotifications ?
  (this.list[this.list.length - 1].Id + 1) : 1;
  const n = new Notification(id, msg, type);
  this.list.push(n);
  this.notificacion$.next(n);
  // Redundancia: Los errores también se muestran en consola
  if (type === NotificationType.error) {
  this.out.error(`NOTIFICATION: ${msg}`);
  }
  }
  ngOnDestroy(): void {
    this.notificacion$.complete()
    }
 
  //Delete One notification
  public remove(index: number) {
    if (index < 0 || index >= this.list.length) {
    this.out.error('Index out of range.');
    return;
    }
    this.list.splice(index, 1);
    }

    //Delete all notifications
    public clear() {
      if (this.ThereIsNotifications)
      this.list.splice(0);
      }
     

}
