import { Routes } from '@angular/router';
import {ProduitsComponent} from "./produits/produits.component";
import {AcheteursComponent} from "./acheteurs/acheteurs.component";
import {VentesComponent} from "./ventes/ventes.component";

export const routes: Routes = [
  { path : "produits" , component : ProduitsComponent},
  { path : "acheteurs" , component : AcheteursComponent},
  { path : "ventes" , component : VentesComponent}
];
