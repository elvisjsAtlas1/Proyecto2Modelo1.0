import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RequisitoComponent } from './requisito'; // Asegúrate de que la ruta sea correcta

describe('RequisitoComponent', () => {
  let component: RequisitoComponent;
  let fixture: ComponentFixture<RequisitoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RequisitoComponent] // standalone component
    }).compileComponents();

    fixture = TestBed.createComponent(RequisitoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
