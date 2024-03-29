import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LawPageComponent } from './law-page.component';

describe('LawPageComponent', () => {
  let component: LawPageComponent;
  let fixture: ComponentFixture<LawPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LawPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LawPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
