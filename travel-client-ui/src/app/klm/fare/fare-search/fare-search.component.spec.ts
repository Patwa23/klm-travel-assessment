import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FareSearchComponent } from './fare-search.component';

describe('FareSearchComponent', () => {
  let component: FareSearchComponent;
  let fixture: ComponentFixture<FareSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FareSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FareSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
