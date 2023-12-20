import {Component, OnInit} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {HttpClient, HttpClientModule} from "@angular/common/http";

@Component({
  selector: 'app-ventes',
  standalone: true,
    imports: [
      NgForOf,
      NgIf,HttpClientModule
    ],
  templateUrl: './ventes.component.html',
  styleUrl: './ventes.component.css'
})
export class VentesComponent implements OnInit{
  ventes : any
  constructor(private http:HttpClient) {
  }
  ngOnInit() {
    this.http.get("http://localhost:8888/VENTE-SERVICE/ventes").subscribe({
      next : data => {
        this.ventes = data;
      },
      error : err => {
        console.log(err);
      }
    });
  }
}
