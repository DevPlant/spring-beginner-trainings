import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountActivationFailedComponent } from './account-activation-failed.component';

describe('AccountActivationFailedComponent', () => {
  let component: AccountActivationFailedComponent;
  let fixture: ComponentFixture<AccountActivationFailedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountActivationFailedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountActivationFailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
