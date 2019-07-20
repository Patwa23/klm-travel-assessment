import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { AirportsComponent } from './airports.component';
import { DebugElement } from '@angular/core';

// describe('AirportsComponent', () => {
//   let component: AirportsComponent;
//   let fixture: ComponentFixture<AirportsComponent>;

//   beforeEach(async(() => {
//     TestBed.configureTestingModule({
//       declarations: [AirportsComponent]
//     })
//       .compileComponents();
//   }));

//   beforeEach(() => {
//     fixture = TestBed.createComponent(AirportsComponent);
//     component = fixture.componentInstance;
//     fixture.detectChanges();
//   });

//   it('should create', () => {
//     expect(component).toBeTruthy();
//   });
// });

describe('AirportsComponent', () => {
  let component: AirportsComponent;
  let fixture: ComponentFixture<AirportsComponent>;
  let de: DebugElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AirportsComponent],
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AirportsComponent);
    component = fixture.componentInstance;
    de = fixture.debugElement;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

});
