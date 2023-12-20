import {Component, OnInit} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {HttpClient, HttpClientModule} from "@angular/common/http";

@Component({
  selector: 'app-produits',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,HttpClientModule
  ],
  templateUrl: './produits.component.html',
  styleUrl: './produits.component.css'
})
export class ProduitsComponent implements OnInit{
  produits : any
  constructor(private http:HttpClient) {
  }
  ngOnInit() {
    this.http.get("http://localhost:8888/PRODUIT-SERVICE/produits").subscribe({
      next : data => {
        this.produits = data;
      },
      error : err => {
        console.log(err);
      }
    });
  }
}

