import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilamentsListComponent } from './filaments-list.component';

describe('FilamentsListComponent', () => {
  let component: FilamentsListComponent;
  let fixture: ComponentFixture<FilamentsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FilamentsListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FilamentsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
