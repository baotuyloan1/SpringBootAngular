import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root',
})
export class QuizService {
  constructor(private _http: HttpClient) {}

  /**
   * get all quizzes
   * @returns
   */
  public quizzes() {
    return this._http.get(`${baseUrl}/quiz/`);
  }

  /**
   * add quiz
   * @param quiz
   * @returns
   */
  public addQuiz(quiz: any) {
    return this._http.post(`${baseUrl}/quiz/`, quiz);
  }

  /**
   * delete Quiz
   * @param qid
   * @returns
   */
  public deleteQuiz(qid: any) {
    return this._http.delete(`${baseUrl}/quiz/${qid}`);
  }

  /**
   * get the single quiz
   * @param qid
   * @returns
   */
  public getQuiz(qid: any) {
    return this._http.get(`${baseUrl}/quiz/${qid}`);
  }

  /**
   * update quiz
   * @param quiz
   * @returns
   */
  public updateQuiz(quiz: any) {
    return this._http.put(`${baseUrl}/quiz/`, quiz);
  }

  /**
   * get quizzes off category
   * @param cid
   * @returns
   */
  public getQuizzesOfCategory(cid: any) {
    return this._http.get(`${baseUrl}/quiz/category/${cid}`);
  }

  /**
   * get active quizzes
   * @returns
   */
  public getActiveQuizzes() {
    return this._http.get(`${baseUrl}/quiz/active`);
  }

  public getActiveQuizzesOfCategory(cid: any) {
    return this._http.get(`${baseUrl}/quiz/category/active/${cid}`);
  }
}
