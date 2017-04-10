import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivationParentComponent } from './activation-parent.component';

describe('ActivationParentComponent', () => {
  let component: ActivationParentComponent;
  let fixture: ComponentFixture<ActivationParentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivationParentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivationParentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
