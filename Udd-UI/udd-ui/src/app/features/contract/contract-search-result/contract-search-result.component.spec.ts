import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContractSearchResultComponent } from './contract-search-result.component';

describe('ContractSearchResultComponent', () => {
  let component: ContractSearchResultComponent;
  let fixture: ComponentFixture<ContractSearchResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContractSearchResultComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ContractSearchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
