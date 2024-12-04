import { Injectable } from '@angular/core';
import { MaterialType } from '../models/material.type';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MaterialsService {
  private apiUrl = 'http://localhost:8080/api/materials';

  constructor(private http: HttpClient) {}

  getMaterialsFromApi(): Observable<Array<MaterialType>> {
    return this.http.get<Array<MaterialType>>(this.apiUrl);
  }

  removeMaterial(id: string): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url);
  }

  addNewMaterial({type, meltingTemp}: {type: string, meltingTemp: number}): Observable<MaterialType> {
    console.log({type, meltingTemp});
    return this.http.post<MaterialType>(this.apiUrl, {type, meltingTemp});
  }
}
