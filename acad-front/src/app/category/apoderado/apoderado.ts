import { Component, Input, OnInit } from '@angular/core';
import { CommonModule, NgFor } from '@angular/common';
import {ApoderadoModel} from '../models/apoderado-model';

@Component({
  selector: 'app-apoderado',
  standalone: true,
  imports: [CommonModule, NgFor],
  templateUrl: './apoderado.html',
  styleUrl: './apoderado.scss'
})
export class Apoderado implements OnInit {

  @Input() apoderados: ApoderadoModel[] = [];

  ngOnInit(): void {
    console.log("=======>> Lista de apoderados:", this.apoderados);
  }
}
