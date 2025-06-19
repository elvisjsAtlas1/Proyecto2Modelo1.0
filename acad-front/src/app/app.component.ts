import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {Category} from './category/category';
import {ApoderadoServiceService} from './core/service/apoderado-service.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, Category],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'acad-front';

  constructor(private apoderadoServiceService: ApoderadoServiceService) {
    this.apoderadoServiceService.getApoderados().subscribe(response=>{
      console.log(response);
    });
  }


  //protected readonly getEnabledCategories = getEnabledCategories;
  //categories!: import("D:/Proyecto2Modelo1.0/acad-front/src/app/category/models/category-model").CategoryModel[];
}
