import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FareOutputComponent } from './fare-output.component';

describe('FareOutputComponent', () => {
  let component: FareOutputComponent;
  let fixture: ComponentFixture<FareOutputComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FareOutputComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FareOutputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
