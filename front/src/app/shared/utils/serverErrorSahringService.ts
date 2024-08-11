import { Observable, Subject } from 'rxjs';
import { Injectable } from '@angular/core';
@Injectable({ providedIn: 'root' })
export class MessageService {
    private subject = new Subject();
 
    sendMessage(violations: any) {
        this.subject.next({ violations: violations });
    }
 
    clearMessages() {
        this.subject.next({});
    }
 
    getMessage(): Observable<any>  {
        return this.subject.asObservable();
    }
}