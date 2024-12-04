import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FilamentType } from '../models/filament.type';
@Injectable({
  providedIn: 'root'
})
export class FilamentsService {
  FilamentsItems: Array<FilamentType>=[];
  constructor() { }
}
