import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OperatorPickComponent } from './operator-pick.component';

describe('OperatorPickComponent', () => {
  let component: OperatorPickComponent;
  let fixture: ComponentFixture<OperatorPickComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OperatorPickComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OperatorPickComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
