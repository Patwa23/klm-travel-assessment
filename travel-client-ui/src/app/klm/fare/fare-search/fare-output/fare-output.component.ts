import { Component, OnInit, Input } from '@angular/core';
import { Fare } from '../../fare.model';


@Component({
  selector: 'app-fare-output',
  templateUrl: './fare-output.component.html',
  styleUrls: ['./fare-output.component.css']
})
export class FareOutputComponent {
  @Input() fare: Fare;
  @Input() isCardHide;
  @Input() isSpinnerHide;
}
