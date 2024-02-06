import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LawSearchResultComponent } from './law-search-result.component';

describe('LawSearchResultComponent', () => {
  let component: LawSearchResultComponent;
  let fixture: ComponentFixture<LawSearchResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LawSearchResultComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LawSearchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
