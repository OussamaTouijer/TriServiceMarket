import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-acheteurs',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,HttpClientModule
  ],
  templateUrl: './acheteurs.component.html',
  styleUrl: './acheteurs.component.css'
})
export class AcheteursComponent implements OnInit{
  acheteurs : any
  constructor(private http:HttpClient) {
  }
  ngOnInit() {
    this.http.get("http://localhost:8888/ACHETEUR-SERVICE/acheteurs").subscribe({
      next : data => {
        this.acheteurs = data;
      },
      error : err => {
        console.log(err);
      }
    });
  }
}
