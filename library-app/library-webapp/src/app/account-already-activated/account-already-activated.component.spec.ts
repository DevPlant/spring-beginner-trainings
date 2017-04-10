import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountAlreadyActivatedComponent } from './account-already-activated.component';

describe('AccountAlreadyActivatedComponent', () => {
  let component: AccountAlreadyActivatedComponent;
  let fixture: ComponentFixture<AccountAlreadyActivatedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountAlreadyActivatedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountAlreadyActivatedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
