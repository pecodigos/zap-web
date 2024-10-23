import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/users';
  private loggedIn = new BehaviorSubject<boolean>(this.hasToken());

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, { username, password }, { withCredentials: true });
  }

  register(name: string, username: string, email: string, password: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, { name, username, email, password });
  }

  logout(): void {
    localStorage.removeItem('userToken');
    this.loggedIn.next(false);
    window.location.href = '/home';
  }

  isLoggedIn(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  private hasToken(): boolean {
    return !!localStorage.getItem('userToken');
  }

  setToken(token: string): void {
    localStorage.setItem('userToken', token);
    this.loggedIn.next(true);
  }
}
