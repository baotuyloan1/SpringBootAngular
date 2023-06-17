import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quizzes',
  templateUrl: './view-quizzes.component.html',
  styleUrls: ['./view-quizzes.component.css'],
})
export class ViewQuizzesComponent implements OnInit {
  quizzes = [
    {
      qId: 23,
      title: 'Basic Java Quiz',
      description:
        'What is Core Java? It is a part of the Java programming language that one can use for developing or creating a general-purpose app. ',
      maxMarks: '50',
      numberOfQuestions: '20',
      active: '',
      category: {
        title: 'Programming',
      },
    },
  ];
  constructor(private _quiz: QuizService) {}

  ngOnInit(): void {
    this._quiz.quizzes().subscribe(
      (data: any) => {
        this.quizzes = data;
        console.log(this.quizzes);
      },
      (error) => {
        console.log(error);
        Swal.fire('Error !', 'Error in loading data!', 'error');
      }
    );
  }

  /**
   * delete Quiz
   * @param qid
   */
  deleteQuiz(qid: any) {
    Swal.fire({
      icon: 'info',
      title: 'are you sure ?',
      confirmButtonText: 'Delete',
      showCancelButton: true,
    }).then((result) => {
      if (result.isConfirmed) {
        // Delete
        this._quiz.deleteQuiz(qid).subscribe(
          (data) => {
            this.quizzes = this.quizzes.filter((quiz) => quiz.qId != qid);
            Swal.fire('Success', 'Quiz deleted', 'success');
          },
          (error) => {
            Swal.fire('Error', 'Error in deleting quiz', 'error');
          }
        );
      }
    });
  }
}
