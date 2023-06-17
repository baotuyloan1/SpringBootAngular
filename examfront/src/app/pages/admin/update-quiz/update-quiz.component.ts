import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-quiz',
  templateUrl: './update-quiz.component.html',
  styleUrls: ['./update-quiz.component.css'],
})
export class UpdateQuizComponent implements OnInit {
  qId = 0;
  constructor(
    private _route: ActivatedRoute,
    private _quiz: QuizService,
    private _cat: CategoryService,
    private _router: Router
  ) {}
  quiz: any = null;
  categories: any = [];

  ngOnInit(): void {
    this._cat.categories().subscribe(
      (data: any) => {
        // categories load
        this.categories = data;
        console.log(this.categories);
      },
      (error) => {
        console.log(error);
        Swal.fire('Error!!', 'Error in loading data from server', 'error');
      }
    );
    this.qId = this._route.snapshot.params['qid'];
    this._quiz.getQuiz(this.qId).subscribe(
      (data) => {
        this.quiz = data;
        console.log(this.quiz);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  /**
   * update form submit
   */
  public updateData() {
    // validate ...
    this._quiz.updateQuiz(this.quiz).subscribe(
      (data) => {
        Swal.fire('Success !!!', 'Quiz updated', 'success').then((e) => {
          this._router.navigate(['/admin/quizzes']);
        });
      },
      (error) => {
        Swal.fire('Error', 'Error in updating quiz', 'error');
        console.log(error);
      }
    );
  }
}
