import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountActivationSuccessfulComponent } from './account-activation-successful.component';

describe('AccountActivationSuccessfulComponent', () => {
  let component: AccountActivationSuccessfulComponent;
  let fixture: ComponentFixture<AccountActivationSuccessfulComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountActivationSuccessfulComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountActivationSuccessfulComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
