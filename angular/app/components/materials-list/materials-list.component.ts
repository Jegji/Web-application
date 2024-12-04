import {Component, OnInit, inject, signal} from '@angular/core';
import { MaterialsService } from '../../services/materials.service';
import {MaterialType} from '../../models/material.type';
import {catchError, of} from 'rxjs';
import {NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';
@Component({
  selector: 'app-materials-list',
  imports: [NgIf, FormsModule],
  templateUrl: './materials-list.component.html',
  standalone: true,
  styleUrl: './materials-list.component.css'
})
export class MaterialsListComponent implements OnInit {
  materialService = inject(MaterialsService);
  materials = signal<Array<MaterialType>>([]);
  isAddFormVisible: boolean = false;
  newMaterial: { meltingTemp: number; type: string } = { type: '', meltingTemp: 0 };

  ngOnInit() {
    this.getMaterials();
  }
  toggleForm() {
    this.isAddFormVisible = !this.isAddFormVisible;
  }
  onSubmit() {
    console.log('Form submitted:');
    this.materialService.addNewMaterial(this.newMaterial).subscribe(
      response => {
        console.log('Material added successfully:', response);
        this.isAddFormVisible = false;
        this.getMaterials();
      },
      error => {
        console.error('Error adding material:', error);
      }
    );
  }
  removeMaterial(id: string) {
    console.log('Removing material with id:',id);
    this.materialService.removeMaterial(id).subscribe(
      response => {
        console.log('Material removed successfully:', response);
        this.getMaterials();
      },
      error => {
        console.error('Error removing material:', error);
      }
    );
    console.log("Material removed");
  }

  getMaterials() {
    this.materialService
      .getMaterialsFromApi()
      .pipe(catchError((err) => {
        console.error(err);
        return of([]);
      }))
      .subscribe((materialsList) =>
        this.materials.set(materialsList)
      );
    console.log("Got materials:");
  }
}
