import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  public loginStatusSubject = new Subject<boolean>();

  constructor(private http: HttpClient) {}

  /**
   * current user : which is loggedin
   * @returns
   */
  public getCurrentUser() {
    return this.http.get(`${baseUrl}/current-user`);
  }
  /**
   * generate token
   * @param loginData
   * @returns
   */
  public generateToken(loginData: any) {
    return this.http.post(`${baseUrl}/generate-token`, loginData);
  }

  /**
   * login user: set token in localstorage
   * @param token
   * @returns
   */
  public loginUser(token: any) {
    localStorage.setItem('token', token);
    return true;
  }

  /**
   * isLogin : user is logged in or not
   * @returns
   */
  public isLoggedIn() {
    let tokenStr = localStorage.getItem('token');
    if (tokenStr == undefined || tokenStr == '' || tokenStr == null) {
      return false;
    } else {
      return true;
    }
  }
  /**
   * logout : remove token from local storage
   * @returns
   */
  public logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    return true;
  }

  /**
   * get token
   * @returns
   */
  public getToken() {
    return localStorage.getItem('token');
  }

  /**
   * set User Details
   * @param user
   */
  public setUser(user: any) {
    localStorage.setItem('user', JSON.stringify(user));
  }

  /**
   * getUser
   * @returns
   */
  public getUser() {
    let userStr = localStorage.getItem('user');
    if (userStr != null) {
      return JSON.parse(userStr);
    } else {
      this.logout();
      return null;
    }
  }

  /**
   * get user role
   * @returns
   */
  public getUserRole() {
    let user = this.getUser();
    return user.authorities[0].authority;
  }
}
